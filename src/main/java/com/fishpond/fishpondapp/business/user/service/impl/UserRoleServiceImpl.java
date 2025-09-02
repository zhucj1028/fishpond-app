package com.fishpond.fishpondapp.business.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishpond.fishpondapp.business.user.entity.UserRole;
import com.fishpond.fishpondapp.business.user.mapper.UserRoleMapper;
import com.fishpond.fishpondapp.business.user.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户角色关联表 ServiceImpl
 *
 * @author zhucj
 * @since 2025-08-30
 */
@Service
@RequiredArgsConstructor
class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
