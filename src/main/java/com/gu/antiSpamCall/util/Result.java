package com.gu.antiSpamCall.util;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;

import java.beans.Transient;

@SuppressWarnings("unused")
public class Result<T> {
    @ApiModelProperty("200 正常")
    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public Result<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode())
                .setMsg(ResultCodeEnum.SUCCESS.getMsg())
                .setData(data);
        return result;
    }

    public static <T> Result<T> code(ResultCodeEnum codeEnum) {
        Result<T> result = new Result<>();
        result.setMsg(codeEnum.getMsg());
        result.setCode(codeEnum.getCode());
        return result;
    }

    public Result() {
    }

    public Result(T data) {
        this.data = data;
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.msg = ResultCodeEnum.SUCCESS.getMsg();
    }

    public Result(T data, int code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    /**
     * 验证返回是否正常
     */
    @JSONField(serialize = false, deserialize = false)
    @Transient
    public void assertSuccess() {
        if (ResultCodeEnum.ERROR.getCode() == this.getCode()) {
            throw new RuntimeException(this.getCode() + this.getMsg());
        } else if (ResultCodeEnum.SUCCESS.getCode() != this.getCode()) {
            throw new RuntimeException(this.getCode() + this.getMsg());
        }
    }

}
