package com.cxyz.network.exception;

/**
 * Created by xiaxuchen on 18-10-26.
 * 网络异常时抛出的异常
 */
public class NetWorkException extends OKHttpException {
    public NetWorkException() {
    }

    public NetWorkException(String msg) {
        super(msg);
    }

    public NetWorkException(Throwable throwable) {
        super(throwable);
    }

    public NetWorkException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
