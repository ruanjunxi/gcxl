package com.example.gcxl.service.ex;

/**
 * 采购发票明细不存在
 * @ClassName: PurchaseInvoiceDetailDoesNotExistException
 * @author: Eason
 * @data: 2022/4/18 19:41
 */
public class PurchaseInvoiceDetailDoesNotExistException extends SeviceException{
    public PurchaseInvoiceDetailDoesNotExistException() {
        super();
    }

    public PurchaseInvoiceDetailDoesNotExistException(String message) {
        super(message);
    }

    public PurchaseInvoiceDetailDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public PurchaseInvoiceDetailDoesNotExistException(Throwable cause) {
        super(cause);
    }

    protected PurchaseInvoiceDetailDoesNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
