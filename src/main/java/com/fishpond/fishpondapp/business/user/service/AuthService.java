package com.fishpond.fishpondapp.business.user.service;

import com.fishpond.fishpondapp.business.user.dto.PhoneLoginDTO;
import com.fishpond.fishpondapp.business.user.dto.WeChatLoginDTO;
import com.fishpond.fishpondapp.business.user.vo.TokenVO;
import com.fishpond.fishpondapp.business.user.vo.WeChatLoginVO;

/**
 * 认证服务接口
 *
 * @author zhucj
 * @since 2025-01-01
 */
public interface AuthService {

    /**
     * 微信小程序登录（支持两阶段登录）
     *
     * @param wechatLoginDTO 微信登录请求
     * @return 登录响应
     */
    WeChatLoginVO wechatLogin(WeChatLoginDTO wechatLoginDTO);

    /**
     * 手机号登录
     * @param phoneLoginDTO 手机号登录请求
     * @return Token信息
     */
    TokenVO phoneLogin(PhoneLoginDTO phoneLoginDTO);

    /**
     * 用户登出
     *
     * @param token JWT令牌
     */
    void logout(String token);

    /**
     * 刷新令牌
     *
     * @param token 当前令牌
     * @return 新的登录响应
     */
    TokenVO refreshToken(String token);

    /**
     * 获取当前用户信息
     *
     * @param token JWT令牌
     * @return 用户信息
     */
    TokenVO getCurrentUser(String token);
}
