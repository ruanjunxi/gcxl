package com.example.gcxl.controller;

import com.example.gcxl.domain.*;
import com.example.gcxl.domain.query.CostOccupationCondition;
import com.example.gcxl.domain.query.CostOccupationResult;
import com.example.gcxl.domain.query.RelationDetail;
import com.example.gcxl.service.CostOccupationService;
import com.example.gcxl.service.FareAdjustmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api("补票占用管理")
@RequestMapping("fare_adjustment")
public class FareAdjustmentController extends BaseController {
    @Autowired
    CostOccupationService costOccupationService;

    @Autowired
    FareAdjustmentService fareAdjustmentService;


    @ApiOperation("补票占用管理页面展示")
    @PostMapping("/all_fareAdjustment_byCondition")
    public ResultData<CostOccupationResult> findAllContractByCondition(@ApiParam("补票占用管理页面查询条件") @RequestBody @Valid CostOccupationCondition condition) {
//        System.out.println(condition);

//        System.out.println(money);
        //分页
//        if(condition.getStatus()!=null || condition.getStatus()!=""){
//            if(condition.getStatus()=="待完成") condition.setStatus("已完成1");
//            else if(condition.getStatus()=="已完成") condition.setStatus("已完成22");
//        }

        PageHelper.startPage(condition.getPageInfo().getPageNum(),condition.getPageInfo().getPageSize());
        List<CostOccupation> list = fareAdjustmentService.findAllFareAdjustmentByCondition(condition);
        for (CostOccupation item:list
        ) {
            System.out.println(item.getStatus());
            if(item.getStatus().equals("已完成11")) item.setStatus("待完成");
            else item.setStatus("已完成");

            System.out.println(item.getStatus());
        }
        int counts = list.size();
        PageInfo<CostOccupation> info = new PageInfo<>(list);
//        System.out.println(list);
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

//                                            HttpSession session
    @ApiOperation("商品关联")
    @PostMapping("/commodity_related")
    public ResultData commodityRelated( @ApiParam("关联明细实体，带上销售发票编号") @RequestBody List<RelationDetail> details){
//        fareAdjustmentService.commodityRelated(details,getUsernameFromSession(session));
        fareAdjustmentService.commodityRelated(details,"lyy");
        return new ResultData<>(ReturnCode.RC100);

    }

    @ApiOperation("补票占用管理页面的完成")
    @GetMapping("/completion")
    public ResultData completion(@ApiParam("销售发票编号") @RequestParam() String invoiceID){
        fareAdjustmentService.complete(invoiceID);
        return new ResultData<>(ReturnCode.RC100);
    }
    @ApiOperation("补票占用管理页面的撤销")
    @GetMapping("/recall")
    public ResultData recall(@ApiParam("销售发票编号") @RequestParam() String invoiceID){
        fareAdjustmentService.recall(invoiceID);
        return new ResultData<>(ReturnCode.RC100);
    }
}
