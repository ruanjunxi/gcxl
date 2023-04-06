package com.example.gcxl.service.ex;

/**
 * @author Eason
 * @ClassName:PasswordNotMatchException
 * @data:2022/4/16 15:38
 * 密码错误异常
 */
public class PasswordNotMatchException extends SeviceException{
    public PasswordNotMatchException() {
        super();
    }

    public PasswordNotMatchException(String message) {
        super(message);
    }

    public PasswordNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotMatchException(Throwable cause) {
        super(cause);
    }

    protected PasswordNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
