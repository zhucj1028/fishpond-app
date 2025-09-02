package com.fishpond.fishpondapp.business.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

/**
 * 商品分类表 实体
 *
 * @author zhucj
 * @since 2025-08-25
*/
@Data
@Accessors(chain = true)
@Schema(description = "商品分类表")
@TableName("t_mall_category")
public class MallCategory {
    /** 分类ID */
    @TableId
    //@NotBlank(message = "分类ID不能为空")
    @Schema(description = "分类ID")
    private String id;

    /** 是否删除: 0未删除 1已删除 */
    @NotNull(message = "是否删除: 0未删除 1已删除不能为null")
    @Schema(description ="是否删除: 0未删除 1已删除")
    private Integer deleted;

    /** 分类描述 */
    @Schema(description ="分类描述")
    private String description;

    /** 分类名称 */
    @NotBlank(message = "分类名称不能为空")
    @Schema(description ="分类名称")
    private String name;

    /** 排序值，越小越靠前 */
    @Schema(description ="排序值，越小越靠前")
    private Integer sort;
}