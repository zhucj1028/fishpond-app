package com.fishpond.fishpondapp.business.user.controller;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class WxLoginTest {

    public static void main(String[] args) {
        // 替换成你自己的 appid 和 secret
        String APPID = "wx63e24cb50e8af9da";
        String SECRET = "700ff2f6b919ac2c336e0958a8b0b406";

        // 在小程序端 wx.login() 获取到的临时 code
        String CODE = "0c3G5E100YQdTU1mcY200EJVZx2G5E10";

        // 拼接微信接口 URL
        String url = "https://api.weixin.qq.com/sns/jscode2session"
                + "?appid=" + APPID
                + "&secret=" + SECRET
                + "&js_code=" + CODE
                + "&grant_type=authorization_code";

        OkHttpClient client = new OkHttpClient();

        try {
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();

            String responseBody = response.body().string();
            System.out.println("微信返回原始数据: " + responseBody);

            // 解析 JSON
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> wxResult = mapper.readValue(responseBody, Map.class);

            System.out.println("openid: " + wxResult.get("openid"));
            System.out.println("session_key: " + wxResult.get("session_key"));
            System.out.println("unionid: " + wxResult.get("unionid"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
