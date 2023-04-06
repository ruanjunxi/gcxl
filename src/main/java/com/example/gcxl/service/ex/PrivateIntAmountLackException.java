package com.example.gcxl.service.ex;

/**
 * 采购数量小于出库数量加关联数量
 * @ClassName: PrivateIntAmountLackException
 * @author: Eason
 * @data: 2022/4/18 20:33
 */
public class PrivateIntAmountLackException extends SeviceException{
    public PrivateIntAmountLackException() {
        super();
    }

    public PrivateIntAmountLackException(String message) {
        super(message);
    }

    public PrivateIntAmountLackException(String message, Throwable cause) {
        super(message, cause);
    }

    public PrivateIntAmountLackException(Throwable cause) {
        super(cause);
    }

    protected PrivateIntAmountLackException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
