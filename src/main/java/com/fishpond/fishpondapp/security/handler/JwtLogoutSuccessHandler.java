package com.fishpond.fishpondapp.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fishpond.fishpondapp.common.response.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT登出成功处理器
 *
 * @author zhucj
 * @since 2025-01-01
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                               Authentication authentication) throws IOException, ServletException {
        log.info("用户登出成功: {}", authentication != null ? authentication.getName() : "未知用户");

        // 返回登出成功响应
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(R.ok(null, "登出成功")));
    }
}
