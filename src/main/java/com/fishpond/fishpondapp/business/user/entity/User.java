package com.fishpond.fishpondapp.business.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

/**
 * 用户表 实体
 *
 * @author zhucj
 * @since 2025-08-30
*/
@Data
@Accessors(chain = true)
@Schema(description = "用户表")
@TableName("t_user")
public class User {
    /** 用户ID */
    @TableId
    @Schema(description = "用户ID")
    private String id;

    /** 微信OpenID */
    @Schema(description ="微信OpenID")
    private String openid;

    /** 微信UnionID */
    @Schema(description ="微信UnionID")
    private String unionid;

    /** 昵称 */
    @Schema(description ="用户名称")
    private String username;

    /** 手机号 */
    @Schema(description ="手机号")
    private String phone;

    /** 密码（可选，用于传统登录） */
    @Schema(description = "密码")
    private String password;

    /** 头像URL */
    @Schema(description ="头像URL")
    private String avatar;

    /** 是否删除 0否 1是 */
    @NotNull(message = "是否删除 0否 1是不能为null")
    @Schema(description ="是否删除 0否 1是")
    private Integer deleted;

    /** 会员等级 */
    @Schema(description ="会员等级")
    private Integer level;

    /** 积分 */
    @Schema(description ="积分")
    private Integer points;
}