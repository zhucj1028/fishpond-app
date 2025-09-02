package com.fishpond.fishpondapp.business.mall.controller;

import com.fishpond.fishpondapp.business.mall.entity.MallCategory;
import com.fishpond.fishpondapp.business.mall.service.MallCategoryService;
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
 * 商品分类表 Controller
 *
 * @author zhucj
 * @since 2025-08-25
 */
@Tag(name = "商品分类表")
@RequiredArgsConstructor
@RestController
@RequestMapping("/mall/category")
class MallCategoryController {
    private final MallCategoryService mallCategoryService;

    @Operation(summary = "按条件分页查询所有")
    @PostMapping("/v1/page")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<IPage<MallCategory>> v1Page(@RequestBody Page<MallCategory> page) {
        QueryWrapper<MallCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", DeletedConstants.FALSE);
        IPage<MallCategory> result = mallCategoryService.page(page, queryWrapper);
        return R.ok(result);
    }

    @Operation(summary = "按条件查询所有")
    @PostMapping("/v1/listAll")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<List<MallCategory>> v1ListAll(@RequestBody MallCategory o) {
        o.setDeleted(DeletedConstants.FALSE);
        QueryWrapper<MallCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", DeletedConstants.FALSE);
        return R.ok(mallCategoryService.list(queryWrapper));
    }

    @Operation(summary = "按ID获取信息")
    @GetMapping("/v1/info/{id}")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<MallCategory> v1Info(@PathVariable String id) {
        return R.ok(mallCategoryService.getById(id));
    }

    @Operation(summary = "按ID删除")
    @GetMapping("/v1/delete/{id}")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<Void> v1Delete(@PathVariable String id) {
        MallCategory o = new MallCategory();
        o.setId(id);
        o.setDeleted(DeletedConstants.TRUE);
        mallCategoryService.updateById(o);
        return R.ok();
    }

    @Operation(summary = "按ID修改")
    @PostMapping("/v1/modify")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<Void> v1Modify(@RequestBody @Validated MallCategory o) {
        o.setDeleted(DeletedConstants.FALSE);
        mallCategoryService.updateById(o);
        return R.ok();
    }

    @Operation(summary = "新增")
    @PostMapping("/v1/add")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<Void> v1Add(@RequestBody @Validated MallCategory o) {
        o.setId(IdUtil.randomUUID());
        o.setDeleted(DeletedConstants.FALSE);
        mallCategoryService.save(o);
        return R.ok();
    }
}