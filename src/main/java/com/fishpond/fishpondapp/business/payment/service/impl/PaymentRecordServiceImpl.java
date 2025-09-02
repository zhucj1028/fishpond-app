package com.fishpond.fishpondapp.business.payment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishpond.fishpondapp.business.payment.entity.PaymentRecord;
import com.fishpond.fishpondapp.business.payment.mapper.PaymentRecordMapper;
import com.fishpond.fishpondapp.business.payment.service.PaymentRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 支付记录表 ServiceImpl
 *
 * @author zhucj
 * @since 2025-08-25
 */
@Service
@RequiredArgsConstructor
class PaymentRecordServiceImpl extends ServiceImpl<PaymentRecordMapper, PaymentRecord> implements PaymentRecordService {
}
