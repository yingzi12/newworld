package com.xinshijie.common.utils;

import java.util.Base64;


public class Base64Utils {
    static final String salt = "jadada236t2";

    /**
     * 加密
     *
     * @param str
     * @return
     */
    public static String encode(String str) {
        byte[] bytes = (str + salt).getBytes();
        //Base64 加密
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 解密
     *
     * @param str
     */
    public static String decode(String str) {
        //Base64 解密
        byte[] decoded = Base64.getDecoder().decode(str);
        return new String(decoded).replace(salt, "");
    }

}
