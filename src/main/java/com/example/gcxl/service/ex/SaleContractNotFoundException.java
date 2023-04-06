package com.example.gcxl.service.ex;

/**
 * 采购发票不存在
 * @ClassName: PurchasingContractNotFoundException
 * @author: Eason
 * @data: 2022/4/17 16:05
 */
public class SaleContractNotFoundException extends SeviceException{
    public SaleContractNotFoundException() {
        super();
    }

    public SaleContractNotFoundException(String message) {
        super(message);
    }

    public SaleContractNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaleContractNotFoundException(Throwable cause) {
        super(cause);
    }

    protected SaleContractNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
