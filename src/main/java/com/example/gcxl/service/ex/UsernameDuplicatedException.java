package com.example.gcxl.service.ex;

/**
 * 用户名重复错误
 * @author Eason
 * @ClassName:UsernameDuplicatedException
 * @data:2022/4/15 20:45
 * 用户名被占用
 */
public class UsernameDuplicatedException extends SeviceException{
    public UsernameDuplicatedException() {
        super();
    }

    public UsernameDuplicatedException(String message) {
        super(message);
    }

    public UsernameDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameDuplicatedException(Throwable cause) {
        super(cause);
    }

    protected UsernameDuplicatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
