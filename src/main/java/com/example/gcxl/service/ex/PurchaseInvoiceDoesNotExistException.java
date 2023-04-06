package com.example.gcxl.service.ex;

/**
 * 采购发票不存在
 * @ClassName: PurchaseInvoiceDoesNotExistException
 * @author: Eason
 * @data: 2022/4/18 19:12
 */
public class PurchaseInvoiceDoesNotExistException extends SeviceException{
    public PurchaseInvoiceDoesNotExistException() {
        super();
    }

    public PurchaseInvoiceDoesNotExistException(String message) {
        super(message);
    }

    public PurchaseInvoiceDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public PurchaseInvoiceDoesNotExistException(Throwable cause) {
        super(cause);
    }

    protected PurchaseInvoiceDoesNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
