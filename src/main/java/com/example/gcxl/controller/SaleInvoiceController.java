package com.example.gcxl.controller;

import com.example.gcxl.domain.*;
import com.example.gcxl.domain.query.SaleInvoiceResult;
import com.example.gcxl.service.SaleInvoiceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
@RestController
@Api("销售发票接口")
@RequestMapping("sale_invoice")
public class SaleInvoiceController extends BaseController {

    @Autowired
    private SaleInvoiceService saleInvoiceService;


    @ApiOperation("新增销售发票")
    @PostMapping("add_new_Invoice")
    @Transactional
    public ResultData addInvoice(@ApiParam("销售发票实体") @Valid @RequestBody SaleInvoice invoice
//                                 HttpSession session
    ){
//        invoice.setCreator(getUsernameFromSession(session));
        invoice.setCreator("lyy");
        System.out.println(invoice);
        for (SaleInvoiceDetail detail:invoice.getDetailList()
             ) {
            detail.setInvoiceID(invoice.getInvoiceID());
        }
        saleInvoiceService.AddInvoice(invoice);

        //插入发票明细
        saleInvoiceService.addBatchInvoiceDetail(invoice.getDetailList());
        return new ResultData<>(ReturnCode.RC100);
    }

    @ApiOperation("修改销售发票")
    @GetMapping("modify_Invoice")
    @Transactional
    public ResultData modifyInvoice(@ApiParam("销售发票编号") @RequestParam(value = "invoiceId",required = false) String invoiceId,
                                 @ApiParam("开票日期") @RequestParam(value = "invoiceDate",required = false) String date,
                                 @ApiParam("发票类型") @RequestParam(value = "invoiceType",required = false) String type,
                                 HttpSession session){
        saleInvoiceService.updateInvoiceById(invoiceId,date,type,getUsernameFromSession(session));
        return new ResultData<>(ReturnCode.RC100);
    }

    @ApiOperation("修改销售发票明细")
    @PostMapping("modify_Invoice_detail")
    @Transactional
    public ResultData modifyInvoiceDetail( @ApiParam("销售发票明细") @RequestBody @Valid SaleInvoiceDetail detail, HttpSession session){
        saleInvoiceService.updateInvoiceDetailById(detail,getUsernameFromSession(session));
        return new ResultData<>(ReturnCode.RC100);
    }

    @ApiOperation("显示发票修改记录")
    @GetMapping("get_modify_invoice")
    public ResultData<List<ModifyLogWithDetails>> getModifyInvoice(@ApiParam("销售发票ID") @RequestParam(value = "invoiceId",required = true) String invoiceId,
                                                                   HttpSession session){
        List<ModifyLogWithDetails> detailsList = saleInvoiceService.getModifyWithDetailByTypeID(invoiceId);
        return new ResultData<>(ReturnCode.RC100,detailsList);
    }

    @ApiOperation("显示发票明细修改记录")
    @GetMapping("get_modify_invoice_detail")
    public ResultData<List<ModifyLogWithDetails>> getModifyInvoiceDetail(@ApiParam("销售发票明细ID") @RequestParam(value = "detailID",required = true) String detailID,
                                                                   HttpSession session){
        List<ModifyLogWithDetails> detailsList = saleInvoiceService.getModifyWithDetailByTypeID(detailID);
        return new ResultData<>(ReturnCode.RC100,detailsList);
    }

    @ApiOperation("销售发票查询")
    @GetMapping("query_saleInvoice")
    public ResultData<SaleInvoiceResult> querySaleInvoice(@ApiParam("发票编号") @RequestParam(required = false) String invoiceId,
                                                          @ApiParam("采购单位") @RequestParam(required = false) String purchasingUnit,
                                                          @ApiParam("开票年月") @RequestParam(required = false) String date,
                                                          @ApiParam("页码") @RequestParam(required = false) Integer pageNum,
                                                          @ApiParam("分页大小") @RequestParam(required = false) Integer pageSize,
                                                          HttpSession session) throws ParseException {
        String year=null;
        String month=null;
        if(date!=null){
            Date d1 = new SimpleDateFormat("yyyy-MM").parse(date);
            SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy");

            SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
            year = sdf0.format(d1);
            month = sdf1.format(d1);
        }
        SaleInvoiceResult result = new SaleInvoiceResult();
        Float amount=0f;
        Integer counts=0;
        List<SaleInvoice> listAll = saleInvoiceService.getSaleInvoiceByCondition(invoiceId,purchasingUnit,year,month);
        for (SaleInvoice item:listAll
             ) {
            counts++;
            amount+=item.getAmount();
        }
        PageHelper.startPage(pageNum,pageSize);
        List<SaleInvoice> list = saleInvoiceService.getSaleInvoiceByCondition(invoiceId,purchasingUnit,year,month);
        PageInfo<SaleInvoice> info = new PageInfo<>(list);
        result.setSaleInvoiceList(info);
        result.setAmount(amount);
        result.setCounts(counts);
        return new ResultData<>(ReturnCode.RC100,result);
    }
    @ApiOperation("销售发票明细查询")
    @GetMapping("query_saleInvoiceDetail")
    public ResultData<List<SaleInvoiceDetail>> querySaleInvoiceDetail(@ApiParam("发票编号") @RequestParam String invoiceId,
                                                          HttpSession session){
        List<SaleInvoiceDetail> list = saleInvoiceService.getSaleInvoiceDetailByInvoiceId(invoiceId);
        return new ResultData<>(ReturnCode.RC100,list);
    }
}
