package com.xiaoming.todolist.enums;

public enum AppHttpCodeEnum {
    SUCCESS(200,"操作成功"),
    FAILED(500,"操作失败"),
    VALIDATE_ERROR(500,"参数检验失败");

    private final int code;
    private final String msg;

    AppHttpCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "AppHttpCodeEnum{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
