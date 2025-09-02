package com.fishpond.fishpondapp.business.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fishpond.fishpondapp.business.user.dto.WeChatLoginDTO;
import com.fishpond.fishpondapp.business.user.entity.User;

/**
 * 用户表 Service
 *
 * @author zhucj
 * @since 2025-08-30
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    User findByUsername(String username);

    /**
     * 根据微信OpenID查找用户
     *
     * @param openid 微信OpenID
     * @return 用户信息
     */
    User findByOpenid(String openid);

    /**
     * 根据手机号查找用户
     *
     * @param phone 手机号
     * @return 用户信息
     */
    User findByPhone(String phone);

    /**
     * 根据ID查找用户
     *
     * @param id 用户ID
     * @return 用户信息
     */
    User findById(String id);

    /**
     * 创建或更新微信用户
     *
     * @param wechatLoginDTO 微信登录信息
     * @return 用户信息
     */
    User createOrUpdateWechatUser(WeChatLoginDTO wechatLoginDTO);
}
