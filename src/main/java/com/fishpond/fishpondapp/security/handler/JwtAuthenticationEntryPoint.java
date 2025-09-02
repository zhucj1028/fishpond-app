package com.fishpond.fishpondapp.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fishpond.fishpondapp.common.response.R;
import com.fishpond.fishpondapp.common.response.RCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT认证入口点处理器
 * 处理未认证的请求
 *
 * @author zhucj
 * @since 2025-01-01
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                        AuthenticationException authException) throws IOException, ServletException {
        log.error("未认证的请求: {}", request.getRequestURI());

        // 返回未认证响应
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(
                R.error(RCode.BAD_TOKEN, "请先登录")
        ));
    }
}
