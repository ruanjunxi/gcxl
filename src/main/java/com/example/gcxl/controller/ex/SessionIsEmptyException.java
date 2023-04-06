package com.example.gcxl.controller.ex;

/**
 * @ClassName: SessionIsEmptyException
 * @author: Eason
 * @data: 2022/4/18 14:42
 */
public class SessionIsEmptyException extends RuntimeException{
    public SessionIsEmptyException() {
        super();
    }

    public SessionIsEmptyException(String message) {
        super(message);
    }

    public SessionIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public SessionIsEmptyException(Throwable cause) {
        super(cause);
    }

    protected SessionIsEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
