package com.fishpond.fishpondapp.business.acl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishpond.fishpondapp.business.acl.entity.AclRolePermission;
import com.fishpond.fishpondapp.business.acl.mapper.AclRolePermissionMapper;
import com.fishpond.fishpondapp.business.acl.service.AclRolePermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 角色权限关联表 ServiceImpl
 *
 * @author zhucj
 * @since 2025-08-24
 */
@Service
@RequiredArgsConstructor
class AclRolePermissionServiceImpl extends ServiceImpl<AclRolePermissionMapper, AclRolePermission> implements AclRolePermissionService {
}
