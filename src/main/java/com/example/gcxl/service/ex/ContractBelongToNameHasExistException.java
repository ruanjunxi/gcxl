package com.example.gcxl.service.ex;

/**
 * 无要修改的信息
 * @ClassName: ThereIsNoChangeInformationException
 * @author: Eason
 * @data: 2022/4/18 14:38
 */
public class ContractBelongToNameHasExistException extends SeviceException{
    public ContractBelongToNameHasExistException() {
        super();
    }

    public ContractBelongToNameHasExistException(String message) {
        super(message);
    }

    public ContractBelongToNameHasExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContractBelongToNameHasExistException(Throwable cause) {
        super(cause);
    }

    protected ContractBelongToNameHasExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
