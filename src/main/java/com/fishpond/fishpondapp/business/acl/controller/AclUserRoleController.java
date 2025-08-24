package com.fishpond.fishpondapp.business.acl.controller;

import com.fishpond.fishpondapp.business.acl.entity.AclUserRole;
import com.fishpond.fishpondapp.business.acl.service.AclUserRoleService;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fishpond.fishpondapp.common.constant.DeletedConstants;
import com.fishpond.fishpondapp.common.response.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户角色关联表 Controller
 *
 * @author zhucj
 * @since 2025-08-24
 */
@Tag(name = "用户角色关联表")
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/user/role")
class AclUserRoleController {
    private final AclUserRoleService adminUserRoleService;

    @Operation(summary = "按条件分页查询所有")
    @PostMapping("/v1/page")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<IPage<AclUserRole>> v1Page(@RequestBody Page<AclUserRole> page) {
        QueryWrapper<AclUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", DeletedConstants.FALSE);
        IPage<AclUserRole> result = adminUserRoleService.page(page, queryWrapper);
        return R.ok(result);
    }

    @Operation(summary = "按条件查询所有")
    @PostMapping("/v1/listAll")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<List<AclUserRole>> v1ListAll(@RequestBody AclUserRole o) {
//        o.setDeleted(DeletedConstants.FALSE);
        QueryWrapper<AclUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", DeletedConstants.FALSE);
        return R.ok(adminUserRoleService.list(queryWrapper));
    }

    @Operation(summary = "按ID获取信息")
    @GetMapping("/v1/info/{id}")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<AclUserRole> v1Info(@PathVariable String id) {
        return R.ok(adminUserRoleService.getById(id));
    }

    @Operation(summary = "按ID删除")
    @GetMapping("/v1/delete/{id}")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<Void> v1Delete(@PathVariable String id) {
        AclUserRole o = new AclUserRole();
        o.setId(id);
//        o.setDeleted(DeletedConstants.TRUE);
        adminUserRoleService.updateById(o);
        return R.ok();
    }

    @Operation(summary = "按ID修改")
    @PostMapping("/v1/modify")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<Void> v1Modify(@RequestBody @Validated AclUserRole o) {
//        o.setDeleted(DeletedConstants.FALSE);
        adminUserRoleService.updateById(o);
        return R.ok();
    }

    @Operation(summary = "新增")
    @PostMapping("/v1/add")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<Void> v1Add(@RequestBody @Validated AclUserRole o) {
        o.setId(IdUtil.randomUUID());
//        o.setDeleted(DeletedConstants.FALSE);
        adminUserRoleService.save(o);
        return R.ok();
    }
}