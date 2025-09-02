package com.fishpond.fishpondapp.business.activity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishpond.fishpondapp.business.activity.entity.ActivityEnroll;
import com.fishpond.fishpondapp.business.activity.mapper.ActivityEnrollMapper;
import com.fishpond.fishpondapp.business.activity.service.ActivityEnrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 活动报名表 ServiceImpl
 *
 * @author zhucj
 * @since 2025-08-25
 */
@Service
@RequiredArgsConstructor
class ActivityEnrollServiceImpl extends ServiceImpl<ActivityEnrollMapper, ActivityEnroll> implements ActivityEnrollService {
}
