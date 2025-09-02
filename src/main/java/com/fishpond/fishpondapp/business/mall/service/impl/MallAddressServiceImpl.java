package com.fishpond.fishpondapp.business.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishpond.fishpondapp.business.mall.entity.MallAddress;
import com.fishpond.fishpondapp.business.mall.mapper.MallAddressMapper;
import com.fishpond.fishpondapp.business.mall.service.MallAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户收货地址表 ServiceImpl
 *
 * @author zhucj
 * @since 2025-08-25
 */
@Service
@RequiredArgsConstructor
class MallAddressServiceImpl extends ServiceImpl<MallAddressMapper, MallAddress> implements MallAddressService {
}
