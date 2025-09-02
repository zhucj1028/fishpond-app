package com.fishpond.fishpondapp.business.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

/**
 * 订单项表 实体
 *
 * @author zhucj
 * @since 2025-08-25
*/
@Data
@Accessors(chain = true)
@Schema(description = "订单项表")
@TableName("t_mall_order_item")
public class MallOrderItem {
    /** 订单项ID */
    @TableId
    //@NotBlank(message = "订单项ID不能为空")
    @Schema(description = "订单项ID")
    private String id;

    /** 是否删除: 0未删除 1已删除 */
    @NotNull(message = "是否删除: 0未删除 1已删除不能为null")
    @Schema(description ="是否删除: 0未删除 1已删除")
    private Integer deleted;

    /** 订单ID */
    @NotBlank(message = "订单ID不能为空")
    @Schema(description ="订单ID")
    private String orderId;

    /** 单价(下单时) */
    @NotNull(message = "单价(下单时)不能为null")
    @Schema(description ="单价(下单时)")
    private Double price;

    /** 商品ID */
    @NotBlank(message = "商品ID不能为空")
    @Schema(description ="商品ID")
    private String productId;

    /** 商品图片快照 */
    @Schema(description ="商品图片快照")
    private String productImage;

    /** 商品名称快照 */
    @Schema(description ="商品名称快照")
    private String productName;

    /** 购买数量 */
    @NotNull(message = "购买数量不能为null")
    @Schema(description ="购买数量")
    private Integer quantity;
}