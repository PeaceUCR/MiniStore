package com.mini.store.demo.error;

public class BusinessException extends Exception implements CommonError {
    private CommonError commonError;

    public BusinessException(CommonError commonError) {
        super();
        this.commonError = commonError;
    }
    //set error code with custom msg
    public BusinessException(CommonError commonError, String msg) {
        super();
        this.commonError = commonError;
        this.commonError.setError(msg);
    }

    @Override
    public int getCode() {
        return this.commonError.getCode();
    }

    @Override
    public String getMsg() {
        return this.commonError.getMsg();
    }

    @Override
    public CommonError setError(String msg) {
        this.commonError.setError(msg);
        return this;
    }
}
