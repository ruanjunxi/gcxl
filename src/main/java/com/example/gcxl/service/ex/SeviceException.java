package com.example.gcxl.service.ex;

/**
 * @author Eason
 * @ClassName:SeviceException
 * @data:2022/4/15 20:42
 * 业务层异常的基类
 */

public class SeviceException extends RuntimeException{
    public SeviceException() {
        super();
    }

    public SeviceException(String message) {
        super(message);
    }

    public SeviceException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeviceException(Throwable cause) {
        super(cause);
    }

    protected SeviceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
