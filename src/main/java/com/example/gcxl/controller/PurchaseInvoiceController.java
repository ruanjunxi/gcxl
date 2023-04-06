package com.example.gcxl.controller;

import com.example.gcxl.domain.*;
import com.example.gcxl.service.PurchaseInvoiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Api("采购发票接口")
@RequestMapping("purchaseinvoice")
@Transactional(rollbackFor=Exception.class)

public class PurchaseInvoiceController extends BaseController {
    @Autowired
    private PurchaseInvoiceService purchaseInvoiceService;

    @Transactional(rollbackFor=Exception.class)
    @PostMapping("/insertPurchaseInvoice")
    @ApiOperation("插入采购发票")
    public ResultData<Void> insertPurchaseInvoice(@ApiParam("采购发票有明细")@RequestBody PurchaseInvoiceHaveDetails purchaseInvoiceHaveDetails, HttpSession session) {
        if (purchaseInvoiceHaveDetails==null){
            throw new NullPointerException("传入的数据存在空值");
        }
        System.out.println(purchaseInvoiceHaveDetails);
        PurchaseInvoice purchaseInvoice = purchaseInvoiceHaveDetails.getPurchaseInvoice();
        System.out.println(purchaseInvoice.getNumberAttachments());
        List<PurchaseInvoiceDetail> purchaseInvoiceDetailList = purchaseInvoiceHaveDetails.getPurchaseInvoiceDetailList();
//        String account=getUsernameFromSession(session);
        String account="eason";
        purchaseInvoice.setAccount(account);
        purchaseInvoiceService.insertPurchaseInvoice(purchaseInvoice);
//        批量插入
        purchaseInvoiceService.bulkInsertPurchasingInvoiceDetail(purchaseInvoiceDetailList);
        return new ResultData<>(ReturnCode.RC100);
    }
    @GetMapping("/updatePurchaseInvoice")
    @ApiOperation("修改采购发票")
    @Transactional(rollbackFor=Exception.class)
    public ResultData<Void> updatePurchaseInvoice(@ApiParam("新发票id")@RequestParam(required = false) String newInvoiceID,
                                                  @ApiParam("新发票时间")@RequestParam(required = false)String newMakeOutAnInvoiceDate,
                                                  @ApiParam("新发票类型")@RequestParam(required = false)String newInvoiceType,
                                                  @ApiParam("原发票id")@RequestParam(required = false)String oldInvoiceID,HttpSession session){
        if (newInvoiceID==null||newMakeOutAnInvoiceDate==null|| newInvoiceType==null|| oldInvoiceID==null){
            throw new NullPointerException("传入的数据存在空值");
        }
        //        String account=getUsernameFromSession(session);
        String account="eason";
        purchaseInvoiceService.updatePurchaseInvoice(newInvoiceID,newMakeOutAnInvoiceDate,newInvoiceType,oldInvoiceID,
                account);
        return new ResultData<>(ReturnCode.RC100);
    }
    @PostMapping("/updatePurchaseInvoiceDetail")
    @ApiOperation("修改采购发票明细")
    @Transactional(rollbackFor=Exception.class)
    public ResultData<Void> updatePurchaseInvoiceDetail(@ApiParam("合同明细类")@RequestBody PurchaseInvoiceDetail purchaseInvoiceDetail,HttpSession session){
        if (purchaseInvoiceDetail==null){
            throw new NullPointerException("传入的数据存在空值");
        }
        //        String account=getUsernameFromSession(session);
        String account="eason";
        purchaseInvoiceService.updatePurchaseInvoiceDetail(purchaseInvoiceDetail,account);
        return new ResultData<>(ReturnCode.RC100);
    }
    @GetMapping("/getAllPurchaseInvoiceWithDetail")
    @ApiOperation("显示对应合同id下的采购发票及其明细")
    @Transactional(rollbackFor=Exception.class)
    public ResultData<List<PurchaseInvoiceHaveDetails>> getAllPurchaseInvoiceWithDetail(@ApiParam("采购合同id")@RequestParam(required = false) String contractID){
        if (contractID==null){
            throw new NullPointerException("传入的数据存在空值");
        }
        List<PurchaseInvoiceHaveDetails> allPurchaseInvoiceWithDetail = purchaseInvoiceService.getAllPurchaseInvoiceWithDetail(contractID);
        return new ResultData<>(ReturnCode.RC100,allPurchaseInvoiceWithDetail);
    }
}
