package com.fishpond.fishpondapp.business.activity.controller;

import com.fishpond.fishpondapp.business.activity.entity.ActivityEnroll;
import com.fishpond.fishpondapp.business.activity.service.ActivityEnrollService;
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
 * 活动报名表 Controller
 *
 * @author zhucj
 * @since 2025-08-25
 */
@Tag(name = "活动报名表")
@RequiredArgsConstructor
@RestController
@RequestMapping("/activity/enroll")
class ActivityEnrollController {
    private final ActivityEnrollService activityEnrollService;

    @Operation(summary = "按条件分页查询所有")
    @PostMapping("/v1/page")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<IPage<ActivityEnroll>> v1Page(@RequestBody Page<ActivityEnroll> page) {
        QueryWrapper<ActivityEnroll> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", DeletedConstants.FALSE);
        IPage<ActivityEnroll> result = activityEnrollService.page(page, queryWrapper);
        return R.ok(result);
    }

    @Operation(summary = "按条件查询所有")
    @PostMapping("/v1/listAll")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<List<ActivityEnroll>> v1ListAll(@RequestBody ActivityEnroll o) {
        o.setDeleted(DeletedConstants.FALSE);
        QueryWrapper<ActivityEnroll> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", DeletedConstants.FALSE);
        return R.ok(activityEnrollService.list(queryWrapper));
    }

    @Operation(summary = "按ID获取信息")
    @GetMapping("/v1/info/{id}")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<ActivityEnroll> v1Info(@PathVariable String id) {
        return R.ok(activityEnrollService.getById(id));
    }

    @Operation(summary = "按ID删除")
    @GetMapping("/v1/delete/{id}")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<Void> v1Delete(@PathVariable String id) {
        ActivityEnroll o = new ActivityEnroll();
        o.setId(id);
        o.setDeleted(DeletedConstants.TRUE);
        activityEnrollService.updateById(o);
        return R.ok();
    }

    @Operation(summary = "按ID修改")
    @PostMapping("/v1/modify")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<Void> v1Modify(@RequestBody @Validated ActivityEnroll o) {
        o.setDeleted(DeletedConstants.FALSE);
        activityEnrollService.updateById(o);
        return R.ok();
    }

    @Operation(summary = "新增")
    @PostMapping("/v1/add")
    @PreAuthorize("@atc.admin")//默认只有管理员能访问该接口。其他设置：@atc.admin、@atc.user、@atc.inside、@atc.hasAuthority('xxx')
    public R<Void> v1Add(@RequestBody @Validated ActivityEnroll o) {
        o.setId(IdUtil.randomUUID());
        o.setDeleted(DeletedConstants.FALSE);
        activityEnrollService.save(o);
        return R.ok();
    }
}