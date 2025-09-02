package com.fishpond.fishpondapp.business.user.controller;

import com.fishpond.fishpondapp.business.user.dto.PhoneLoginDTO;
import com.fishpond.fishpondapp.business.user.dto.WeChatLoginDTO;
import com.fishpond.fishpondapp.common.response.R;
import com.fishpond.fishpondapp.business.user.service.AuthService;
import com.fishpond.fishpondapp.business.user.vo.TokenVO;
import com.fishpond.fishpondapp.business.user.vo.WeChatLoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 认证控制器
 *
 * @author zhucj
 * @since 2025-01-01
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "认证管理", description = "用户认证相关接口")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "微信小程序登录", description = "微信小程序登录，支持两阶段登录：第一阶段检查用户是否存在，第二阶段完成授权注册")
    @PostMapping("/wechat/login")
    public R<WeChatLoginVO> wechatLogin(@Parameter(description = "微信登录信息") @RequestBody @Valid WeChatLoginDTO wechatLoginDTO) {
        log.info("微信登录请求: code={}, authorized={}", wechatLoginDTO.getCode(), wechatLoginDTO.getAuthorized());
        WeChatLoginVO loginVO = authService.wechatLogin(wechatLoginDTO);
        return R.ok(loginVO, "微信登录处理成功");
    }

    @Operation(summary = "手机号登录", description = "使用手机号和密码进行登录")
    @PostMapping("/phone/login")
    public R<TokenVO> phoneLogin(@Parameter(description = "手机号登录信息") @RequestBody @Valid PhoneLoginDTO phoneLoginDTO) {
        log.info("手机号登录请求: {}", phoneLoginDTO.getPhone());
        TokenVO tokenVO = authService.phoneLogin(phoneLoginDTO);
        return R.ok(tokenVO, "手机号登录成功");
    }

    @Operation(summary = "用户登出", description = "用户登出并清除认证信息")
    @PostMapping("/logout")
    public R<Void> logout(@Parameter(description = "JWT令牌") @RequestHeader("Authorization") String token) {
        log.info("用户登出请求");
        authService.logout(token);
        return R.ok(null, "登出成功");
    }

    @Operation(summary = "刷新令牌", description = "刷新JWT令牌")
    @PostMapping("/refresh")
    public R<TokenVO> refreshToken(@Parameter(description = "JWT令牌") @RequestHeader("Authorization") String token) {
        log.info("刷新令牌请求");
        TokenVO loginVO = authService.refreshToken(token);
        return R.ok(loginVO, "令牌刷新成功");
    }

    @Operation(summary = "获取当前用户信息", description = "获取当前登录用户的信息")
    @GetMapping("/me")
    public R<TokenVO> getCurrentUser(@Parameter(description = "JWT令牌") @RequestHeader("Authorization") String token) {
        log.info("获取当前用户信息");
        TokenVO loginVO = authService.getCurrentUser(token);
        return R.ok(loginVO, "获取成功");
    }
}
