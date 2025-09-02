package com.fishpond.fishpondapp.business.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 微信登录请求DTO
 *
 * @author zhucj
 * @since 2025-01-01
 */
@Data
@Schema(description = "微信登录请求DTO")
public class WeChatLoginDTO {

    /** 微信授权码（必填，用于获取openid） */
    @NotBlank(message = "微信授权码不能为空")
    @Schema(description = "微信授权码", example = "wx_code_123456")
    private String code;

    /** 以下字段在第二阶段登录时使用（用户授权后） */
    @Schema(description = "微信OpenID", example = "wx_openid_123456")
    private String openid;

    @Schema(description = "微信UnionID", example = "wx_unionid_123456")
    private String unionid;

    @Schema(description = "昵称", example = "微信用户")
    private String username;

    @Schema(description = "头像URL", example = "https://example.com/avatar.jpg")
    private String avatar;

    @Schema(description = "手机号", example = "13800138000")
    private String phone;

    /** 是否为授权登录（第二阶段） */
    @Schema(description = "是否为授权登录", example = "false")
    private Boolean authorized = false;
}
