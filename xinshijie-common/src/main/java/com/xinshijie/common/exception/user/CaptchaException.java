package com.xinshijie.common.exception.user;

import com.xinshijie.common.enums.ResultCodeEnum;

import java.io.Serial;

/**
 * 验证码错误异常类
 *
 * @author xinshijie
 */
public class CaptchaException extends UserException {
    @Serial
    private static final long serialVersionUID = 1L;

    public CaptchaException() {
        super("user.jcaptcha.error", null);
    }

    public CaptchaException(ResultCodeEnum resultCodeEnum) {
        super("user.jcaptcha.expire", null);
    }
}
