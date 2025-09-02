package com.fishpond.fishpondapp.business.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

/**
 * 角色表 实体
 *
 * @author zhucj
 * @since 2025-08-30
*/
@Data
@Accessors(chain = true)
@Schema(description = "角色表")
@TableName("t_role")
public class Role {
    /** 主键ID */
    @TableId
    //@NotBlank(message = "主键ID不能为空")
    @Schema(description = "主键ID")
    private String id;

    /** 是否删除 0否 1是 */
    @NotNull(message = "是否删除 0否 1是不能为null")
    @Schema(description ="是否删除 0否 1是")
    private Integer deleted;

    /** 角色描述 */
    @Schema(description ="角色描述")
    private String description;

    /** 角色名称 */
    @NotBlank(message = "角色名称不能为空")
    @Schema(description ="角色名称")
    private String roleName;
}