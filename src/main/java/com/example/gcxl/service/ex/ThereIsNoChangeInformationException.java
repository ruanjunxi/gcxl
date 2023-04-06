package com.example.gcxl.service.ex;

/**
 * 无要修改的信息
 * @ClassName: ThereIsNoChangeInformationException
 * @author: Eason
 * @data: 2022/4/18 14:38
 */
public class ThereIsNoChangeInformationException extends SeviceException{
    public ThereIsNoChangeInformationException() {
        super();
    }

    public ThereIsNoChangeInformationException(String message) {
        super(message);
    }

    public ThereIsNoChangeInformationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ThereIsNoChangeInformationException(Throwable cause) {
        super(cause);
    }

    protected ThereIsNoChangeInformationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
