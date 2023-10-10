package com.xinshijie.framework.security.handle;

import com.alibaba.fastjson2.JSON;
import com.xinshijie.common.constant.HttpStatus;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.utils.ServletUtils;
import com.xinshijie.common.utils.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

/**
 * 认证失败处理类 返回未授权
 *
 * @author xinshijie
 */
@Slf4j
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    @Serial
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {
        log.debug("AuthenticationEntryPointImpl e:{},", e);
        int code = ResultCodeEnum.EXPIRED.getCode();
        log.warn("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        String msg = StringUtils.format("认证失败，无法访问系统资源");
        response.setStatus( HttpStatus.SUCCESS);
        ServletUtils.renderString(response, JSON.toJSONString(Result.error(code, msg)));
    }
}
