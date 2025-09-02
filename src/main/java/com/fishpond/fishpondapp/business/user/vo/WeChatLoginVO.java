package com.fishpond.fishpondapp.business.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 微信登录响应VO
 *
 * @author zhucj
 * @since 2025-01-01
 */
@Data
@Schema(description = "微信登录响应VO")
public class WeChatLoginVO {

    @Schema(description = "是否为已注册用户", example = "true")
    private Boolean isRegistered;

    @Schema(description = "微信OpenID", example = "wx_openid_123456")
    private String openid;

    @Schema(description = "Token信息（已注册用户才有）")
    private TokenVO tokenInfo;

    @Schema(description = "提示信息", example = "用户未注册，需要授权登录")
    private String message;
}