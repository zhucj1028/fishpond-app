package com.fishpond.fishpondapp.business.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishpond.fishpondapp.business.community.entity.CommunityComment;
import com.fishpond.fishpondapp.business.community.mapper.CommunityCommentMapper;
import com.fishpond.fishpondapp.business.community.service.CommunityCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 社区评论表 ServiceImpl
 *
 * @author zhucj
 * @since 2025-08-25
 */
@Service
@RequiredArgsConstructor
class CommunityCommentServiceImpl extends ServiceImpl<CommunityCommentMapper, CommunityComment> implements CommunityCommentService {
}
