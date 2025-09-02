package com.fishpond.fishpondapp.business.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishpond.fishpondapp.business.user.entity.UserPermission;
import com.fishpond.fishpondapp.business.user.mapper.UserPermissionMapper;
import com.fishpond.fishpondapp.business.user.service.UserPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 角色权限关联表 ServiceImpl
 *
 * @author zhucj
 * @since 2025-08-30
 */
@Service
@RequiredArgsConstructor
class UserPermissionServiceImpl extends ServiceImpl<UserPermissionMapper, UserPermission> implements UserPermissionService {
}
