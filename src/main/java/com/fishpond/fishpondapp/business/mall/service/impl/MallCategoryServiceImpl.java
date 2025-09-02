package com.fishpond.fishpondapp.business.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishpond.fishpondapp.business.mall.entity.MallCategory;
import com.fishpond.fishpondapp.business.mall.mapper.MallCategoryMapper;
import com.fishpond.fishpondapp.business.mall.service.MallCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 商品分类表 ServiceImpl
 *
 * @author zhucj
 * @since 2025-08-25
 */
@Service
@RequiredArgsConstructor
class MallCategoryServiceImpl extends ServiceImpl<MallCategoryMapper, MallCategory> implements MallCategoryService {
}
