package com.gu.antiSpamCall.util;

@SuppressWarnings("unused")
public enum ResultCodeEnum {
    SUCCESS(200, "success"),
    FAIL(400, "fail"),
    NOT_LOGGED(403, "not logged"),
    NOT_FOUND(404, "not found"),
    VALID_FAIL(450, "valid fail"),
    ERROR(500, "error"),
    HYSTRIX(700, "hystrix")
    ;

    private final int code;
    private final String msg;

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
