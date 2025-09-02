package com.fishpond.fishpondapp.business.mall.controller;

import com.fishpond.fishpondapp.business.mall.entity.MallOrder;
import com.fishpond.fishpondapp.business.mall.service.MallOrderService;
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
 * 订单表 Controller
 *
 * @author zhucj
 * @since 2025-08-25
 */
@Tag(name = "订单表")
@RequiredArgsConstructor
@RestController
@RequestMapping("/mall/order")
class MallOrderController {
    private final MallOrderService mallOrderService;

    @Operation(summary = "按条件分页查询所有")
    @PostMapping("/v1/page")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<IPage<MallOrder>> v1Page(@RequestBody Page<MallOrder> page) {
        QueryWrapper<MallOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", DeletedConstants.FALSE);
        IPage<MallOrder> result = mallOrderService.page(page, queryWrapper);
        return R.ok(result);
    }

    @Operation(summary = "按条件查询所有")
    @PostMapping("/v1/listAll")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<List<MallOrder>> v1ListAll(@RequestBody MallOrder o) {
        o.setDeleted(DeletedConstants.FALSE);
        QueryWrapper<MallOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", DeletedConstants.FALSE);
        return R.ok(mallOrderService.list(queryWrapper));
    }

    @Operation(summary = "按ID获取信息")
    @GetMapping("/v1/info/{id}")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<MallOrder> v1Info(@PathVariable String id) {
        return R.ok(mallOrderService.getById(id));
    }

    @Operation(summary = "按ID删除")
    @GetMapping("/v1/delete/{id}")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<Void> v1Delete(@PathVariable String id) {
        MallOrder o = new MallOrder();
        o.setId(id);
        o.setDeleted(DeletedConstants.TRUE);
        mallOrderService.updateById(o);
        return R.ok();
    }

    @Operation(summary = "按ID修改")
    @PostMapping("/v1/modify")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<Void> v1Modify(@RequestBody @Validated MallOrder o) {
        o.setDeleted(DeletedConstants.FALSE);
        mallOrderService.updateById(o);
        return R.ok();
    }

    @Operation(summary = "新增")
    @PostMapping("/v1/add")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<Void> v1Add(@RequestBody @Validated MallOrder o) {
        o.setId(IdUtil.randomUUID());
        o.setDeleted(DeletedConstants.FALSE);
        mallOrderService.save(o);
        return R.ok();
    }
}