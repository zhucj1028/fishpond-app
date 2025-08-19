package com.fishpond.fishpondapp.common.response;

import com.fishpond.fishpondapp.common.exception.Errs;

/**
 * @Author: Zhucj
 * @Description: 响应枚举
 * @Date: 2025/8/19 10:08
 */
public enum RCode {
    OK(0, "成功"),
    ERROR(1, "失败"),
    BAD_TOKEN(2, "无效的令牌");

    public final int value;
    public final String name;

    RCode(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static RCode requireOf(Integer value) {
        RCode v = valueOf(value);
        Errs.notNull(v, "未知的响应码：%s", value);
        return v;
    }

    public static RCode valueOf(Integer value) {
        if (value != null) {
            for (RCode rCode : RCode.values()) {
                if (rCode.value == value) {
                    return rCode;
                }
            }
        }
        return null;
    }
}
