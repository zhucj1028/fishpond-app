package com.fishpond.fishpondapp.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fishpond.fishpondapp.common.response.R;
import com.fishpond.fishpondapp.common.response.RCode;
import com.fishpond.fishpondapp.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录成功处理器
 *
 * @author zhucj
 * @since 2025-01-01
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                      Authentication authentication) throws IOException, ServletException {
        log.info("用户登录成功: {}", authentication.getName());

        // 生成JWT令牌
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails.getUsername(), userDetails.getUsername());

        // 构建响应数据
        Map<String, Object> data = new HashMap<>();
        data.put("accessToken", token);
        data.put("tokenType", "Bearer");
        data.put("expiresIn", 86400L);
        data.put("username", userDetails.getUsername());

        // 返回成功响应
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(R.ok(data, "登录成功")));
    }
}
