package com.example.gcxl.controller.ex;

/**
 * @ClassName: TimeNullException
 * @author: Eason
 * @data: 2022/5/5 20:51
 */
public class TimeNullException extends FileUploadException{
    public TimeNullException() {
        super();
    }

    public TimeNullException(String message) {
        super(message);
    }

    public TimeNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeNullException(Throwable cause) {
        super(cause);
    }

    protected TimeNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
