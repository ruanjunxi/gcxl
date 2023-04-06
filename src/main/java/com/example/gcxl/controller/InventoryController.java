package com.example.gcxl.controller;

import com.example.gcxl.domain.*;
import com.example.gcxl.service.InventoryService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: InventoryController
 * @author: Eason
 * @data: 2022/4/23 16:59
 */
@RestController
@Api("库存查询")
@RequestMapping("Inventory")
@Transactional(rollbackFor=Exception.class)

public class InventoryController extends BaseController{
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/getAllInventory")
    @ApiOperation("得到所有的库存")
    public ResultData<PageInfo<Inventory>> getAllInventory(@ApiParam("页数") @RequestParam(required = false) Integer pageNum,
                                                       @ApiParam("页面大小") @RequestParam(required = false) Integer pageSize){

        PageInfo<Inventory> allInventory = inventoryService.getAllInventory(pageNum,pageSize);
        return new ResultData(ReturnCode.RC100,allInventory);
    }

    @GetMapping("/getInventoryByIDTradeCommodityModel")
    @ApiOperation("通过合同id，发票id，品名，商品名称，型号，按order升序或降序排序返回")
    public ResultData<PageInfo<Inventory>> getInventoryByIDTradeCommodityModel(@ApiParam("品名")  @RequestParam(required = false)String tradeName,
                                                                           @ApiParam("商品名称") @RequestParam(required = false)String nameCommodity,
                                                                           @ApiParam("型号") @RequestParam(required = false)String model,
                                                                           @ApiParam("合同id") @RequestParam(required = false)String contractID,
                                                                           @ApiParam("发票id") @RequestParam(required = false)String invoiceID,
                                                                           @ApiParam("按order排序") @RequestParam(required = false)String order,
                                                                           @ApiParam("升序和降序") @RequestParam(required = false)String orderType,
                                                                           @ApiParam("升序和降序") @RequestParam(required = false)Integer pageNum,
                                                                           @ApiParam("升序和降序") @RequestParam(required = false)Integer pageSize){
//        if (tradeName==null||nameCommodity==null||model==null||contractID==null||invoiceID==null||order==null||orderType==null){
//            throw new NullPointerException("传入的数据存在空值");
//        }
        PageInfo<Inventory> inventoryByIDTradeCommodityModel = inventoryService.getInventoryByIDTradeCommodityModel(tradeName, nameCommodity, model, contractID, invoiceID, order, orderType, pageNum, pageSize);
        return new ResultData(ReturnCode.RC100,inventoryByIDTradeCommodityModel);
    }

    @GetMapping("/getAll")
    @ApiOperation("通过商品id得到出库明细")
    public ResultData<List<RelatedSales>> getOutBoundByID(@ApiParam("明细id") @RequestParam(required = false) Integer id){
        if (id==null){
            throw new NullPointerException("传入的数据存在空值");
        }
        List<OutBoundWithDetail> outBoundByID = inventoryService.getOutBoundByID(id);
        List<RelatedSales> relatedSalesByID = inventoryService.getRelatedSalesByID(id);
        for (RelatedSales r : relatedSalesByID) {
            r.setType("销售关联");
        }
        for (OutBoundWithDetail o : outBoundByID) {
            RelatedSales r = new RelatedSales();
            r.setType("其他出库");
            r.setAssociatedNumber(o.getOutboundNumber());
            r.setOperator(o.getAccount());
            r.setOperationDate(o.getCreationDate());
            r.setInvoiceID(o.getOutboundID());
            relatedSalesByID.add(r);
        }
        return new ResultData(ReturnCode.RC100,relatedSalesByID);
    }

    @GetMapping("/updateDetailNote")
    @ApiOperation("更新商品明细的备注信息")
    public ResultData<Void> updateDetailNote(@ApiParam("备注信息")  @RequestParam(required = false)String noteInformation,
                                             @ApiParam("采购发票明细")  @RequestParam(required = false)Integer id){
        if (noteInformation==null){
            noteInformation="";
        }
        if (id==null){
            throw new NullPointerException("传入的数据存在空值");
        }
        inventoryService.updateDetailNote(noteInformation,id);
        return new ResultData<>(ReturnCode.RC100);
    }

//    @GetMapping("/getRelatedSalesByID")
//    @ApiOperation("通过商品id获得关联信息")
//    public ResultData<List<RelatedSales>> getRelatedSalesByID(@ApiParam("商品id")  @RequestParam(required = false)Integer id){
//        List<RelatedSales> relatedSalesByID = inventoryService.getRelatedSalesByID(id);
//        return new ResultData<>(ReturnCode.RC100,relatedSalesByID);
//    }
}
