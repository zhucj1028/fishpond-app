package com.fishpond.fishpondapp.business.acl.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotBlank;

/**
 * 角色权限关联表 实体
 *
 * @author zhucj
 * @since 2025-08-24
*/
@Data
@Accessors(chain = true)
@Schema(description = "角色权限关联表")
@TableName("t_acl_role_permission")
public class AclRolePermission {
    /** 主键ID */
    @TableId
    //@NotBlank(message = "主键ID不能为空")
    @Schema(description = "主键ID")
    private String id;

    /** 权限ID */
    @NotBlank(message = "权限ID不能为空")
    @Schema(description ="权限ID")
    private String permissionId;

    /** 角色ID */
    @NotBlank(message = "角色ID不能为空")
    @Schema(description ="角色ID")
    private String roleId;

    /** 删除 0未删除 1已删除 */
    @Schema(description ="删除 0未删除 1已删除")
    private Integer deleted;
}