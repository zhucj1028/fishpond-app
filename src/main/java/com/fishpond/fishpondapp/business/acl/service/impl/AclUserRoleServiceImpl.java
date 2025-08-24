package com.fishpond.fishpondapp.business.acl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishpond.fishpondapp.business.acl.entity.AclUserRole;
import com.fishpond.fishpondapp.business.acl.mapper.AclUserRoleMapper;
import com.fishpond.fishpondapp.business.acl.service.AclUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户角色关联表 ServiceImpl
 *
 * @author zhucj
 * @since 2025-08-24
 */
@Service
@RequiredArgsConstructor
class AclUserRoleServiceImpl extends ServiceImpl<AclUserRoleMapper, AclUserRole> implements AclUserRoleService {
}
