package com.mini.store.demo.error;

public interface CommonError {
    public int getCode();
    public String getMsg();
    public CommonError setError(String msg);
}
