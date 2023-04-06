package com.example.gcxl.service.ex;

/**
 * 采购发票不存在
 * @ClassName: PurchasingContractNotFoundException
 * @author: Eason
 * @data: 2022/4/17 16:05
 */
public class PurchasingContractNotFoundException extends SeviceException{
    public PurchasingContractNotFoundException() {
        super();
    }

    public PurchasingContractNotFoundException(String message) {
        super(message);
    }

    public PurchasingContractNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PurchasingContractNotFoundException(Throwable cause) {
        super(cause);
    }

    protected PurchasingContractNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
