package com.fishpond.fishpondapp.business.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Token响应VO
 *
 * @author zhucj
 * @since 2025-01-01
 */
@Data
@Schema(description = "Token响应VO")
public class TokenVO {

    @Schema(description = "访问令牌", example = "eyJhbGciOiJIUzI1NiJ9...")
    private String accessToken;

    @Schema(description = "刷新令牌", example = "eyJhbGciOiJIUzI1NiJ9...")
    private String refreshToken;

    @Schema(description = "令牌类型", example = "Bearer")
    private String tokenType;

    @Schema(description = "过期时间（秒）", example = "86400")
    private Long expiresIn;

    @Schema(description = "用户ID", example = "123456")
    private String userId;

    @Schema(description = "用户名", example = "admin")
    private String username;

    @Schema(description = "头像URL", example = "https://example.com/avatar.jpg")
    private String avatar;
}