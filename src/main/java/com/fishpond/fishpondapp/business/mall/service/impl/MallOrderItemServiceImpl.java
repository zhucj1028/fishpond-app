package com.fishpond.fishpondapp.business.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishpond.fishpondapp.business.mall.entity.MallOrderItem;
import com.fishpond.fishpondapp.business.mall.mapper.MallOrderItemMapper;
import com.fishpond.fishpondapp.business.mall.service.MallOrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 订单项表 ServiceImpl
 *
 * @author zhucj
 * @since 2025-08-25
 */
@Service
@RequiredArgsConstructor
class MallOrderItemServiceImpl extends ServiceImpl<MallOrderItemMapper, MallOrderItem> implements MallOrderItemService {
}
