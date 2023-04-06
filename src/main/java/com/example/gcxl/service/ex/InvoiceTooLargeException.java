package com.example.gcxl.service.ex;

import org.apache.ibatis.annotations.Select;

/**
 * 采购发票金额大于合同金额
 */
public class InvoiceTooLargeException extends SeviceException {
    public InvoiceTooLargeException() {
        super();
    }

    public InvoiceTooLargeException(String message) {
        super(message);
    }

    public InvoiceTooLargeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvoiceTooLargeException(Throwable cause) {
        super(cause);
    }

    protected InvoiceTooLargeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
