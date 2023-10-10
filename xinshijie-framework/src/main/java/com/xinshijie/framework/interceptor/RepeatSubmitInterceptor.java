package com.xinshijie.framework.interceptor;

import com.alibaba.fastjson2.JSON;
import com.xinshijie.common.annotation.RepeatSubmit;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.utils.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

import static com.xinshijie.common.enums.ResultCodeEnum.SYSTEM_INNER_ERROR;

/**
 * 防止重复提交拦截器
 *
 * @author xinshijie
 */
@Slf4j
@Component
public abstract class RepeatSubmitInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("RepeatSubmitInterceptor");
        if (handler instanceof HandlerMethod handlerMethod) {
            Method method = handlerMethod.getMethod();
            RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
            if (annotation != null) {
                if (this.isRepeatSubmit(request, annotation)) {

                    ServletUtils.renderString(response, JSON.toJSONString(Result.error(SYSTEM_INNER_ERROR.getCode(), annotation.message())));
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    /**
     * 验证是否重复提交由子类实现具体的防重复提交的规则
     *
     * @param request
     * @return
     * @throws Exception
     */
    public abstract boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation);
}
