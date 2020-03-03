package com.mini.store.demo.error;

public enum BusinessError implements CommonError {
    //GENERAL
    UNKNOWN_ERROR(00000, "未知错误"),
    PARAMETER_ERROR(00001, "参数不合法"),
    //USER
    USER_NOT_EXIST(10001, "用户不存在"),
    USER_ALREADY_EXIST(10002, "用户已存在"),
    USER_PASSWORD_ERROR(10003, "密码错误"),
    //
    NOT_A_REQUEST(20001, "不是一个请求"),
    NO_UID_IN_REQUEST_ATTR(20002, "请求属性没有UID"),
    ;
    private int code;
    private String msg;

    private BusinessError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public CommonError setError(String msg) {
        this.msg = msg;
        return this;
    }
}
