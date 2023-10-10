package com.xinshijie.framework.security.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;

/**
 * 身份验证信息
 *
 * @author xinshijie
 */
@Slf4j
public class AuthenticationContextHolder {
    private static final ThreadLocal<Authentication> contextHolder = new ThreadLocal<>();

    public static Authentication getContext() {
        log.debug("getContext");
        return contextHolder.get();
    }

    public static void setContext(Authentication context) {
        log.debug("setContext");
        contextHolder.set(context);
    }

    public static void clearContext() {
        log.debug("clearContext");
        contextHolder.remove();
    }
}
