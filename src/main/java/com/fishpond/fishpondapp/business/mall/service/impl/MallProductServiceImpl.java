package com.fishpond.fishpondapp.business.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishpond.fishpondapp.business.mall.entity.MallProduct;
import com.fishpond.fishpondapp.business.mall.mapper.MallProductMapper;
import com.fishpond.fishpondapp.business.mall.service.MallProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 商品表 ServiceImpl
 *
 * @author zhucj
 * @since 2025-08-25
 */
@Service
@RequiredArgsConstructor
class MallProductServiceImpl extends ServiceImpl<MallProductMapper, MallProduct> implements MallProductService {
}
