package com.example.gcxl.service.ex;

/**
 * @author Eason
 * @ClassName:InsertException
 * @data:2022/4/15 20:47
 * 插入过程中产生的异常
 */

public class InsertException extends SeviceException{
    public InsertException() {
        super();
    }

    public InsertException(String message) {
        super(message);
    }

    public InsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertException(Throwable cause) {
        super(cause);
    }

    protected InsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
