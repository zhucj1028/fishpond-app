package com.fishpond.fishpondapp.business.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishpond.fishpondapp.business.log.entity.t_fishing_log;
import com.fishpond.fishpondapp.business.log.mapper.t_fishing_logMapper;
import com.fishpond.fishpondapp.business.log.service.t_fishing_logService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 钓鱼日志表 ServiceImpl
 *
 * @author zhucj
 * @since 2025-08-25
 */
@Service
@RequiredArgsConstructor
class t_fishing_logServiceImpl extends ServiceImpl<t_fishing_logMapper, t_fishing_log> implements t_fishing_logService {
}
