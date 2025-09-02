package com.fishpond.fishpondapp.business.user.service.impl;

import com.fishpond.fishpondapp.business.user.entity.User;
import com.fishpond.fishpondapp.business.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * 自定义用户详情服务
 *
 * @author zhucj
 * @since 2025-01-01
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MyUserDetailServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("加载用户信息: {}", username);
        
        try {
            // 这里需要根据您的实际业务逻辑来查询用户
            // 由于您的User实体中没有password字段，这里假设通过openid或phone来查询
            User user = userService.findByUsername(username);
            
            if (user == null) {
                log.warn("用户不存在: {}", username);
                throw new UsernameNotFoundException("用户不存在: " + username);
            }

            // 创建用户详情对象
            // 注意：这里使用openid作为密码，实际项目中应该有专门的密码字段
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getId())
                    .password(user.getOpenid() != null ? user.getOpenid() : "N/A")
                    .authorities(Collections.singletonList(new SimpleGrantedAuthority("USER")))
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(user.getDeleted() != null && user.getDeleted() == 1)
                    .build();
                    
        } catch (Exception e) {
            log.error("加载用户信息失败: {}", e.getMessage(), e);
            throw new UsernameNotFoundException("加载用户信息失败", e);
        }
    }
}
