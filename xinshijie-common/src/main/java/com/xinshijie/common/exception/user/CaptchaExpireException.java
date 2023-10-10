package com.xinshijie.common.exception.user;

import com.xinshijie.common.enums.ResultCodeEnum;

import java.io.Serial;

/**
 * 验证码失效异常类
 *
 * @author xinshijie
 */
public class CaptchaExpireException extends UserException {
    @Serial
    private static final long serialVersionUID = 1L;

    public CaptchaExpireException() {
        super("user.jcaptcha.expire", null);
    }

    public CaptchaExpireException(ResultCodeEnum resultCodeEnum) {
        super("user.jcaptcha.expire", null);
    }
}
