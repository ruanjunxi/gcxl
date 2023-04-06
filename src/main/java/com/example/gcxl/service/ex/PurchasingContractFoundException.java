package com.example.gcxl.service.ex;

/**
 * 采购合同已存在
 * @ClassName: PurchasingContractFoundException
 * @author: Eason
 * @data: 2022/4/18 19:09
 */
public class PurchasingContractFoundException extends SeviceException{
    public PurchasingContractFoundException() {
        super();
    }

    public PurchasingContractFoundException(String message) {
        super(message);
    }

    public PurchasingContractFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PurchasingContractFoundException(Throwable cause) {
        super(cause);
    }

    protected PurchasingContractFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
