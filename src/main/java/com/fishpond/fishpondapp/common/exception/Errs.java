package com.fishpond.fishpondapp.common.exception;

import com.fishpond.fishpondapp.common.response.RCode;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;

/**
 * @Author: Zhucj
 * @Description: 参数校验
 * @Date: 2025/8/19 10:09
 */
public class Errs {
    /**
     * 集合为空时抛出错误异常
     */
    public static void listNotEmpty(Collection<?> lst, String msg, Object... msgArgs) {
        if (lst == null || lst.isEmpty()) {
            throw newError(msg, msgArgs);
        }
    }

    /**
     * str为空时抛出错误异常
     */
    public static void notBlank(String str, String msg, Object... msgArgs) {
        if (StringUtils.isBlank(str)) {
            throw newError(msg, msgArgs);
        }
    }

    /**
     * object为null时抛出错误异常
     */
    public static void notNull(Object object, String msg, Object... msgArgs) {
        if (object == null) {
            throw newError(msg, msgArgs);
        }
    }

    /**
     * object不为null时抛出错误异常
     */
    public static void isNull(Object object, String msg, Object... msgArgs) {
        if (object != null) {
            throw newError(msg, msgArgs);
        }
    }

    /**
     * flag为false时抛出错误异常
     */
    public static void requireTrue(boolean flag, String msg, Object... msgArgs) {
        if (!flag) {
            throw newError(msg, msgArgs);
        }
    }

    /**
     * 创建错误异常
     */
    public static Err newError(String msg, Object... msgArgs) {
        return new Err(RCode.ERROR, String.format(msg, msgArgs));
    }

    @SneakyThrows
    public static String getStackTrace(Throwable cause) {
        try (StringWriter stringWriter = new StringWriter(); PrintWriter printWriter = new PrintWriter(stringWriter, true)) {
            cause.printStackTrace(printWriter);
            return stringWriter.toString();
        }
    }
}
