package com.fishpond.fishpondapp.business.activity.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

/**
 * 活动报名表 实体
 *
 * @author zhucj
 * @since 2025-08-25
*/
@Data
@Accessors(chain = true)
@Schema(description = "活动报名表")
@TableName("t_activity_enroll")
public class ActivityEnroll {
    /** 报名ID */
    @TableId
    //@NotBlank(message = "报名ID不能为空")
    @Schema(description = "报名ID")
    private String id;

    /** 活动ID */
    @NotBlank(message = "活动ID不能为空")
    @Schema(description ="活动ID")
    private String activityId;

    /** 是否删除: 0未删除 1已删除 */
    @NotNull(message = "是否删除: 0未删除 1已删除不能为null")
    @Schema(description ="是否删除: 0未删除 1已删除")
    private Integer deleted;

    /** 支付状态: 0未支付 1已支付 */
    @Schema(description ="支付状态: 0未支付 1已支付")
    private Integer payStatus;

    /** 抽号/座位号 */
    @Schema(description ="抽号/座位号")
    private Integer seatNo;

    /** 票据二维码编号 */
    @Schema(description ="票据二维码编号")
    private String ticketCode;

    /** 用户ID */
    @NotBlank(message = "用户ID不能为空")
    @Schema(description ="用户ID")
    private String userId;
}