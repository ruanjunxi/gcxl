package com.example.gcxl.controller;

import com.example.gcxl.controller.ex.*;
import com.example.gcxl.domain.ResultData;
import com.example.gcxl.domain.ReturnCode;
import com.example.gcxl.service.ex.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;

/**
 * @author Eason
 * @ClassName:BaseController
 * @data:2022/4/16 14:51
 * 异常处理类
 */
public class BaseController {
    @ExceptionHandler({SeviceException.class,FileUploadException.class,SessionIsEmptyException.class, ParseException.class,NullPointerException.class})
    public ResultData<Void>handleException(Throwable e){
        if(e instanceof UsernameDuplicatedException){
            return new ResultData<Void>(ReturnCode.RC101);
        }else if (e instanceof InsertException){
            return new ResultData<Void>(ReturnCode.RC102);
        }else if (e instanceof UserNotFoundException){
            return new ResultData<>(ReturnCode.RC201);
        }else if (e instanceof PasswordNotMatchException){
            return new ResultData<>(ReturnCode.RC202);
        }else if (e instanceof VerificationCodeErrorException){
            return new ResultData<>(ReturnCode.RC203);
        }else if (e instanceof UpdateException){
            return new ResultData<>(ReturnCode.RC204);
        }else if (e instanceof FileEmptyException){
            return new ResultData<>(ReturnCode.RC301);
        }else if (e instanceof FileSizeException){
            return new ResultData<>(ReturnCode.RC302);
        }else if (e instanceof FileStateException){
            return new ResultData<>(ReturnCode.RC303);
        }else if (e instanceof FileTypeException){
            return new ResultData<>(ReturnCode.RC304);
        }else if (e instanceof FileUploadIOException){
            return new ResultData<>(ReturnCode.RC305);
        }else if (e instanceof PurchasingContractNotFoundException){
            return new ResultData<>(ReturnCode.RC306);
        }else if (e instanceof ThereIsNoChangeInformationException){
            return new ResultData<>(ReturnCode.RC307);
        }else if (e instanceof SessionIsEmptyException){
            return new ResultData<>(ReturnCode.RC1000);
        }else if (e instanceof PurchasingContractFoundException){
            return new ResultData<>(ReturnCode.RC308);
        }else if (e instanceof PurchaseInvoiceDoesExistException){
            return new ResultData<>(ReturnCode.RC309);
        }else if (e instanceof PurchaseInvoiceDoesNotExistException){
            return new ResultData<>(ReturnCode.RC310);
        }else if (e instanceof PurchaseInvoiceDetailDoesExistException){
            return new ResultData<>(ReturnCode.RC311);
        }else if (e instanceof PurchaseInvoiceDetailDoesNotExistException){
            return new ResultData<>(ReturnCode.RC312);
        }else if (e instanceof PrivateIntAmountLackException){
            return new ResultData<>(ReturnCode.RC313);
        }else if (e instanceof InvoiceTooLargeException){
            return new ResultData<>(ReturnCode.RC314);
        }else if (e instanceof OutBoundIDExistException){
            return new ResultData<>(ReturnCode.RC401);
        }else if (e instanceof OutBoundIDNotExistException){
            return new ResultData<>(ReturnCode.RC402);
        }else if(e instanceof NullPointerException){
            return new ResultData<>(ReturnCode.RC998);
        }else if(e instanceof PurchasingContractAmountTooSmall){
            return new ResultData<>(ReturnCode.RC315);
        }else if (e instanceof TimeNullException){
            return new ResultData<>(ReturnCode.RC5001);
        }else if (e instanceof ContractBelongToNameHasExistException){
            return new ResultData<>(ReturnCode.RC2000);
        }else if (e instanceof SaleInvoiceDoesExistException){
            return new ResultData<>(ReturnCode.RC2010);
        }else if (e instanceof SaleContractNotFoundException){
            return new ResultData<>(ReturnCode.RC2011);
        }
        return new ResultData<>(ReturnCode.RC999);
    }

    protected final String getUsernameFromSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        return session.getAttribute("username").toString();
    }
    public final String getUsernameFromSession(HttpSession session) {
        Object username = session.getAttribute("username");
        if (username==null){
            throw new SessionIsEmptyException();
        }
        return session.getAttribute("username").toString();
    }
}
