package com.xinshijie.wiki.aspect;

import com.alibaba.fastjson2.JSONObject;
import com.xinshijie.common.annotation.UserVelidate;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.common.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 拦截器
 * 判断是否有权限
 */
@Aspect
@Component
@Slf4j
public class UserValidateAspect {

    @Before("@annotation(com.xinshijie.common.annotation.UserVelidate)")
    public void repeatSumbitIntercept(JoinPoint joinPoint) {
        log.debug("RepeatSubmitInterceptor");
        log.info("用户权限判断切面");
        //获取接口的参数
        Object[] arr = joinPoint.getArgs();
        String[] parameterNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        Object object = arr[0];
        if (object == null) {
            throw new ServiceException(ResultCodeEnum.PARAMS_NOT_COMPLETE);
        }
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(object));
        log.info("请求参数:" + jsonObject.toString());
        Long userid = SecurityUtils.getUserId();
//        Message message = messageService.selectMessageByWidUserId(jsonObject.getLong("wid"),userid);
//        if(message == null){
//            throw new ServiceException("非法请求,权限不足");
//        }

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        UserVelidate annotation = method.getAnnotation(UserVelidate.class);
        String[] requestParams = annotation.requestParams();
        log.info("annotation参数:" + JSONObject.toJSONString(requestParams));
    }
}
