package com.xinshijie.common.core.vo;


import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.exception.ServiceException;

import java.util.Map;

public class ApiResult<T> {
    private Integer code;
    private String msg;
    private String version;
    private T data;

    private ApiResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ApiResult(ResultCodeEnum ResultCodeEnum) {
        if (ResultCodeEnum != null) {
            this.code = ResultCodeEnum.getCode();
            this.msg = ResultCodeEnum.getMsg();
        }

    }

    private ApiResult(T data, String version) {
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.msg = ResultCodeEnum.SUCCESS.getMsg();
        this.version = version;
        this.data = data;
    }

    public T result() {
        if (ResultCodeEnum.SUCCESS.getCode() != this.code) {
            throw new ServiceException(this.code, this.msg);
        } else {
            return this.data;
        }
    }

    public static <T> ApiResult<T> success(T data, String version) {
        return new ApiResult(data, version);
    }

    public static <T> ApiResult<T> success(Map<Object, String> obj) {
        return new ApiResult(obj.get("data"), obj.get("version"));
    }

    public static <T> ApiResult<T> success() {
        return new ApiResult(ResultCodeEnum.SUCCESS);
    }

    public static <T> ApiResult<T> success(Integer code, String msg) {
        return new ApiResult(code, msg);
    }

    public static <T> ApiResult<T> error(ResultCodeEnum ResultCodeEnum) {
        return new ApiResult(ResultCodeEnum);
    }

    public static <T> ApiResult<T> error(Integer code, String msg) {
        return new ApiResult(code, msg);
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getVersion() {
        return this.version;
    }

    public T getData() {
        return this.data;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    public void setVersion(final String version) {
        this.version = version;
    }

    public void setData(final T data) {
        this.data = data;
    }
}
