package com.fishpond.fishpondapp.business.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

/**
 * 商品表 实体
 *
 * @author zhucj
 * @since 2025-08-25
*/
@Data
@Accessors(chain = true)
@Schema(description = "商品表")
@TableName("t_mall_product")
public class MallProduct {
    /** 商品ID */
    @TableId
    //@NotBlank(message = "商品ID不能为空")
    @Schema(description = "商品ID")
    private String id;

    /** 分类ID */
    @NotBlank(message = "分类ID不能为空")
    @Schema(description ="分类ID")
    private String categoryId;

    /** 商品主图 */
    @Schema(description ="商品主图")
    private String coverImage;

    /** 是否删除: 0未删除 1已删除 */
    @NotNull(message = "是否删除: 0未删除 1已删除不能为null")
    @Schema(description ="是否删除: 0未删除 1已删除")
    private Integer deleted;

    /** 商品描述 */
    @Schema(description ="商品描述")
    private String description;

    /** 商品名称 */
    @NotBlank(message = "商品名称不能为空")
    @Schema(description ="商品名称")
    private String name;

    /** 售价 */
    @NotNull(message = "售价不能为null")
    @Schema(description ="售价")
    private Double price;

    /** 状态: 1上架 0下架 */
    @Schema(description ="状态: 1上架 0下架")
    private Integer status;

    /** 库存数量 */
    @Schema(description ="库存数量")
    private Integer stock;
}