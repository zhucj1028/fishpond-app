package com.fishpond.fishpondapp.business.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishpond.fishpondapp.business.user.entity.Role;
import com.fishpond.fishpondapp.business.user.mapper.RoleMapper;
import com.fishpond.fishpondapp.business.user.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 角色表 ServiceImpl
 *
 * @author zhucj
 * @since 2025-08-30
 */
@Service
@RequiredArgsConstructor
class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
