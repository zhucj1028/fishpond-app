package com.fishpond.fishpondapp.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码过滤器
 * 用于验证登录时的验证码
 *
 * @author zhucj
 * @since 2025-01-01
 */
@Slf4j
@Component
public class CaptchaFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // 只对登录请求进行验证码校验
        if ("/login".equals(request.getRequestURI()) && "POST".equals(request.getMethod())) {
            log.debug("验证码过滤器处理登录请求");
            // 这里可以添加验证码校验逻辑
            // 例如：验证session中的验证码与请求中的验证码是否一致
        }
        
        filterChain.doFilter(request, response);
    }
}
