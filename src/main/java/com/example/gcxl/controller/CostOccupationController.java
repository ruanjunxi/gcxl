package com.example.gcxl.controller;

import com.example.gcxl.domain.*;
import com.example.gcxl.domain.query.*;
import com.example.gcxl.service.CostOccupationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */

@RestController
@Api("成本占用管理")
@RequestMapping("cost_occupation")
public class CostOccupationController extends BaseController{
    @Autowired
    CostOccupationService costOccupationService;
    //    分页
    @ApiOperation("显示条件查询的成本占用管理页面数据")
    @PostMapping("/all_costOccupation_byCondition")
    public ResultData<CostOccupationResult> findAllContractByCondition(@ApiParam("成本占用管理页面查询条件") @RequestBody @Valid CostOccupationCondition condition) {
//        System.out.println(condition);

//        System.out.println(money);
        //分页
//        if(condition.getStatus()==null || condition.getStatus()!=""){
//            if(condition.getStatus()=="已完成") condition.setStatus("已完成21");
//        }
        PageHelper.startPage(condition.getPageInfo().getPageNum(),condition.getPageInfo().getPageSize());
        List<CostOccupation> list = costOccupationService.findAllCostOccupationByCondition(condition);
        int counts = list.size();
        for (CostOccupation item:list
             ) {
//            if(item.getStatus().equals("已完成11")&&item.getCorrelationRate()<0.000001){
//                item.setOperation("撤销");
//            }
            if(item.getStatus().equals("已完成11")||item.getStatus().equals("已完成21")||item.getStatus().equals("已完成111")){
                item.setStatus("已完成");
            }
        }
        PageInfo<CostOccupation> info = new PageInfo<>(list);
        System.out.println(list);
        CostOccupationResult result = new CostOccupationResult(info,counts);

        return new ResultData<>(ReturnCode.RC100, result);
    }
    @ApiOperation("显示当前销售发票下的关联信息")
    @GetMapping("/all_takeUp_bySaleInvoiceID")
    public ResultData<List<TakeUp>> findAllContractByCondition(@ApiParam("销售发票id")  @RequestParam(required = true) String saleInvoiceID){
        List<TakeUp> list = costOccupationService.findAllTakeUpBySaleInvoiceID(saleInvoiceID);
        List<SaleInvoiceDetail> detailList = costOccupationService.findSaleInvoiceDetailByInvoiceId(saleInvoiceID);
        for (SaleInvoiceDetail detail:detailList
             ) {
            TakeUp takeUp=new TakeUp(detail);
            list.add(0,takeUp);
        }
        return new ResultData<>(ReturnCode.RC100, list);
    }

//    @ApiOperation("发票关联所需展示")
//    @PostMapping("/invoice_related_show")
//    public  invoiceRelatedShow();
//    @ApiOperation("商品关联")
//    @PostMapping("/commodity_related")
//    public ResultData commodityRelated(@ApiParam("关联明细实体，带上销售发票编号") @RequestBody() int id[],
//                                         @ApiParam("需要关联采购商品的数量") @RequestParam(value = "nums",required = false) int nums[],
//                                         @ApiParam("销售发票id") @RequestParam(value = "saleInvoiceID",required = false) String saleInvoiceID,
//                                         HttpSession session){
//        costOccupationService.commodityRelated(id,nums,saleInvoiceID,getUsernameFromSession(session));
//        return new ResultData<>(ReturnCode.RC100);
//    }
    @ApiOperation("商品关联")
    @PostMapping("/commodity_related")
    public ResultData commodityRelated( @ApiParam("关联明细实体，带上销售发票编号") @RequestBody List<RelationDetail> details
//                                         HttpSession session
    ){
//        costOccupationService.commodityRelated(details,getUsernameFromSession(session));
        costOccupationService.commodityRelated(details,"lyy");
        return new ResultData<>(ReturnCode.RC100);
    }


    @ApiOperation("作废")
    @GetMapping("/cancel_relation")
    @Transactional
    public ResultData cancelRelation(@ApiParam("需要作废商品的takeUpID") @RequestParam() int id){
        costOccupationService.cancelRelation(id);
        return new ResultData<>(ReturnCode.RC100);
    }
    @ApiOperation("点击关联后，弹出发票关联页面展示的关联明细")
    @GetMapping("/get_takeUp")
    public ResultData getTakeUp( @ApiParam("当前的销售发票编号") @RequestParam String saleInvoiceID){
        List<TakeUp> list = costOccupationService.findAllTakeUpBySaleInvoiceID(saleInvoiceID,"正常");
        return new ResultData<>(ReturnCode.RC100, list);
    }
    @ApiOperation("发票关联的查询页面")
    @GetMapping("/query_takeUp")
    public ResultData queryTakeUp(@ApiParam("供应商") @RequestParam(required = false) String supplier,
                                  @ApiParam("品牌名") @RequestParam(required = false) String tradeName,
                                  @ApiParam("商品名") @RequestParam(required = false) String nameCommodity,
                                  @ApiParam("型号") @RequestParam(required = false) String model){
        List<TakeUpResult> list = costOccupationService.findAllTakeUpByCondition(supplier,tradeName,nameCommodity,model);
        return new ResultData<>(ReturnCode.RC100, list);
    }
    @ApiOperation("成本占用管理页面的完成")
    @GetMapping("/completion")
    public ResultData queryTakeUp(@ApiParam("销售发票编号") @RequestParam() String invoiceID){
        costOccupationService.complete(invoiceID);
        return new ResultData<>(ReturnCode.RC100);
    }

    @ApiOperation("成本占用管理页面的撤销")
    @GetMapping("/recall")
    public ResultData recall(@ApiParam("销售发票编号") @RequestParam() String invoiceID){
        costOccupationService.recall(invoiceID);
        return new ResultData<>(ReturnCode.RC100);
    }
}
