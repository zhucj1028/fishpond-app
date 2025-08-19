package com.fishpond.fishpondapp.common.exception;

import com.fishpond.fishpondapp.common.response.RCode;
import lombok.Getter;

/**
 * @Author: Zhucj
 * @Description: 错误
 * @Date: 2025/8/19 10:10
 */
public class Err extends RuntimeException {
    @Getter
    private final RCode code;

    public Err(RCode code, String message) {
        super(message);
        this.code = code;
    }
}
