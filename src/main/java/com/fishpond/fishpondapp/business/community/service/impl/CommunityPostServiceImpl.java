package com.fishpond.fishpondapp.business.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishpond.fishpondapp.business.community.entity.CommunityPost;
import com.fishpond.fishpondapp.business.community.mapper.CommunityPostMapper;
import com.fishpond.fishpondapp.business.community.service.CommunityPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 社区帖子表 ServiceImpl
 *
 * @author zhucj
 * @since 2025-08-25
 */
@Service
@RequiredArgsConstructor
class CommunityPostServiceImpl extends ServiceImpl<CommunityPostMapper, CommunityPost> implements CommunityPostService {
}
