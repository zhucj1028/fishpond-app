package com.fishpond.fishpondapp.business.payment.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

/**
 * 支付记录表 实体
 *
 * @author zhucj
 * @since 2025-08-25
*/
@Data
@Accessors(chain = true)
@Schema(description = "支付记录表")
@TableName("t_payment_record")
public class PaymentRecord {
    /** 支付记录ID */
    @TableId
    //@NotBlank(message = "支付记录ID不能为空")
    @Schema(description = "支付记录ID")
    private String id;

    /** 支付金额 */
    @NotNull(message = "支付金额不能为null")
    @Schema(description ="支付金额")
    private Double amount;

    /** 是否删除: 0未删除 1已删除 */
    @NotNull(message = "是否删除: 0未删除 1已删除不能为null")
    @Schema(description ="是否删除: 0未删除 1已删除")
    private Integer deleted;

    /** 订单/报名/预约ID */
    @Schema(description ="订单/报名/预约ID")
    private String orderId;

    /** 支付状态: 0待支付 1成功 2失败 */
    @Schema(description ="支付状态: 0待支付 1成功 2失败")
    private Integer status;

    /** 第三方支付单号 */
    @Schema(description ="第三方支付单号")
    private String transactionNo;

    /** 支付类型: activity/ mall/ reservation */
    @NotBlank(message = "支付类型: activity/ mall/ reservation不能为空")
    @Schema(description ="支付类型: activity/ mall/ reservation")
    private String type;

    /** 用户ID */
    @NotBlank(message = "用户ID不能为空")
    @Schema(description ="用户ID")
    private String userId;
}