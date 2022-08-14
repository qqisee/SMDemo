package com.netsec.sm.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Data
@Accessors(chain = true)
@SuppressWarnings("all")
public class R<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    /**
     * 状态码200 + 固定成功信息提示（’success‘）
     *
     * @return 实例R
     */
    public static R success() {
        return new R()
                .setCode(HttpStatus.OK.value())
                .setMsg("success");
    }

    /**
     * 状态码200 + 固定成功信息提示（’success‘）+ 自定义数据
     *
     * @param data 数据
     * @param <T>  回显数据泛型
     * @return 实例R
     */
    public static <T> R success(T data) {
        return success().setData(data);
    }

    /**
     * 状态码500 + 固定错误信息提示（'fail'）的失败响应
     *
     * @return 实例R
     */
    public static R fail() {
        return new R()
                .setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setMsg("fail");
    }

    /**
     * 状态码500 + 自定义错误信息的失败响应
     *
     * @param msg  错误信息
     * @return 实例R
     */
    public static <T> R fail(String msg) {
        return new R()
                .setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setMsg(msg);
    }

    /**
     * 状态码自定义 + 错误信息自定义的失败响应
     * {@link GlobalErrorCodeConstants}
     *
     * @param data 数据
     * @param msg  错误信息
     * @return 实例R
     */
    public static R fail(Integer errorCode, String msg) {
        return fail()
                .setCode(errorCode)
                .setMsg(msg);
    }

    /**
     * 自定义错误类封装的失败响应
     *
     * @param error 自定义错误类
     * @return 实例R
     * @see ErrorCode
     */
    public static R fail(ErrorCode error) {
        return fail()
                .setCode(error.getCode())
                .setMsg(error.getMsg());
    }
}