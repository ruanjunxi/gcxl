package com.example.gcxl.service.ex;

/**
 * @ClassName: PurchasingContractAmountTooSmall
 * @author: Eason
 * @data: 2022/4/27 20:19
 */
public class PurchasingContractAmountTooSmall extends SeviceException{
    public PurchasingContractAmountTooSmall() {
    }

    public PurchasingContractAmountTooSmall(String message) {
        super(message);
    }

    public PurchasingContractAmountTooSmall(String message, Throwable cause) {
        super(message, cause);
    }

    public PurchasingContractAmountTooSmall(Throwable cause) {
        super(cause);
    }

    public PurchasingContractAmountTooSmall(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
