package com.xinshijie.framework.config;

import com.xinshijie.framework.config.properties.PermitAllUrlProperties;
import com.xinshijie.framework.security.filter.JwtAuthenticationTokenFilter;
import com.xinshijie.framework.security.handle.AuthenticationEntryPointImpl;
import com.xinshijie.framework.security.handle.CustomAccessDeniedHandler;
import com.xinshijie.framework.security.handle.CustomAuthenticationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * spring security配置
 *
 * @author xinshijie
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {
    // 自定义用户认证逻辑
    @Autowired
    private UserDetailsService userDetailsService;

    // 认证失败处理类
    @Autowired
    private AuthenticationEntryPointImpl unauthorizedHandler;

    // 允许匿名访问的地址
    @Autowired
    private PermitAllUrlProperties permitAllUrl;

    // 强散列哈希加密实现
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Jwt认证过滤器
    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    // 身份验证管理器
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // 身份验证Bean
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authProvider;
    }

    // 自定义身份验证提供者
    @Bean
    public AuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    // 配置安全过滤链
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 允许匿名访问的URL
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        permitAllUrl.getUrls().forEach(url -> registry.requestMatchers(url).permitAll());

        // 配置HTTP安全过滤链
        http
                .httpBasic(httpBasic -> httpBasic.disable())
                .csrf(cors -> cors.disable())
                .formLogin(formLog -> formLog.disable())
                .logout(logout -> logout.permitAll().disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(accessDeniedHandler -> accessDeniedHandler.accessDeniedHandler(new CustomAccessDeniedHandler()))
                .authenticationProvider(authenticationProvider())
                //判断token是否合法
                .addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .authorizeRequests()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/login", "/logout").permitAll()
                .requestMatchers("/error").permitAll()
                .requestMatchers("/login", "/register", "/captchaImage", "/common/upload", "/common/uploadImage", "/wiki/**", "/tool/swagger").anonymous()
                .requestMatchers(HttpMethod.GET, "/", "/*.html", "/webjars/**", "/webjars/**", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js", "/profile/**").anonymous()
                .requestMatchers("/swagger-ui.html", "/doc.html", "/favicon.ico", "/v3/**", "/swagger-resources/**", "/druid/**", "/wiki/**").anonymous()
//                .requestMatchers("/**").hasAnyAuthority("token")
                .anyRequest().authenticated()
        ;
        return http.build();
    }
}
