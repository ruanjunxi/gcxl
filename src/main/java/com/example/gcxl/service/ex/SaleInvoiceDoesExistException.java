package com.example.gcxl.service.ex;

/**
 * 采购发票已存在
 * @ClassName: PurchaseInvoiceDoesExistException
 * @author: Eason
 * @data: 2022/4/18 19:14
 */
public class SaleInvoiceDoesExistException extends SeviceException{
    public SaleInvoiceDoesExistException() {
        super();
    }

    public SaleInvoiceDoesExistException(String message) {
        super(message);
    }

    public SaleInvoiceDoesExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaleInvoiceDoesExistException(Throwable cause) {
        super(cause);
    }

    protected SaleInvoiceDoesExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
