package com.fishpond.fishpondapp.codegen.core;

import lombok.SneakyThrows;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class IOUtil {
    /**
     * 从输入流中读取字符串，读取完成后将关闭输入流
     */
    @SneakyThrows
    public static String readString(InputStream stream) {
        try (Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
            StringBuilder sb = new StringBuilder();
            char[] buffer = new char[1024];
            int r = 0;
            while ((r = reader.read(buffer)) != -1) {
                sb.append(Arrays.copyOfRange(buffer, 0, r));
            }
            return sb.toString();
        }
    }

    /**
     * 从输入流中读取字节数组，读取完成后将关闭输入流
     */
    @SneakyThrows
    public static byte[] readBytes(InputStream stream) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); InputStream in = stream) {
            int r = 0;
            byte[] bytes = new byte[1024];
            while ((r = stream.read(bytes)) != -1) {
                bos.write(bytes, 0, r);
            }
            return bos.toByteArray();
        }
    }

    /**
     * 将字符串转为二进制流
     */
    public static ByteArrayInputStream asStream(String data) {
        return null == data ? null : new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
    }
}
