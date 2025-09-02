package com.fishpond.fishpondapp.business.community.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

/**
 * 社区评论表 实体
 *
 * @author zhucj
 * @since 2025-08-25
*/
@Data
@Accessors(chain = true)
@Schema(description = "社区评论表")
@TableName("t_community_comment")
public class CommunityComment {
    /** 评论ID */
    @TableId
    //@NotBlank(message = "评论ID不能为空")
    @Schema(description = "评论ID")
    private String id;

    /** 评论内容 */
    @Schema(description ="评论内容")
    private String content;

    /** 是否删除: 0未删除 1已删除 */
    @NotNull(message = "是否删除: 0未删除 1已删除不能为null")
    @Schema(description ="是否删除: 0未删除 1已删除")
    private Integer deleted;

    /** 帖子ID */
    @NotBlank(message = "帖子ID不能为空")
    @Schema(description ="帖子ID")
    private String postId;

    /** 用户ID */
    @NotBlank(message = "用户ID不能为空")
    @Schema(description ="用户ID")
    private String userId;
}