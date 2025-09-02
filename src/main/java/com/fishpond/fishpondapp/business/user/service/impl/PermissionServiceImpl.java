package com.fishpond.fishpondapp.business.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishpond.fishpondapp.business.user.entity.Permission;
import com.fishpond.fishpondapp.business.user.mapper.PermissionMapper;
import com.fishpond.fishpondapp.business.user.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 权限表 ServiceImpl
 *
 * @author zhucj
 * @since 2025-08-30
 */
@Service
@RequiredArgsConstructor
class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
}
