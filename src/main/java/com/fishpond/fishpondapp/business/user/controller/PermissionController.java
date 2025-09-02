package com.fishpond.fishpondapp.business.user.controller;

import com.fishpond.fishpondapp.business.user.entity.Permission;
import com.fishpond.fishpondapp.business.user.service.PermissionService;
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
 * 权限表 Controller
 *
 * @author zhucj
 * @since 2025-08-30
 */
@Tag(name = "权限表")
@RequiredArgsConstructor
@RestController
@RequestMapping("/permission")
class PermissionController {
    private final PermissionService permissionService;

    @Operation(summary = "按条件分页查询所有")
    @PostMapping("/v1/page")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<IPage<Permission>> v1Page(@RequestBody Page<Permission> page) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", DeletedConstants.FALSE);
        IPage<Permission> result = permissionService.page(page, queryWrapper);
        return R.ok(result);
    }

    @Operation(summary = "按条件查询所有")
    @PostMapping("/v1/listAll")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<List<Permission>> v1ListAll(@RequestBody Permission o) {
        o.setDeleted(DeletedConstants.FALSE);
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", DeletedConstants.FALSE);
        return R.ok(permissionService.list(queryWrapper));
    }

    @Operation(summary = "按ID获取信息")
    @GetMapping("/v1/info/{id}")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<Permission> v1Info(@PathVariable String id) {
        return R.ok(permissionService.getById(id));
    }

    @Operation(summary = "按ID删除")
    @GetMapping("/v1/delete/{id}")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<Void> v1Delete(@PathVariable String id) {
        Permission o = new Permission();
        o.setId(id);
        o.setDeleted(DeletedConstants.TRUE);
        permissionService.updateById(o);
        return R.ok();
    }

    @Operation(summary = "按ID修改")
    @PostMapping("/v1/modify")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<Void> v1Modify(@RequestBody @Validated Permission o) {
        o.setDeleted(DeletedConstants.FALSE);
        permissionService.updateById(o);
        return R.ok();
    }

    @Operation(summary = "新增")
    @PostMapping("/v1/add")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<Void> v1Add(@RequestBody @Validated Permission o) {
        o.setId(IdUtil.randomUUID());
        o.setDeleted(DeletedConstants.FALSE);
        permissionService.save(o);
        return R.ok();
    }
}