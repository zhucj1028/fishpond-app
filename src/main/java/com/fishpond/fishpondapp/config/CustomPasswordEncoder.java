package com.fishpond.fishpondapp.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 自定义密码编码器
 * 用于处理没有密码字段的用户认证场景
 *
 * @author zhucj
 * @since 2025-01-01
 */
@Slf4j
@Component
public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        // 对于没有密码字段的用户，直接返回原始值
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // 对于微信登录等场景，可能需要特殊处理
        // 这里简化处理，直接比较字符串
        if (rawPassword == null || encodedPassword == null) {
            return false;
        }
        
        // 如果encodedPassword是"N/A"，说明用户没有设置密码（如微信用户）
        if ("N/A".equals(encodedPassword)) {
            log.warn("用户未设置密码，无法进行密码验证");
            return false;
        }
        
        return rawPassword.toString().equals(encodedPassword);
    }
}
