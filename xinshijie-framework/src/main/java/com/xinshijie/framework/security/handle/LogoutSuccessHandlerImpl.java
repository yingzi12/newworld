//package com.xinshijie.framework.security.handle;
//
//import java.io.IOException;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//import com.alibaba.fastjson2.JSON;
//import com.xinshijie.common.constant.Constants;
//import com.xinshijie.common.constant.HttpStatus;
//import com.xinshijie.common.core.vo.Result;
//import com.xinshijie.common.core.domain.model.LoginUser;
//import com.xinshijie.common.utils.ServletUtils;
//import com.xinshijie.common.utils.StringUtils;
//import com.xinshijie.framework.manager.AsyncManager;
//import com.xinshijie.framework.manager.factory.AsyncFactory;
//import com.xinshijie.framework.web.service.TokenService;
//
///**
// * 自定义退出处理类 返回成功
// *
// * @author xinshijie
// */
//@Slf4j
//@Configuration
//public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler
//{
//    @Autowired
//    private TokenService tokenService;
//
//    /**
//     * 退出处理
//     *
//     * @return
//     */
//    @Override
//    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
//    {
//        log.debug("LogoutSuccessHandlerImpl ");
//        LoginUser loginUser = tokenService.getLoginUser(request);
//        if (StringUtils.isNotNull(loginUser))
//        {
//            String userName = loginUser.getUsername();
//            // 删除用户缓存记录
//            tokenService.delLoginUser(loginUser.getToken());
//            // 记录用户退出日志
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGOUT, "退出成功"));
//        }
//        ServletUtils.renderString(response, JSON.toJSONString(Result.error(HttpStatus.SUCCESS, "退出成功")));
//    }
//}
