package com.fishpond.fishpondapp.business.acl.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotBlank;

/**
 * 管理员用户表 实体
 *
 * @author zhucj
 * @since 2025-08-24
*/
@Data
@Accessors(chain = true)
@Schema(description = "管理员用户表")
@TableName("t_acl_user")
public class AclUser {
    /** 主键ID */
    @TableId
    //@NotBlank(message = "主键ID不能为空")
    @Schema(description = "主键ID")
    private String id;

    /** 邮箱 */
    @Schema(description ="邮箱")
    private String email;

    /** 昵称 */
    @Schema(description ="昵称")
    private String nickname;

    /** 密码（加密存储） */
    @NotBlank(message = "密码（加密存储）不能为空")
    @Schema(description ="密码（加密存储）")
    private String password;

    /** 手机号 */
    @Schema(description ="手机号")
    private String phone;

    /** 状态 1启用 0禁用 */
    @Schema(description ="状态 1启用 0禁用")
    private Integer status;

    /** 管理员账号 */
    @NotBlank(message = "管理员账号不能为空")
    @Schema(description ="管理员账号")
    private String username;

    /** 删除 0未删除 1已删除 */
    @Schema(description ="删除 0未删除 1已删除")
    private Integer deleted;
}