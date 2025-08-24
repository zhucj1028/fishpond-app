package com.fishpond.fishpondapp.business.acl.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotBlank;

/**
 * 权限表 实体
 *
 * @author zhucj
 * @since 2025-08-24
*/
@Data
@Accessors(chain = true)
@Schema(description = "权限表")
@TableName("t_acl_permission")
public class AclPermission {
    /** 主键ID */
    @TableId
    //@NotBlank(message = "主键ID不能为空")
    @Schema(description = "主键ID")
    private String id;

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

    /** 删除 0未删除 1已删除 */
    @Schema(description ="删除 0未删除 1已删除")
    private Integer deleted;


}