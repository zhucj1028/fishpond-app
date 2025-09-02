package com.fishpond.fishpondapp.business.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

/**
 * 订单表 实体
 *
 * @author zhucj
 * @since 2025-08-25
*/
@Data
@Accessors(chain = true)
@Schema(description = "订单表")
@TableName("t_mall_order")
public class MallOrder {
    /** 订单ID */
    @TableId
    //@NotBlank(message = "订单ID不能为空")
    @Schema(description = "订单ID")
    private String id;

    /** 收货地址ID */
    @Schema(description ="收货地址ID")
    private String addressId;

    /** 取消时间 */
    @Schema(description ="取消时间")
    private String cancelTime;

    /** 是否删除: 0未删除 1已删除 */
    @NotNull(message = "是否删除: 0未删除 1已删除不能为null")
    @Schema(description ="是否删除: 0未删除 1已删除")
    private Integer deleted;

    /** 完成时间 */
    @Schema(description ="完成时间")
    private String finishTime;

    /** 支付时间 */
    @Schema(description ="支付时间")
    private String payTime;

    /** 发货时间 */
    @Schema(description ="发货时间")
    private String shipTime;

    /** 状态: 0待支付 1已支付 2已发货 3已完成 4已取消 */
    @Schema(description ="状态: 0待支付 1已支付 2已发货 3已完成 4已取消")
    private Integer status;

    /** 订单总金额 */
    @NotNull(message = "订单总金额不能为null")
    @Schema(description ="订单总金额")
    private Double totalAmount;

    /** 用户ID */
    @NotBlank(message = "用户ID不能为空")
    @Schema(description ="用户ID")
    private String userId;
}