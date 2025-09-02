package com.fishpond.fishpondapp.security.filter;

import com.fishpond.fishpondapp.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import com.fishpond.fishpondapp.business.user.service.impl.MyUserDetailServiceImpl;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

/**
 * JWT认证过滤器
 *
 * @author zhucj
 * @since 2025-01-01
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final MyUserDetailServiceImpl userDetailsService;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    
    // 白名单路径配置，与 SecurityConfig 中保持一致
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

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        boolean shouldNotFilter = Arrays.stream(URL_WHITELIST)
                .anyMatch(pattern -> {
                    boolean matches = pathMatcher.match(pattern, path);
                    if (matches) {
                        log.debug("请求路径: {} 匹配白名单模式: {}, 跳过JWT过滤器", path, pattern);
                    }
                    return matches;
                });
        
        if (!shouldNotFilter) {
            log.debug("请求路径: {} 不匹配任何白名单模式, 将进行JWT认证", path);
        }
        return shouldNotFilter;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        try {
            String token = getJwtFromRequest(request);
            
            if (StringUtils.hasText(token) && jwtUtil.validateToken(token)) {
                String userId = jwtUtil.getUserIdFromToken(token);
                
                UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
                
                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.debug("用户 {} 已通过JWT认证", userId);
                }
            }
        } catch (Exception e) {
            log.error("JWT认证处理失败: {}", e.getMessage());
        }
        
        filterChain.doFilter(request, response);
    }

    /**
     * 从请求中获取JWT令牌
     *
     * @param request HTTP请求
     * @return JWT令牌
     */
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        return jwtUtil.extractTokenFromHeader(bearerToken);
    }
}
