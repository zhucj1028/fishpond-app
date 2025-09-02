package com.fishpond.fishpondapp.business.user.service.impl;

import com.fishpond.fishpondapp.business.user.dto.PhoneLoginDTO;
import com.fishpond.fishpondapp.business.user.dto.WeChatLoginDTO;
import com.fishpond.fishpondapp.business.user.dto.WeChatUserInfoDTO;
import com.fishpond.fishpondapp.business.user.entity.User;
import com.fishpond.fishpondapp.business.user.service.UserService;
import com.fishpond.fishpondapp.business.user.service.WeChatService;
import com.fishpond.fishpondapp.common.constant.DeletedConstants;
import com.fishpond.fishpondapp.common.exception.Err;
import com.fishpond.fishpondapp.common.response.RCode;
import com.fishpond.fishpondapp.business.user.service.AuthService;
import com.fishpond.fishpondapp.business.user.vo.TokenVO;
import com.fishpond.fishpondapp.business.user.vo.WeChatLoginVO;
import com.fishpond.fishpondapp.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 认证服务实现
 *
 * @author zhucj
 * @since 2025-01-01
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final WeChatService weChatService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Value("${jwt.expiration:86400}")
    private Long expiration;

    @Override
    public WeChatLoginVO wechatLogin(WeChatLoginDTO wechatLoginDTO) {
        log.info("微信登录请求: code={}, authorized={}", wechatLoginDTO.getCode(), wechatLoginDTO.getAuthorized());
        
        try {
            WeChatLoginVO loginVO = new WeChatLoginVO();
            
            // 第一阶段：通过code获取openid
            if (!wechatLoginDTO.getAuthorized()) {
                return handleFirstStageLogin(wechatLoginDTO, loginVO);
            } 
            // 第二阶段：用户授权后的完整登录
            else {
                return handleSecondStageLogin(wechatLoginDTO, loginVO);
            }
            
        } catch (Exception e) {
            log.error("微信登录失败: {}", e.getMessage(), e);
            throw new Err(RCode.ERROR, "微信登录失败");
        }
    }

    /**
     * 处理第一阶段登录：检查用户是否存在
     */
    private WeChatLoginVO handleFirstStageLogin(WeChatLoginDTO wechatLoginDTO, WeChatLoginVO loginVO) {
        // 调用微信接口获取openid
        WeChatUserInfoDTO weChatUserInfo = weChatService.getWeChatUserInfo(wechatLoginDTO.getCode());
        
        if (!StringUtils.hasText(weChatUserInfo.getOpenid())) {
            throw new Err(RCode.ERROR, "获取微信用户信息失败");
        }
        
        loginVO.setOpenid(weChatUserInfo.getOpenid());
        
        // 查找用户是否已存在
        User existingUser = userService.findByOpenid(weChatUserInfo.getOpenid());
        
        if (existingUser != null) {
            // 用户已存在，直接登录
            loginVO.setIsRegistered(true);
            loginVO.setMessage("用户已存在，登录成功");
            
            // 生成Token
            TokenVO tokenVO = generateTokenVO(existingUser);
            loginVO.setTokenInfo(tokenVO);
            
            log.info("微信用户 {} 登录成功", weChatUserInfo.getOpenid());
        } else {
            // 用户不存在，需要授权注册
            loginVO.setIsRegistered(false);
            loginVO.setMessage("用户未注册，需要授权登录");
            
            log.info("微信用户 {} 未注册，需要授权", weChatUserInfo.getOpenid());
        }
        
        return loginVO;
    }

    /**
     * 处理第二阶段登录：用户授权后的完整注册登录
     */
    private WeChatLoginVO handleSecondStageLogin(WeChatLoginDTO wechatLoginDTO, WeChatLoginVO loginVO) {
        // 再次调用微信接口确认用户信息
        WeChatUserInfoDTO weChatUserInfo = weChatService.getWeChatUserDetail(wechatLoginDTO.getCode());
        
        if (!StringUtils.hasText(weChatUserInfo.getOpenid())) {
            throw new Err(RCode.ERROR, "获取微信用户信息失败");
        }
        
        loginVO.setOpenid(weChatUserInfo.getOpenid());
        
        // 查找或创建用户
        User user = userService.findByOpenid(weChatUserInfo.getOpenid());
        
        if (user == null) {
            // 创建新用户
            user = new User();
            user.setOpenid(weChatUserInfo.getOpenid());
            user.setUnionid(weChatUserInfo.getUnionid());
            user.setUsername(StringUtils.hasText(wechatLoginDTO.getUsername()) ? 
                           wechatLoginDTO.getUsername() : weChatUserInfo.getNickname());
            user.setAvatar(StringUtils.hasText(wechatLoginDTO.getAvatar()) ? 
                         wechatLoginDTO.getAvatar() : weChatUserInfo.getAvatarUrl());
            user.setPhone(wechatLoginDTO.getPhone());
            user.setDeleted(DeletedConstants.FALSE);
            user.setLevel(1);
            user.setPoints(0);
            
            userService.save(user);
            log.info("创建新微信用户: {}", weChatUserInfo.getOpenid());
        } else {
            // 更新用户信息
            if (StringUtils.hasText(wechatLoginDTO.getUsername())) {
                user.setUsername(wechatLoginDTO.getUsername());
            }
            if (StringUtils.hasText(wechatLoginDTO.getAvatar())) {
                user.setAvatar(wechatLoginDTO.getAvatar());
            }
            if (StringUtils.hasText(wechatLoginDTO.getPhone())) {
                user.setPhone(wechatLoginDTO.getPhone());
            }
            userService.updateById(user);
            log.info("更新微信用户信息: {}", weChatUserInfo.getOpenid());
        }
        
        // 设置登录成功信息
        loginVO.setIsRegistered(true);
        loginVO.setMessage("授权登录成功");
        
        // 生成Token
        TokenVO tokenVO = generateTokenVO(user);
        loginVO.setTokenInfo(tokenVO);
        
        log.info("微信用户 {} 授权登录成功", weChatUserInfo.getOpenid());
        return loginVO;
    }

    /**
     * 生成TokenVO
     */
    private TokenVO generateTokenVO(User user) {
        String token = jwtUtil.generateToken(user.getId(), user.getUsername());
        
        TokenVO tokenVO = new TokenVO();
        tokenVO.setAccessToken(token);
        tokenVO.setTokenType("Bearer");
        tokenVO.setExpiresIn(expiration);
        tokenVO.setUserId(user.getId());
        tokenVO.setUsername(user.getUsername());
        tokenVO.setAvatar(user.getAvatar());
        
        return tokenVO;
    }

    @Override
    public TokenVO phoneLogin(PhoneLoginDTO phoneLoginDTO) {
        log.info("手机号登录: {}", phoneLoginDTO.getPhone());
        
        try {
            // 1. 根据手机号查找用户
            User user = userService.findByPhone(phoneLoginDTO.getPhone());
            if (user == null) {
                throw new Err(RCode.ERROR, "用户不存在");
            }
            
            // 2. 验证密码
            if (!passwordEncoder.matches(phoneLoginDTO.getPassword(), user.getPassword())) {
                throw new Err(RCode.ERROR, "手机号或密码错误");
            }
            
            // 3. 生成JWT令牌
            String accessToken = jwtUtil.generateToken(user.getId(), user.getUsername());
            String refreshToken = jwtUtil.generateRefreshToken(user.getId());
            
            // 4. 封装TokenVO返回
            TokenVO tokenVO = new TokenVO();
            tokenVO.setAccessToken(accessToken);
            tokenVO.setRefreshToken(refreshToken);
            tokenVO.setTokenType("Bearer");
            tokenVO.setExpiresIn(expiration);
            tokenVO.setUserId(user.getId());
            tokenVO.setUsername(user.getUsername());
            tokenVO.setAvatar(user.getAvatar());
            
            log.info("手机号用户 {} 登录成功", phoneLoginDTO.getPhone());
            return tokenVO;
            
        } catch (Err e) {
            throw e;
        } catch (Exception e) {
            log.error("手机号登录失败: {}", e.getMessage(), e);
            throw new Err(RCode.ERROR, "登录失败");
        }
    }

    @Override
    public void logout(String token) {
        log.info("用户登出");
        // 清除安全上下文
        SecurityContextHolder.clearContext();
        // 这里可以添加token黑名单逻辑
    }

    @Override
    public TokenVO refreshToken(String token) {
        log.info("刷新令牌");
        
        try {
            // 验证当前令牌
            if (!jwtUtil.validateToken(token)) {
                throw new Err(RCode.BAD_TOKEN, "令牌已过期或无效");
            }
            
            // 获取用户信息
            String userId = jwtUtil.getUserIdFromToken(token);
            String username = jwtUtil.getUsernameFromToken(token);
            
            // 生成新令牌
            String newToken = jwtUtil.generateToken(userId, username);
            
            // 获取用户信息
            User user = userService.findById(userId);
            if (user == null) {
                throw new Err(RCode.ERROR, "用户信息不存在");
            }
            
            // 构建登录响应
            TokenVO loginVO = new TokenVO();
            loginVO.setAccessToken(newToken);
            loginVO.setTokenType("Bearer");
            loginVO.setExpiresIn(expiration);
            loginVO.setUserId(user.getId());
            loginVO.setUsername(username);
            loginVO.setAvatar(user.getAvatar());
            
            log.info("令牌刷新成功");
            return loginVO;
            
        } catch (Exception e) {
            log.error("令牌刷新失败: {}", e.getMessage());
            throw new Err(RCode.BAD_TOKEN, "令牌刷新失败");
        }
    }

    @Override
    public TokenVO getCurrentUser(String token) {
        return null;
    }
}
