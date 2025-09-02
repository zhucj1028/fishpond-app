package com.fishpond.fishpondapp.business.reservation.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

/**
 * 钓位预约表 实体
 *
 * @author zhucj
 * @since 2025-08-25
*/
@Data
@Accessors(chain = true)
@Schema(description = "钓位预约表")
@TableName("t_reservation")
public class Reservation {
    /** 预约ID */
    @TableId
    //@NotBlank(message = "预约ID不能为空")
    @Schema(description = "预约ID")
    private String id;

    /** 是否删除: 0未删除 1已删除 */
    @NotNull(message = "是否删除: 0未删除 1已删除不能为null")
    @Schema(description ="是否删除: 0未删除 1已删除")
    private Integer deleted;

    /** 结束时间 */
    @NotBlank(message = "结束时间不能为空")
    @Schema(description ="结束时间")
    private String endTime;

    /** 钓位ID */
    @NotBlank(message = "钓位ID不能为空")
    @Schema(description ="钓位ID")
    private String spotId;

    /** 开始时间 */
    @NotBlank(message = "开始时间不能为空")
    @Schema(description ="开始时间")
    private String startTime;

    /** 状态: 0待支付 1已预约 2已取消 */
    @Schema(description ="状态: 0待支付 1已预约 2已取消")
    private Integer status;

    /** 用户ID */
    @NotBlank(message = "用户ID不能为空")
    @Schema(description ="用户ID")
    private String userId;
}