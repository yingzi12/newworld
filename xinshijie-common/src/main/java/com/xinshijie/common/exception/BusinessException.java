package com.xinshijie.common.exception;


import com.xinshijie.common.enums.RespCodeEnum;
import com.xinshijie.common.enums.ResultCodeEnum;

public class BusinessException extends ServiceException {
    public BusinessException(ResultCodeEnum resultCode) {
        super(resultCode);
    }

    public BusinessException(RespCodeEnum resultCode) {
        super(resultCode);
    }

    public BusinessException(Integer code, String msg) {
        super(code, msg);
    }

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(Throwable throwable) {
        super(throwable);
    }

    public BusinessException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
