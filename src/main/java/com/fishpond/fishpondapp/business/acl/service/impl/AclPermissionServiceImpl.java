package com.fishpond.fishpondapp.business.acl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishpond.fishpondapp.business.acl.entity.AclPermission;
import com.fishpond.fishpondapp.business.acl.mapper.AclPermissionMapper;
import com.fishpond.fishpondapp.business.acl.service.AclPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 权限表 ServiceImpl
 *
 * @author zhucj
 * @since 2025-08-24
 */
@Service
@RequiredArgsConstructor
class AclPermissionServiceImpl extends ServiceImpl<AclPermissionMapper, AclPermission> implements AclPermissionService {
}
