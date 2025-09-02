package com.fishpond.fishpondapp.business.reservation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishpond.fishpondapp.business.reservation.entity.Reservation;
import com.fishpond.fishpondapp.business.reservation.mapper.ReservationMapper;
import com.fishpond.fishpondapp.business.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 钓位预约表 ServiceImpl
 *
 * @author zhucj
 * @since 2025-08-25
 */
@Service
@RequiredArgsConstructor
class ReservationServiceImpl extends ServiceImpl<ReservationMapper, Reservation> implements ReservationService {
}
