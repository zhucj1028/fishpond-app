package com.fishpond.fishpondapp.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: Zhucj
 * @Description: 统一返回结果类
 * @Date: 2025/8/19 10:00
 */
@Data
@Schema(title = "统一返回结果类")
public class R<T> {
    /**
     * 响应码
     */
    @Schema(description = "响应码")
    private int code = -1;
    /**
     * 响应信息
     */
    @Schema(description = "响应信息")
    private String msg;
    /**
     * 响应数据
     */
    @Schema(description = "响应数据")
    private T data;

    public static <T> R<T> ok() {
        return ok(null);
    }

    public static <T> R<T> ok(T data) {
        return ok(data, "OK");
    }

    public static <T> R<T> ok(T data, String msg) {
        return create(RCode.OK, data, msg);
    }

    public static R<Void> error(RCode code, String msg) {
        R<Void> r = new R<>();
        r.setCode(code.value);
        r.setMsg(msg);
        return r;
    }

    public static void requireOk(R<?> r, String msg) {
        if (r == null || r.getCode() != RCode.OK.value) {
            throw new RuntimeException(msg + "，响应结果：" + r);
        }
    }

    private static <T> R<T> create(RCode code, T data, String msg) {
        R<T> r = new R<>();
        r.setCode(code.value);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
