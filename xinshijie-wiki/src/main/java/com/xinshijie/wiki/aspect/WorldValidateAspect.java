package com.xinshijie.wiki.aspect;

import com.alibaba.fastjson2.JSONObject;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.wiki.service.IManageService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 拦截器
 * 判断是否有权限
 */
@Aspect
@Component
@Slf4j
public class WorldValidateAspect {

    @Autowired
    private IManageService manageService;

    @Before("@annotation(com.xinshijie.common.annotation.WorldVelidate)")
    public void repeatSumbitIntercept(JoinPoint joinPoint) {
        log.debug("RepeatSubmitInterceptor");
        log.info("世界权限判断切面");
        //获取接口的参数
        Object[] arr = joinPoint.getArgs();
        String[] parameterNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        Object object = arr[0];
        if (object == null) {
            throw new ServiceException(ResultCodeEnum.PARAMS_NOT_COMPLETE);
        }
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(object));
        log.info("请求参数:" + jsonObject.toString());
        if (!jsonObject.containsKey("wid")) {
            throw new ServiceException(ResultCodeEnum.PARAMS_NOT_COMPLETE);
        }
        Long userid = SecurityUtils.getUserId();
        manageService.isCheck(jsonObject.getInteger("wid"));
    }
}
