package com.example.gcxl.service.ex;

/**
 * 采购发票明细已存在
 * @ClassName: PurchaseInvoiceDetailDoesExistException
 * @author: Eason
 * @data: 2022/4/18 19:46
 */
public class PurchaseInvoiceDetailDoesExistException extends SeviceException{
    public PurchaseInvoiceDetailDoesExistException() {
        super();
    }

    public PurchaseInvoiceDetailDoesExistException(String message) {
        super(message);
    }

    public PurchaseInvoiceDetailDoesExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public PurchaseInvoiceDetailDoesExistException(Throwable cause) {
        super(cause);
    }

    protected PurchaseInvoiceDetailDoesExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
