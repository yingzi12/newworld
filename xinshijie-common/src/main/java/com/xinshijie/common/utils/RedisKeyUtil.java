package com.xinshijie.common.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.Assert;

/**
 * RedisKey 枚举类
 */
@Getter
public enum RedisKeyUtil {

    /**
     * key较多的情况：不定义具体的key, key当参数传给keyBuilder
     */

    OAUTH_OFFICIALADMIN_TOKEN("OAUTH", "OFFICIALADMIN", "TOKEN", ""),
    /**
     * key较少的情况：
     * 1) 像下面这种定义具体的key
     * 2）在ConstantKeyEnum里定义具体的key
     * 2种方式习惯用哪个都行
     */
    STORE_MODULE3_FUN3_343("STORE", "MODULE3", "FUN3", "343", ""),

    OFFICIALADMIN_VERIFYCODE_BINDMOBILE("OFFICIALADMIN", "VERIFYCODE", "BINDMOBILE", ""),

    OFFICIALADMIN_VERIFYCODE_CHANGEPASSWORD("OFFICIALADMIN", "VERIFYCODE", "CHANGEPASSWORD", ""),
    OFFICIALADMIN_CAPTCHA("OFFICIALADMIN", "CAPTCHA", "CAPTCHA", ""),

    ;

    /**
     * 系统标识
     */
    private final String keyPrefix;
    /**
     * 模块名称
     */
    private final String module;
    /**
     * 方法名称
     */
    private final String func;
    /**
     * key
     */
    private String key;
    /**
     * 描述
     */
    private final String remark;

    RedisKeyUtil(String keyPrefix, String module, String func, String remark) {
        this.keyPrefix = keyPrefix;
        this.module = module;
        this.func = func;
        this.remark = remark;
    }

    RedisKeyUtil(String keyPrefix, String module, String func, String key, String remark) {
        this.keyPrefix = keyPrefix;
        this.module = module;
        this.func = func;
        this.key = key;
        this.remark = remark;
    }

    public String keyBuilder() {
        return checkAndGetValue(key);
    }

    public String keyBuilder(String key) {
        return checkAndGetValue(key);
    }

    private String checkAndGetValue(String key) {
        Assert.notNull(keyPrefix, "RedisKeyEnum: keyPrefix can not be null");
        Assert.notNull(module, "RedisKeyEnum: module can not be null");
        Assert.notNull(func, "RedisKeyEnum: func can not be null");
        Assert.notNull(key, "RedisKeyEnum: key can not be null");
        return keyPrefix + ":" + module + ":" + func + ":" + key;
    }

    /***
     * Redis常量key在此枚举中申明
     *
     * @author hulilei
     * @date 2019/6/11
     */
    @Getter
    @AllArgsConstructor
    enum ConstantKeyEnum {
        /**
         * 常量Key样例
         */
        LMDM_TOKEN_EXPIRE_TIME("LMDM:TOKEN:EXPIRE:TIME", "token过期时间"),
        LMDM_SMS_CODE_EXPIRE("LMDM:SMS:CODE:EXPIRE", "短信验证码过期时间");
        String key;
        String remark;
    }

}

