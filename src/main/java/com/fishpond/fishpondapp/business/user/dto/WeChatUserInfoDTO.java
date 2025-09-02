package com.fishpond.fishpondapp.business.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 微信用户信息DTO
 *
 * @author zhucj
 * @since 2025-01-01
 */
@Data
@Schema(description = "微信用户信息DTO")
public class WeChatUserInfoDTO {

    @Schema(description = "微信OpenID", example = "wx_openid_123456")
    private String openid;

    @Schema(description = "微信UnionID", example = "wx_unionid_123456")
    private String unionid;

    @Schema(description = "会话密钥", example = "session_key_123456")
    private String sessionKey;

    @Schema(description = "昵称", example = "微信用户")
    private String nickname;

    @Schema(description = "头像URL", example = "https://example.com/avatar.jpg")
    private String avatarUrl;

    @Schema(description = "性别 0-未知 1-男 2-女", example = "1")
    private Integer gender;

    @Schema(description = "国家", example = "中国")
    private String country;

    @Schema(description = "省份", example = "广东")
    private String province;

    @Schema(description = "城市", example = "深圳")
    private String city;

    @Schema(description = "错误码", example = "0")
    private Integer errcode;

    @Schema(description = "错误信息", example = "ok")
    private String errmsg;
}