package com.fishpond.fishpondapp.config;

import com.fishpond.fishpondapp.security.filter.CaptchaFilter;
import com.fishpond.fishpondapp.security.filter.JwtAuthenticationFilter;
import com.fishpond.fishpondapp.security.handler.*;
import com.fishpond.fishpondapp.business.user.service.impl.MyUserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security配置类
 *
 * @author zhucj
 * @since 2025-01-01
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // 替代 @EnableGlobalMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final LoginFailureHandler loginFailureHandler;
    private final LoginSuccessHandler loginSuccessHandler;
    private final CaptchaFilter captchaFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtLogoutSuccessHandler jwtLogoutSuccessHandler;
    private final MyUserDetailServiceImpl myUserDetailService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // 白名单
    private static final String[] URL_WHITELIST = {
            "/login",
            "/logout",
            "/captcha",
            "/password",
            "/image/**",
            "/auth/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/api-docs/**",
            "/webjars/**",
            "/favicon.ico",
            "/error",
            "/actuator/**"
    };

    /**
     * Spring Security 6.x 推荐直接在配置类中定义 AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * 密码加密器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Security 核心配置
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 关闭 csrf
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())

                // 登录配置
                .formLogin(form -> form
                        .successHandler(loginSuccessHandler)
                        .failureHandler(loginFailureHandler)
                )
                // 登出配置
                .logout(logout -> logout
                        .logoutSuccessHandler(jwtLogoutSuccessHandler)
                )

                // 禁用 session（STATELESS）
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 拦截规则
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(URL_WHITELIST).permitAll()
                        .anyRequest().authenticated()
                )

                // 异常处理
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                )

                // 过滤器链：先验证码，再 JWT
                .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * 认证管理（配置 UserDetailsService + 密码加密器）
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        // 使用推荐的构造函数方式，直接传入 UserDetailsService
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(myUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
