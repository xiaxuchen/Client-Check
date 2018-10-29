package com.cxyz.commons.utils.HttpUtil.exception;

import com.cxyz.commons.exception.BaseException;

/**
 * Created by 夏旭晨 on 2018/9/23.
 */

public class OKHttpException extends BaseException {
    public OKHttpException() {
    }

    public OKHttpException(String msg) {
        super(msg);
    }

    public OKHttpException(Throwable throwable) {
        super(throwable);
    }

    public OKHttpException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
