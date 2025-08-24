package com.fishpond.fishpondapp.business.acl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishpond.fishpondapp.business.acl.entity.AclRole;
import com.fishpond.fishpondapp.business.acl.mapper.AclRoleMapper;
import com.fishpond.fishpondapp.business.acl.service.AclRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 角色表 ServiceImpl
 *
 * @author zhucj
 * @since 2025-08-24
 */
@Service
@RequiredArgsConstructor
class AclRoleServiceImpl extends ServiceImpl<AclRoleMapper, AclRole> implements AclRoleService {
}
