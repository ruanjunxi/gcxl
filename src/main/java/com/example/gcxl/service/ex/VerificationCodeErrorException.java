package com.example.gcxl.service.ex;

/**
 * 验证码错误
 * @author Eason
 * @ClassName:VerificationCodeErrorException
 * @data:2022/4/16 15:56
 * 验证码错误异常
 */
public class VerificationCodeErrorException extends SeviceException{
    public VerificationCodeErrorException() {
        super();
    }

    public VerificationCodeErrorException(String message) {
        super(message);
    }

    public VerificationCodeErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public VerificationCodeErrorException(Throwable cause) {
        super(cause);
    }

    protected VerificationCodeErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
