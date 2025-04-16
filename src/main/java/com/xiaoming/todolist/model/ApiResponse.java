package com.xiaoming.todolist.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public ApiResponse() {
        this.code = 200; // 默认成功状态码
        this.msg = "操作成功";
    }

    public ApiResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ApiResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(String msg, T data) {
        return new ApiResponse<>(200, msg, data);
    }

    public static <T> ApiResponse<T> success(String msg) {
        return new ApiResponse<>(200, msg, null);
    }

    public static <T> ApiResponse<T> error(Integer code, String msg) {
        return new ApiResponse<>(code, msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
