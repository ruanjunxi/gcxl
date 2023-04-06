package com.example.gcxl.service.ex;

/**
 * 采购发票已存在
 * @ClassName: PurchaseInvoiceDoesExistException
 * @author: Eason
 * @data: 2022/4/18 19:14
 */
public class PurchaseInvoiceDoesExistException extends SeviceException{
    public PurchaseInvoiceDoesExistException() {
        super();
    }

    public PurchaseInvoiceDoesExistException(String message) {
        super(message);
    }

    public PurchaseInvoiceDoesExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public PurchaseInvoiceDoesExistException(Throwable cause) {
        super(cause);
    }

    protected PurchaseInvoiceDoesExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
