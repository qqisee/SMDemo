package com.netsec.sm.config;

import lombok.Data;

@Data
public class ErrorCode {
    private final Integer code;

    private final String msg;

    public ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}