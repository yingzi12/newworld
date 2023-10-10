package com.xinshijie.common.utils;

import cn.hutool.jwt.JWT;

import java.util.Calendar;

public class JwtUtil {
    // 秘钥，
    public static final String SECRET = "ASD!@#F^%A";

    /**
     * // 过期时间，60s
     *
     * @param expires
     * @return
     */
    public static String getToken(Long id, String username, String email, Calendar expires) {
        // 密钥
        byte[] key = SECRET.getBytes();
        String token = JWT.create()
                .setPayload("id", id)
                .setPayload("username", username)
                .setPayload("email", email)
                .setKey(key)
                .setExpiresAt(expires.getTime())
                .sign();
        return token;
    }
}
