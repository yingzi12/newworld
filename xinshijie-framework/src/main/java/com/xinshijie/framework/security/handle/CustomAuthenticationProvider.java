package com.xinshijie.framework.security.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) {
        log.debug("CustomAuthenticationProvider");
        // 从认证信息中获取用户名和密码
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // 使用用户名和密码查询用户信息
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // 验证密码
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            // 验证成功，返回认证信息
            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        } else {
            // 验证失败，抛出异常
            throw new BadCredentialsException("用户名或密码错误");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 只支持 UsernamePasswordAuthenticationToken 类型的认证信息
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}