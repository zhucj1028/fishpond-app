package com.fishpond.fishpondapp.business.activity.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

/**
 * 活动表 实体
 *
 * @author zhucj
 * @since 2025-08-25
*/
@Data
@Accessors(chain = true)
@Schema(description = "活动表")
@TableName("t_activity")
public class Activity {
    /** 活动ID */
    @TableId
    //@NotBlank(message = "活动ID不能为空")
    @Schema(description = "活动ID")
    private String id;

    /** 发布人(管理员/商户ID) */
    @Schema(description ="发布人(管理员/商户ID)")
    private Long createdBy;

    /** 是否删除: 0未删除 1已删除 */
    @NotNull(message = "是否删除: 0未删除 1已删除不能为null")
    @Schema(description ="是否删除: 0未删除 1已删除")
    private Integer deleted;

    /** 活动介绍 */
    @Schema(description ="活动介绍")
    private String description;

    /** 结束时间 */
    @NotBlank(message = "结束时间不能为空")
    @Schema(description ="结束时间")
    private String endTime;

    /** 涉及鱼种 */
    @Schema(description ="涉及鱼种")
    private String fishTypes;

    /** 活动地点 */
    @Schema(description ="活动地点")
    private String location;

    /** 人数上限 */
    @Schema(description ="人数上限")
    private Integer maxParticipants;

    /** 票价 */
    @NotNull(message = "票价不能为null")
    @Schema(description ="票价")
    private Double price;

    /** 活动规则 */
    @Schema(description ="活动规则")
    private String rule;

    /** 开始时间 */
    @NotBlank(message = "开始时间不能为空")
    @Schema(description ="开始时间")
    private String startTime;

    /** 活动标题 */
    @NotBlank(message = "活动标题不能为空")
    @Schema(description ="活动标题")
    private String title;
}