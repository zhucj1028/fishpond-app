package com.fishpond.fishpondapp.business.user.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fishpond.fishpondapp.business.user.dto.WeChatUserInfoDTO;
import com.fishpond.fishpondapp.business.user.service.WeChatService;
import com.fishpond.fishpondapp.common.exception.Err;
import com.fishpond.fishpondapp.common.response.RCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 微信服务实现
 *
 * @author zhucj
 * @since 2025-01-01
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WeChatServiceImpl implements WeChatService {

    @Value("${wechat.appid:wx63e24cb50e8af9da}")
    private String appId;

    @Value("${wechat.secret:700ff2f6b919ac2c336e0958a8b0b406}")
    private String secret;

    private final OkHttpClient okHttpClient = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public WeChatUserInfoDTO getWeChatUserInfo(String code) {
        log.info("通过code获取微信用户信息: {}", code);
        
        try {
            // 拼接微信接口 URL
            String url = "https://api.weixin.qq.com/sns/jscode2session"
                    + "?appid=" + appId
                    + "&secret=" + secret
                    + "&js_code=" + code
                    + "&grant_type=authorization_code";

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            try (Response response = okHttpClient.newCall(request).execute()) {
                String responseBody = response.body().string();
                log.info("微信返回原始数据: {}", responseBody);

                // 解析 JSON
                Map<String, Object> wxResult = objectMapper.readValue(responseBody, Map.class);

                WeChatUserInfoDTO userInfo = new WeChatUserInfoDTO();
                userInfo.setOpenid((String) wxResult.get("openid"));
                userInfo.setUnionid((String) wxResult.get("unionid"));
                userInfo.setSessionKey((String) wxResult.get("session_key"));
                
                // 检查是否有错误
                if (wxResult.containsKey("errcode")) {
                    Integer errcode = (Integer) wxResult.get("errcode");
                    String errmsg = (String) wxResult.get("errmsg");
                    userInfo.setErrcode(errcode);
                    userInfo.setErrmsg(errmsg);
                    
                    if (errcode != 0) {
                        log.error("微信接口调用失败: errcode={}, errmsg={}", errcode, errmsg);
                        throw new Err(RCode.ERROR, "微信登录失败: " + errmsg);
                    }
                }

                log.info("获取微信用户信息成功: openid={}", userInfo.getOpenid());
                return userInfo;
            }
        } catch (Exception e) {
            log.error("调用微信接口失败: {}", e.getMessage(), e);
            throw new Err(RCode.ERROR, "微信登录失败");
        }
    }

    @Override
    public WeChatUserInfoDTO getWeChatUserDetail(String code) {
        // 这里可以实现获取用户详细信息的逻辑
        // 通常需要用户在小程序端授权后才能获取到详细信息
        // 目前先返回基础信息
        return getWeChatUserInfo(code);
    }
}