package com.xinshijie.web.controller.wiki;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.extra.mail.MailUtil;
import com.xinshijie.common.constant.CacheConstants;
import com.xinshijie.common.constant.Constants;
import com.xinshijie.common.core.redis.RedisCache;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.common.utils.StringUtils;
import com.xinshijie.wiki.dto.UserDto;
import com.xinshijie.wiki.dto.UserPasswordDto;
import com.xinshijie.wiki.service.IUserService;
import com.xinshijie.wiki.vo.UserVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@Tag(name = "UserController", description = "用户")
@RequestMapping("/wiki/user")
public class UserController {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private IUserService userService;
    @Value(value = "${user.password.lockTime}")
    private int lockTime;

    @GetMapping("/checkEmail")
    public Result<String> sendEmail(@Validated @Param("key") String key, @Param("check") String check) {
        System.out.println(key + "::::::" + check);
        return Result.success();
    }

    @GetMapping("/updateCheckEmail")
    public Result<String> updateCheckEmail(@Validated @Param("key") String key, @Param("check") String check) {
        if (!redisCache.hasKey(CacheConstants.EMAIL + key)) {
            throw new ServiceException(ResultCodeEnum.EXPIRED);
        }
        String emailMd5 = redisCache.getCacheString(CacheConstants.EMAIL + key);
        String[] varArr = emailMd5.split(":");
        String email = varArr[0];
        String md5 = varArr[1];
        Digester digester = new Digester(DigestAlgorithm.MD5);

        // 5393554e94bf0eb6436f240a4fd71282
        String digestHex = digester.digestHex("安全邮箱绑定" + email);
        if (md5.equals(digestHex)) {
            userService.updateEmail(email);
            redisCache.deleteObject(CacheConstants.EMAIL + key);
        } else {
            throw new ServiceException(ResultCodeEnum.OPERATOR_ERROR);
        }
        System.out.println(key + "::::::" + check);
        return Result.success("绑定成功");
    }

    @GetMapping("/sendCheckPassword")
    public Result<String> sendCheckPassword(@RequestParam("email") String email) {
        boolean isEmail = Validator.isEmail(email);
        if (StringUtils.isNotEmpty(email) && isEmail) {
            UserVo info = userService.selectByEmail(email);
            if (info == null || info.getIsEmail() == 0) {
                throw new ServiceException(ResultCodeEnum.THE_EMAIL_DOES_NOT_EXIST_OR_IS_NOT_VERIFIED );
            }
            String simpleUUID = IdUtil.simpleUUID();
            Digester md5 = new Digester(DigestAlgorithm.MD5);
            // 5393554e94bf0eb6436f240a4fd71282
            String digestHex = md5.digestHex("找回密码" + email);
            redisCache.setCacheString(CacheConstants.PASSWORD + simpleUUID, email + ":" + digestHex, Constants.EMAIL_EXPIRATION, TimeUnit.MINUTES);
            redisCache.setCacheString(CacheConstants.PASSWORD + email, simpleUUID, Constants.EMAIL_EXPIRATION, TimeUnit.MINUTES);

            String content = "<h1>尊敬的用户<h1>\n" +
                    "你正在进行密码找回操作,请点击下方链接地址,来进行重置密码操作，链接30分钟有效.  \n" +
                    " 点击验证:<a href=\"https://www.aiavr.com/check/password?key=" + simpleUUID + "&check=" + digestHex + "\">https://www.aiavr.com/check/password?key=" + simpleUUID + "&check=" + digestHex + "</a>\n";
            MailUtil.send(email, "密码找回", content, true);
            log.info("{}发送重置密码邮件成功", email);
        } else {
            throw new ServiceException(ResultCodeEnum.THE_EMAIL_IS_EMPTY_OR_ILLEGAL);
        }
        return Result.success();
    }

    @GetMapping("/checkPasswortUrl")
    public Result<String> checkPasswortUrl(@Validated @Param("key") String key, @Param("check") String check) {
        if (redisCache.hasKey(CacheConstants.EMAIL + key)) {
            throw new ServiceException(ResultCodeEnum.EXPIRED);
        }
        String emailMd5 = redisCache.getCacheString(CacheConstants.EMAIL + key);
        String[] varArr = emailMd5.split(":");
        String email = varArr[0];
        String md5 = varArr[1];

        Digester digester = new Digester(DigestAlgorithm.MD5);

        // 5393554e94bf0eb6436f240a4fd71282
        String digestHex = digester.digestHex("找回密码" + email);
        if (!md5.equals(digestHex)) {
            throw new ServiceException(ResultCodeEnum.OPERATOR_ERROR);
        }
        return Result.success("绑定成功");
    }

    @PutMapping("/restPasswort")
    public Result<String> restPasswort(@RequestBody UserPasswordDto dto) {
        if (!redisCache.hasKey(CacheConstants.PASSWORD + dto.getKey())) {
            throw new ServiceException(ResultCodeEnum.EXPIRED);
        }
        String emailMd5 = redisCache.getCacheString(CacheConstants.PASSWORD + dto.getKey());
        String[] varArr = emailMd5.split(":");
        String email = varArr[0];
        String md5 = varArr[1];

        Digester digester = new Digester(DigestAlgorithm.MD5);

        // 5393554e94bf0eb6436f240a4fd71282
        String digestHex = digester.digestHex("找回密码" + email);
        if (!md5.equals(digestHex)) {
            throw new ServiceException(ResultCodeEnum.OPERATOR_ERROR);
        } else {
            UserVo userVo = userService.selectByEmail(email);
            UserDto userDto = new UserDto();
            userDto.setUserId(userVo.getUserId());
            userDto.setPassword(SecurityUtils.encryptPassword(dto.getNewPassword()));
            userDto.setUpdateTime(LocalDateTime.now());
            userService.updateUser(userDto);
            redisCache.deleteObject(CacheConstants.PASSWORD + dto.getKey());
            redisCache.setCacheInteger(getCacheKey(userVo.getUserName()), 0, lockTime, TimeUnit.MINUTES);
        }
        return Result.success("重置成功");
    }

    /**
     * 登录账户密码错误次数缓存键名
     *
     * @param username 用户名
     * @return 缓存键key
     */
    private String getCacheKey(String username) {
        return CacheConstants.PWD_ERR_CNT_KEY + username;
    }
}
