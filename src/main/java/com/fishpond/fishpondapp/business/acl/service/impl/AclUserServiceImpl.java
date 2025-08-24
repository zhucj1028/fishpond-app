package com.fishpond.fishpondapp.business.acl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishpond.fishpondapp.business.acl.entity.AclUser;
import com.fishpond.fishpondapp.business.acl.mapper.AclUserMapper;
import com.fishpond.fishpondapp.business.acl.service.AclUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 管理员用户表 ServiceImpl
 *
 * @author zhucj
 * @since 2025-08-24
 */
@Service
@RequiredArgsConstructor
class AclUserServiceImpl extends ServiceImpl<AclUserMapper, AclUser> implements AclUserService {
}
