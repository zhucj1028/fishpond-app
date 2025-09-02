package com.fishpond.fishpondapp.business.user.service;

import com.fishpond.fishpondapp.business.user.dto.WeChatUserInfoDTO;

/**
 * 微信服务接口
 *
 * @author zhucj
 * @since 2025-01-01
 */
public interface WeChatService {

    /**
     * 通过code获取微信用户的openid和session_key
     *
     * @param code 微信授权码
     * @return 微信用户信息
     */
    WeChatUserInfoDTO getWeChatUserInfo(String code);

    /**
     * 获取微信用户详细信息（需要用户授权）
     *
     * @param code 微信授权码
     * @return 微信用户详细信息
     */
    WeChatUserInfoDTO getWeChatUserDetail(String code);
}