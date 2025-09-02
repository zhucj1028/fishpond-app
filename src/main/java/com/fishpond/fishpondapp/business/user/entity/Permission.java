package com.fishpond.fishpondapp.business.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

/**
 * 权限表 实体
 *
 * @author zhucj
 * @since 2025-08-30
*/
@Data
@Accessors(chain = true)
@Schema(description = "权限表")
@TableName("t_permission")
public class Permission {
    /** 主键ID */
    @TableId
    //@NotBlank(message = "主键ID不能为空")
    @Schema(description = "主键ID")
    private String id;

    /** 是否删除 0否 1是 */
    @NotNull(message = "是否删除 0否 1是不能为null")
    @Schema(description ="是否删除 0否 1是")
    private Integer deleted;

    /** 权限描述 */
    @Schema(description ="权限描述")
    private String description;

    /** 权限编码（如 ACTIVITY:CREATE） */
    @NotBlank(message = "权限编码（如 ACTIVITY:CREATE）不能为空")
    @Schema(description ="权限编码（如 ACTIVITY:CREATE）")
    private String permissionCode;

    /** 权限名称（如 创建活动） */
    @NotBlank(message = "权限名称（如 创建活动）不能为空")
    @Schema(description ="权限名称（如 创建活动）")
    private String permissionName;
}