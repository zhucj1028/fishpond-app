package com.fishpond.fishpondapp.business.acl.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotBlank;

/**
 * 角色表 实体
 *
 * @author zhucj
 * @since 2025-08-24
*/
@Data
@Accessors(chain = true)
@Schema(description = "角色表")
@TableName("t_acl_role")
public class AclRole {
    /** 主键ID */
    @TableId
    //@NotBlank(message = "主键ID不能为空")
    @Schema(description = "主键ID")
    private String id;

    /** 角色描述 */
    @Schema(description ="角色描述")
    private String description;

    /** 角色名称 */
    @NotBlank(message = "角色名称不能为空")
    @Schema(description ="角色名称")
    private String roleName;

    /** 删除 0未删除 1已删除 */
    @Schema(description ="删除 0未删除 1已删除")
    private Integer deleted;
}