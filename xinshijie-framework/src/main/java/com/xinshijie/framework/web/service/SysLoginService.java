package com.xinshijie.framework.web.service;

import com.xinshijie.common.constant.CacheConstants;
import com.xinshijie.common.constant.Constants;
import com.xinshijie.common.core.domain.entity.SysUser;
import com.xinshijie.common.core.domain.model.LoginUser;
import com.xinshijie.common.core.redis.RedisCache;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.common.exception.user.CaptchaException;
import com.xinshijie.common.exception.user.CaptchaExpireException;
import com.xinshijie.common.exception.user.UserPasswordNotMatchException;
import com.xinshijie.common.utils.DateUtils;
import com.xinshijie.common.utils.MessageUtils;
import com.xinshijie.common.utils.ServletUtils;
import com.xinshijie.common.utils.StringUtils;
import com.xinshijie.common.utils.ip.IpUtils;
import com.xinshijie.framework.manager.AsyncManager;
import com.xinshijie.framework.manager.factory.AsyncFactory;
import com.xinshijie.framework.security.context.AuthenticationContextHolder;
import com.xinshijie.system.service.ISysConfigService;
import com.xinshijie.system.service.ISysUserService;
import com.xinshijie.system.vo.LoginUserVo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * 登录校验方法
 *
 * @author xinshijie
 */
@Component
public class SysLoginService {
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public LoginUserVo login(String username, String password, String code, String uuid) {
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        // 验证码开关
        if (captchaEnabled) {
            validateCaptcha(username, code, uuid);
        }
        // 用户验证
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(ResultCodeEnum.USER_ACCOUNT_ERROR);
            }
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser.getUserId());
        LoginUserVo ajax = new LoginUserVo();
        ajax.setToken(tokenService.createToken(loginUser));
        ajax.setCode(200);
        ajax.setUser(loginUser.getUser());
        ajax.setAccessToken(ajax.getToken());
        ajax.setRefreshToken(ajax.getToken());
        // 生成token
        return ajax;
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid) {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheString(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
            throw new CaptchaExpireException(ResultCodeEnum.USER_CODE_ERROR);
        }
        if (!code.equalsIgnoreCase(captcha)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException(ResultCodeEnum.USER_CODE_ERROR);
        }
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        sysUser.setLoginDate(DateUtils.getNowDate());
        userService.updateUserProfile(sysUser);
    }
}
