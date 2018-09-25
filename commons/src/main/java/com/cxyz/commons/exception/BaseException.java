package com.cxyz.commons.exception;

/**
 * Created by 夏旭晨 on 2018/9/23.
 */

public class BaseException extends Exception {

    public BaseException(){super();}

    public BaseException(String msg){super(msg);}

    public BaseException(Throwable throwable){super(throwable);}

    public BaseException(String msg,Throwable throwable){super(msg,throwable);}

}
