package com.fishpond.fishpondapp.business.mall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

/**
 * 用户收货地址表 实体
 *
 * @author zhucj
 * @since 2025-08-25
*/
@Data
@Accessors(chain = true)
@Schema(description = "用户收货地址表")
@TableName("t_mall_address")
public class MallAddress {
    /** 地址ID */
    @TableId
    //@NotBlank(message = "地址ID不能为空")
    @Schema(description = "地址ID")
    private String id;

    /** 城市 */
    @Schema(description ="城市")
    private String city;

    /** 是否删除: 0未删除 1已删除 */
    @NotNull(message = "是否删除: 0未删除 1已删除不能为null")
    @Schema(description ="是否删除: 0未删除 1已删除")
    private Integer deleted;

    /** 详细地址 */
    @Schema(description ="详细地址")
    private String detail;

    /** 区县 */
    @Schema(description ="区县")
    private String district;

    /** 是否默认地址: 0否 1是 */
    @Schema(description ="是否默认地址: 0否 1是")
    private Integer isDefault;

    /** 收货人电话 */
    @NotBlank(message = "收货人电话不能为空")
    @Schema(description ="收货人电话")
    private String phone;

    /** 省份 */
    @Schema(description ="省份")
    private String province;

    /** 收货人姓名 */
    @NotBlank(message = "收货人姓名不能为空")
    @Schema(description ="收货人姓名")
    private String receiver;

    /** 用户ID */
    @NotBlank(message = "用户ID不能为空")
    @Schema(description ="用户ID")
    private String userId;
}