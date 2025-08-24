package com.fishpond.fishpondapp.business.acl.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotBlank;

/**
 * 用户角色关联表 实体
 *
 * @author zhucj
 * @since 2025-08-24
*/
@Data
@Accessors(chain = true)
@Schema(description = "用户角色关联表")
@TableName("t_acl_user_role")
public class AclUserRole {
    /** 主键ID */
    @TableId
    //@NotBlank(message = "主键ID不能为空")
    @Schema(description = "主键ID")
    private String id;

    /** 角色ID */
    @NotBlank(message = "角色ID不能为空")
    @Schema(description ="角色ID")
    private String roleId;

    /** 用户ID */
    @NotBlank(message = "用户ID不能为空")
    @Schema(description ="用户ID")
    private String userId;

    /** 删除 0未删除 1已删除 */
    @Schema(description ="删除 0未删除 1已删除")
    private Integer deleted;
}