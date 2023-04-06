package com.example.gcxl.controller;

import com.example.gcxl.domain.ResultData;
import com.example.gcxl.domain.ReturnCode;
import com.example.gcxl.domain.SaleInvoice;
import com.example.gcxl.domain.query.BusinessQueryResult;
import com.example.gcxl.domain.query.SaleInvoiceResult;
import com.example.gcxl.domain.query.WriteOffResult;
import com.example.gcxl.service.BusinessQueryService;
import com.example.gcxl.service.SaleContractService;
import com.example.gcxl.service.SaleInvoiceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
@RestController
@Api("业务查询")
@RequestMapping("business_query")
public class BusinessQueryController extends BaseController{

    @Autowired
    SaleInvoiceService saleInvoiceService;
    @Autowired
    SaleContractService saleContractService;
    @Autowired
    BusinessQueryService queryService;

    @ApiOperation("销售发票查询11")
    @GetMapping("query_saleInvoice")
    public ResultData<SaleInvoiceResult> querySaleInvoice(@ApiParam("发票编号") @RequestParam(required = false) String invoiceId,
                                                          @ApiParam("采购单位") @RequestParam(required = false) String purchasingUnit,
                                                          @ApiParam("开票年月") @RequestParam(required = false) String date,
                                                          @ApiParam("页码") @RequestParam(required = false) Integer pageNum,
                                                          @ApiParam("分页大小") @RequestParam(required = false) Integer pageSize) throws ParseException {
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

    @ApiOperation("核销查询")
    @GetMapping("query_write_off")
    public ResultData<PageInfo<WriteOffResult>> queryWriteOff(@ApiParam("采购单位") @RequestParam(required = false) String purchasingUnit,
                                                       @ApiParam("合同编号") @RequestParam(required = false) String contractID,
                                                       @ApiParam("低占用率") @RequestParam(required = false ,defaultValue = "0.0") Float lowRelationRate,
                                                       @ApiParam("高占用率") @RequestParam(required = false,defaultValue = "10000") Float highRelationRate,
                                                       @ApiParam("状态") @RequestParam(required = false) String status,
                                                       @ApiParam("页码") @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                                       @ApiParam("分页大小") @RequestParam(required = false,defaultValue = "15") Integer pageSize) throws ParseException{
        PageHelper.startPage(pageNum,pageSize);
        List<WriteOffResult> result = saleContractService.writeOffQuery(purchasingUnit,contractID,lowRelationRate,highRelationRate,status);
//        for (WriteOffResult item: result
//             ) {
//            item.setRelationStatus("待完成");
//        }
        //设置关联状态，只有该合同编号下所有发票有已完成的发票状态，则该合同状态设置为已完成
        List<WriteOffResult> toDelete = new ArrayList<>();
        for (WriteOffResult item: result
        ) {
            if(status==null||status.equals("")) {
                item.setRelationStatus("待完成");
                List<SaleInvoice> invoices = saleInvoiceService.getSaleInvoiceByContractId(item.getContractID());
                if(invoices!=null) {
                    for (SaleInvoice invoice : invoices
                    ) {
                        if ((!invoice.getStatus().equals("待完成")) && (!invoice.getStatus().equals("已完成11")) && (!invoice.getStatus().equals("已完成111"))) {
                            item.setRelationStatus("已完成");
//                            result.remove(item);
                        }
                    }
                }
            }
            else if(status.equals("待完成")){
                item.setRelationStatus("待完成");
                List<SaleInvoice> invoices = saleInvoiceService.getSaleInvoiceByContractId(item.getContractID());
                if(invoices!=null) {
                    for (SaleInvoice invoice : invoices
                    ) {
                        if ((!invoice.getStatus().equals("待完成")) && (!invoice.getStatus().equals("已完成11")) && (!invoice.getStatus().equals("已完成111"))) {
//                            item.setRelationStatus("已完成");
//                            result.remove(item);
                            toDelete.add(item);
                        }
                    }
                }
            }
           else if(status.equals("已完成")){
                item.setRelationStatus("已完成");
                List<SaleInvoice> invoices = saleInvoiceService.getSaleInvoiceByContractId(item.getContractID());
                if(invoices!=null) {
                    //默认是待完成
                    boolean flag=false;
                    for (SaleInvoice invoice : invoices
                    ) {
                        if ((!invoice.getStatus().equals("待完成")) && (!invoice.getStatus().equals("已完成11")) && (!invoice.getStatus().equals("已完成111"))) {
//                            item.setRelationStatus("已完成");
                            flag=true;
                        }
                    }
                    if(!flag){
//                        result.remove(item);
                        toDelete.add(item);
                    }
                }
                else{
                    toDelete.add(item);
                }
            }
        }
        for (WriteOffResult item :toDelete
             ) {
            result.remove(item);
        }


        for (WriteOffResult item :result
        ) {
            item.setCounts(result.size());
        }
        PageInfo<WriteOffResult> info = new PageInfo<>(result);
        return new ResultData<>(ReturnCode.RC100,info);
    }

    @ApiOperation("核销查询：当前合同下的销售情况")
    @GetMapping("query_write_off_detail")
    public ResultData<List<BusinessQueryResult>> queryWriteOffDetail(@ApiParam("销售合同编号") @RequestParam() String contractID){
        List<BusinessQueryResult> saleInfo = queryService.getSaleInfo(contractID);
        List<BusinessQueryResult> takeUpInfo = queryService.getTakeUpInfo(contractID);
        List<BusinessQueryResult> res = saleInfo;
        for (BusinessQueryResult item:takeUpInfo
             ) {
            res.add(item);
        }
        return new ResultData<>(ReturnCode.RC100,res);
    }
}
