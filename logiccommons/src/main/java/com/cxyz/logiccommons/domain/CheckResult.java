package com.cxyz.logiccommons.domain;

public class CheckResult<T> {

    /**
     * 请求是否成功
     */
    private boolean success;

    /**
     * 请求的数据
     */
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    /**
     * 请求失败的错误信息
     */
    private String error;

    public CheckResult()
    {}


    public CheckResult(T data) {
        this.success = true;
        this.data = data;
    }

    public CheckResult(String error)
    {
        this.error = error;
        this.success = false;
    }


}
