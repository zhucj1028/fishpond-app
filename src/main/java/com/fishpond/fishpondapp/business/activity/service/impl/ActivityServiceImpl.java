package com.fishpond.fishpondapp.business.activity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishpond.fishpondapp.business.activity.entity.Activity;
import com.fishpond.fishpondapp.business.activity.mapper.ActivityMapper;
import com.fishpond.fishpondapp.business.activity.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 活动表 ServiceImpl
 *
 * @author zhucj
 * @since 2025-08-25
 */
@Service
@RequiredArgsConstructor
class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {
}
