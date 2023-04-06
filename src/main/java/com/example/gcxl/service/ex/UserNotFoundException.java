package com.example.gcxl.service.ex;

/**
 * 用户不存在
 * @author Eason
 * @ClassName:UserNotFoundException
 * @data:2022/4/16 15:38
 * 用户名不存在异常
 */

public class UserNotFoundException extends SeviceException{
    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    protected UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
