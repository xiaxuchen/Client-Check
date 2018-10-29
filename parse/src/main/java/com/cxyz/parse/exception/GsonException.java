package com.cxyz.parse.exception;

/**
 * Created by xiaxuchen on 18-10-26.
 */

public class GsonException extends Exception {
    public GsonException() {
    }

    public GsonException(String message) {
        super(message);
    }

    public GsonException(String message, Throwable cause) {
        super(message, cause);
    }

    public GsonException(Throwable cause) {
        super(cause);
    }
}
