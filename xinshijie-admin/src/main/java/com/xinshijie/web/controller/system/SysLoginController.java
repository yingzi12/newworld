package com.xinshijie.web.controller.system;

import com.xinshijie.common.constant.Constants;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.domain.entity.SysMenu;
import com.xinshijie.common.core.domain.entity.SysUser;
import com.xinshijie.common.core.domain.model.LoginBody;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.common.utils.StringUtils;
import com.xinshijie.framework.manager.AsyncManager;
import com.xinshijie.framework.manager.factory.AsyncFactory;
import com.xinshijie.framework.web.service.SysLoginService;
import com.xinshijie.framework.web.service.SysPermissionService;
import com.xinshijie.framework.web.service.TokenService;
import com.xinshijie.system.domain.vo.RouterVo;
import com.xinshijie.system.service.ISysMenuService;
import com.xinshijie.system.vo.LoginUserVo;
import com.xinshijie.wiki.dto.LogoutDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * 登录验证
 *
 * @author xinshijie
 */
@Slf4j
@RestController
public class SysLoginController extends BaseController {
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public LoginUserVo login(@RequestBody LoginBody loginBody) {
        // 生成令牌
        LoginUserVo ajax = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());

        return ajax;
    }

    @PostMapping("/logout")
    public Result<String> logout(@RequestBody LogoutDto logoutDto) {
        try {
//            LoginUser loginUser =getLoginUser();
            if (StringUtils.isNotNull(logoutDto.getToken())) {
//                String userName = loginUser.getUsername();
                // 删除用户缓存记录
                tokenService.delLoginUser(logoutDto.getToken());
                // 记录用户退出日志
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(logoutDto.getUserName(), Constants.LOGOUT, "退出成功"));
            }
        } catch (Exception ex) {
            log.info("退出异常:{}", ex);
        }
        return Result.success();
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public LoginUserVo getInfo() {
        LoginUserVo ajax = new LoginUserVo();

        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        ajax.setCode(200);
        ajax.setUser(user);
        ajax.setRoles(roles);
        ajax.setPermissions(permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public Result<List<RouterVo>> getRouters() {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return Result.success(menuService.buildMenus(menus));
    }
}
