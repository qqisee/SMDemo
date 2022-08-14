package com.netsec.sm.config;

public interface GlobalErrorCodeConstants {

    ErrorCode SUCCESS = new ErrorCode(0, "成功");

    ErrorCode BAD_REQUEST = new ErrorCode(400, "请求参数不正确");

    ErrorCode INTERNAL_SERVER_ERROR = new ErrorCode(500, "请求过于频繁，请稍后再试");
}