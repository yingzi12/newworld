package com.xinshijie.web.controller.admin;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.extra.mail.MailUtil;
import com.xinshijie.common.annotation.Log;
import com.xinshijie.common.config.XinshijieConfig;
import com.xinshijie.common.constant.CacheConstants;
import com.xinshijie.common.constant.Constants;
import com.xinshijie.common.constant.UserConstants;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.domain.model.LoginUser;
import com.xinshijie.common.core.redis.RedisCache;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.BusinessType;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.common.utils.StringUtils;
import com.xinshijie.common.utils.file.FileUploadUtils;
import com.xinshijie.common.utils.file.MimeTypeUtils;
import com.xinshijie.wiki.dto.UserDto;
import com.xinshijie.wiki.dto.UserPasswordDto;
import com.xinshijie.wiki.service.IUserService;
import com.xinshijie.wiki.vo.UserVo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;

@Valid
@Slf4j
@RestController
@RequestMapping("/admin/user")
public class AdminUserController extends BaseController {
    @Autowired
    private IUserService userService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 根据id获取详情
     */
    @GetMapping("/getInfo")
    @Operation(summary = "getInfo", description = "根据id获取详情")
    public Result<UserVo> getInfo() {
        Long userid = SecurityUtils.getUserId();
        return Result.success(userService.getInfo(userid));
    }

    /**
     * 修改基本详细
     */
    @Log(title = "修改基本详细", businessType = BusinessType.UPDATE)
    @PutMapping("/editBasic")
    public Result<String> editBasic(@RequestBody UserDto dto) {
        Long userid = SecurityUtils.getUserId();
        dto.setUserId(userid);
        return toAjax(userService.editBasic(dto));
    }

    /**
     * 新增用户
     */
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("add")
    public Result<String> add(@Validated @RequestBody UserDto user) {

        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName()))) {
            return Result.error(ResultCodeEnum.SYSTEM_INNER_ERROR.getCode(), "新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        } else if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return Result.error(ResultCodeEnum.SYSTEM_INNER_ERROR.getCode(), "新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user.getUserId(), user.getEmail()))) {
            return Result.error(ResultCodeEnum.SYSTEM_INNER_ERROR.getCode(), "新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("edit")
    public Result<String> edit(@Validated @RequestBody UserDto user) {
        if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return Result.error(ResultCodeEnum.SYSTEM_INNER_ERROR.getCode(), "修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user.getUserId(), user.getEmail()))) {
            return Result.error(ResultCodeEnum.SYSTEM_INNER_ERROR.getCode(), "修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setUpdateBy(getUsername());
        return toAjax(userService.updateUser(user));
    }

    /**
     * 重置密码
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/resetPwd")
    public Result<String> resetPwd(@Validated @RequestBody UserPasswordDto dto) {
        return toAjax(userService.resetPwd(dto));
    }

    @Log(title = "发送邮箱验证链接", businessType = BusinessType.UPDATE)
    @GetMapping("/sendCheckEmail")
    public Result<String> sendCheckEmail() {
        UserVo userVo = userService.getInfo(getUserId());
        boolean isEmail = Validator.isEmail(userVo.getEmail());
        if (StringUtils.isNotEmpty(userVo.getEmail()) && isEmail) {
            if (redisCache.hasKey(CacheConstants.EMAIL + userVo.getEmail())) {
                throw new ServiceException(ResultCodeEnum.EMAIL_ALREADY_SENT);
            }
            String simpleUUID = IdUtil.simpleUUID();
            Digester md5 = new Digester(DigestAlgorithm.MD5);
            // 5393554e94bf0eb6436f240a4fd71282
            String digestHex = userVo.getEmail() + ":" + md5.digestHex(userVo.getEmail());
            redisCache.setCacheString(CacheConstants.EMAIL + simpleUUID, userVo.getEmail() + ":" + digestHex, Constants.EMAIL_EXPIRATION, TimeUnit.MINUTES);
            redisCache.setCacheString(CacheConstants.EMAIL + userVo.getEmail(), simpleUUID, Constants.EMAIL_EXPIRATION, TimeUnit.MINUTES);
            String content = "<h1>尊敬的" + getUsername() + ":<h1>\n" +
                    "你正在进行安全邮箱的绑定操作,请点击下方链接地址,来进行邮箱的验证操作,链接30分钟有效.  \n" +
                    " 点击验证:<a href=\"https://www.aiavr.com/check/email?key=" + simpleUUID + "&check=" + digestHex + "\">https://www.aiavr.com/wiki/check/email?key=" + simpleUUID + "&check=" + digestHex + "</a>\n";

            MailUtil.send(userVo.getEmail(), "安全邮箱验证", content, true);
        } else {
            throw new ServiceException(ResultCodeEnum.THE_EMAIL_IS_EMPTY_OR_ILLEGAL);
        }
//        MailUtil.send(account, CollUtil.newArrayList("xun335610@163.com"), "测试", content, true);
        return Result.success("成功");
    }

    @Log(title = "发送邮箱验证链接", businessType = BusinessType.UPDATE)
    @GetMapping("/sendUpdateCheckEmail")
    public Result<String> sendUpdateCheckEmail(@RequestParam("email") String email) {
        boolean isEmail = Validator.isEmail(email);
        if (StringUtils.isNotEmpty(email) && isEmail) {
            String check = userService.checkEmailUnique(getUserId(), email);
            if (UserConstants.NOT_UNIQUE.equals(check)) {
                throw new ServiceException(ResultCodeEnum.ALREADY_BOUND_ACCOUNT);
            }
            if (redisCache.hasKey(CacheConstants.EMAIL + email)) {
                throw new ServiceException(ResultCodeEnum.EMAIL_ALREADY_SENT);
            }
            String simpleUUID = IdUtil.simpleUUID();
            Digester md5 = new Digester(DigestAlgorithm.MD5);
            // 5393554e94bf0eb6436f240a4fd71282
            String digestHex = md5.digestHex("安全邮箱绑定" + email);
            redisCache.setCacheString(CacheConstants.EMAIL + simpleUUID, email + ":" + digestHex, Constants.EMAIL_EXPIRATION, TimeUnit.MINUTES);
            redisCache.setCacheString(CacheConstants.EMAIL + email, simpleUUID, Constants.EMAIL_EXPIRATION, TimeUnit.MINUTES);

            String content = "<h1>尊敬的" + getUsername() + ":<h1>\n" +
                    "你正在进行安全邮箱的绑定操作,请点击下方链接地址,来进行邮箱的验证操作，链接30分钟有效.  \n" +
                    " 点击验证:<a href=\"https://www.aiavr.com/check/email?key=" + simpleUUID + "&check=" + digestHex + "\">https://www.aiavr.com/check/email?key=" + simpleUUID + "&check=" + digestHex + "</a>\n";

            MailUtil.send(email, "安全邮箱验证", content, true);
        } else {
            throw new ServiceException(ResultCodeEnum.THE_EMAIL_IS_EMPTY_OR_ILLEGAL);
        }
        return Result.success("成功");
    }

    /**
     * 头像
     */
    @Log(title = "头像", businessType = BusinessType.UPDATE)
    @PostMapping("/imageUrl")
    public Result<String> imageUrl(@RequestParam("imageUrl") MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            LoginUser loginUser = getLoginUser();
            String avatar = FileUploadUtils.upload(XinshijieConfig.getWorldPath(), file, MimeTypeUtils.IMAGE_EXTENSION);
//            if (worldService.updateWorld(loginUser.getUsername(), avatar))
//            {
//                Result<>  ajax = Result.success();
//                ajax.put("imgUrl", avatar);
//                // 更新缓存用户头像
//                loginUser.getUser().setAvatar(avatar);
//                tokenService.setLoginUser(loginUser);
//                return ajax;
//            }
        }
        return Result.error(ResultCodeEnum.SYSTEM_INNER_ERROR.getCode(), "上传图片异常，请联系管理员");
    }


}
