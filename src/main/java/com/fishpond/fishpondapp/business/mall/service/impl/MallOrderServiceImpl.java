package com.fishpond.fishpondapp.business.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishpond.fishpondapp.business.mall.entity.MallOrder;
import com.fishpond.fishpondapp.business.mall.mapper.MallOrderMapper;
import com.fishpond.fishpondapp.business.mall.service.MallOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 订单表 ServiceImpl
 *
 * @author zhucj
 * @since 2025-08-25
 */
@Service
@RequiredArgsConstructor
class MallOrderServiceImpl extends ServiceImpl<MallOrderMapper, MallOrder> implements MallOrderService {
}
