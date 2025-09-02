package com.fishpond.fishpondapp.business.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fishpond.fishpondapp.business.user.entity.UserPermission;

import java.util.Collection;
/**
 * 角色权限关联表 Mapper
 *
 * @author zhucj
 * @since 2025-08-30
 */
public interface UserPermissionMapper extends BaseMapper<UserPermission> {
    //void insertList(Collection<UserPermission> list);
    //void insertOrUpdate(UserPermission o);
}
