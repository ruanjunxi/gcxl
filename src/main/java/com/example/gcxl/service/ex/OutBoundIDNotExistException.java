package com.example.gcxl.service.ex;

/**
 * @ClassName: OutBoundIDNotExistException
 * @author: Eason
 * @data: 2022/4/20 21:28
 */
public class OutBoundIDNotExistException extends SeviceException{
    public OutBoundIDNotExistException() {
        super();
    }

    public OutBoundIDNotExistException(String message) {
        super(message);
    }

    public OutBoundIDNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutBoundIDNotExistException(Throwable cause) {
        super(cause);
    }

    protected OutBoundIDNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
