package com.fishpond.fishpondapp.business.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishpond.fishpondapp.business.user.dto.WeChatLoginDTO;
import com.fishpond.fishpondapp.business.user.entity.User;
import com.fishpond.fishpondapp.business.user.mapper.UserMapper;
import com.fishpond.fishpondapp.business.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户表 ServiceImpl
 *
 * @author zhucj
 * @since 2025-08-30
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User findByUsername(String username) {
        log.info("根据用户名查找用户: {}", username);
        
        // 由于User实体中没有username字段，这里假设通过phone或openid来查找
        // 您可以根据实际业务需求调整查询逻辑
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhone, username)
                   .or()
                   .eq(User::getOpenid, username)
                   .eq(User::getDeleted, 0);
        
        return this.getOne(queryWrapper);
    }

    @Override
    public User findById(String id) {
        log.info("根据ID查找用户: {}", id);
        return this.getById(id);
    }

    @Override
    public User createOrUpdateWechatUser(WeChatLoginDTO wechatLoginDTO) {
        return null;
    }

    @Override
    public User findByOpenid(String openid) {
        log.info("根据微信OpenID查找用户: {}", openid);
        
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getOpenid, openid)
                   .eq(User::getDeleted, 0);
        
        return this.getOne(queryWrapper);
    }

    @Override
    public User findByPhone(String phone) {
        return this.getOne(new QueryWrapper<User>().eq("phone", phone));
    }
}
