package com.fishpond.fishpondapp.business.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 手机号登录请求DTO
 *
 * @author zhucj
 * @since 2025-01-01
 */
@Data
@Schema(description = "手机号登录请求DTO")
public class PhoneLoginDTO {

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    @Schema(description = "手机号", example = "13800138000")
    private String phone;

    @NotBlank(message = "密码不能为空")
    @Schema(description = "密码", example = "123456")
    private String password;

//    @NotBlank(message = "验证码不能为空")
//    @Schema(description = "短信验证码", example = "123456")
//    private String code;
}