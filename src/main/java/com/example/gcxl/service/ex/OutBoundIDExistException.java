package com.example.gcxl.service.ex;

/**
 * @ClassName: OutBoundIDExistException
 * @author: Eason
 * @data: 2022/4/20 9:57
 * 出库编号已存在
 */
public class OutBoundIDExistException extends SeviceException{
    public OutBoundIDExistException() {
        super();
    }

    public OutBoundIDExistException(String message) {
        super(message);
    }

    public OutBoundIDExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutBoundIDExistException(Throwable cause) {
        super(cause);
    }

    protected OutBoundIDExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
