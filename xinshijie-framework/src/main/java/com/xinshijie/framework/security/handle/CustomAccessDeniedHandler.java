package com.xinshijie.framework.security.handle;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

/**
 * ，用于处理访问被拒绝（Access Denied）的情况。当用户尝试访问一个需要授权才能访问的资源，
 * 但是他们的权限不足，或者他们尝试执行没有权限的操作时，Spring Security 将会触发访问被拒绝的事件
 */
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * 访问被拒绝调用
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 在这里可以进行自定义的访问被拒绝处理逻辑
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write("Access Denied - Custom Access Denied Handler");
    }
}
