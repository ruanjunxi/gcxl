package com.example.gcxl.service.ex;

/**
 * 更新出现未知错误
 * @author Eason
 * @ClassName:UpdateException
 * @data:2022/4/16 17:56
 */
public class UpdateException extends SeviceException{
    public UpdateException() {
        super();
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }

    protected UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
