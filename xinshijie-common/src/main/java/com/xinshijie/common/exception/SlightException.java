package com.xinshijie.common.exception;


import com.xinshijie.common.enums.RespCodeEnum;
import com.xinshijie.common.enums.ResultCodeEnum;

import java.io.Serial;

public class SlightException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer code;

    public SlightException(ResultCodeEnum resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
    }

    public SlightException(RespCodeEnum resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
    }

    public SlightException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public SlightException(String msg) {
        super(msg);
    }

    public SlightException(Throwable throwable) {
        super(throwable);
    }

    public SlightException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

}
