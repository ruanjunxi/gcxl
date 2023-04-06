package com.example.gcxl.controller;

import com.example.gcxl.domain.*;
import com.example.gcxl.service.OutBoundService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.OMGVMCID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName: OutBoundController
 * @author: Eason
 * @data: 2022/4/20 10:58
 */
@Slf4j
@RestController
@Api("出库接口")
@RequestMapping("outbound")
@Transactional(rollbackFor=Exception.class)

public class OutBoundController extends BaseController {
    @Autowired
    private OutBoundService outBoundService;

    @GetMapping("/findOutBoundDetailByTradeCommodityModel")
    @ApiOperation("用品名、商品名、型号来查询商品")
    public ResultData<List<OutBoundDetails>> findOutBoundDetailByTradeCommodityModel(@ApiParam("品名") @RequestParam(required = false) String tradeName,
                                                                                     @ApiParam("商品名称") @RequestParam(required = false) String nameCommodity,
                                                                                     @ApiParam("型号") @RequestParam(required = false) String model) {
        if (tradeName==null&&nameCommodity==null&&model==null){
            throw new NullPointerException("传入的数据存在空值");
        }
        List<OutBoundDetails> outBoundDetail = outBoundService.findOutBoundDetailByTradeCommodityModel(tradeName, nameCommodity, model);
        return new ResultData<>(ReturnCode.RC100, outBoundDetail);
    }

    @PostMapping("/insertOutBoundWithDetails")
    @ApiOperation("插入出库+明细，并修改采购发票明细中的剩余数量和出库数量")
    public ResultData<Void> insertOutBoundWithDetails(@ApiParam("出库有明细")@RequestBody OutBoundHaveDetail outBoundHaveDetail, HttpSession session) {
        log.info(outBoundHaveDetail.toString());
        if (outBoundHaveDetail==null){
            throw new NullPointerException("传入的数据存在空值");
        }
//        String account = getUsernameFromSession(session);
        String account="eason";
        OutBound outBound = outBoundHaveDetail.getOutBound();
        outBound.setAccount(account);
        List<OutBoundDetails> outBoundDetails = outBoundHaveDetail.getOutBoundDetails();
        if (outBoundDetails==null){
            throw new NullPointerException("传入数据存在空");
        }
        outBoundService.insertOutBoundWithDetails(outBound, outBoundDetails);
        outBoundService.updateRemainOut(outBound.getOutboundID(), true);
        return new ResultData(ReturnCode.RC100);
    }

    @GetMapping("/updateOutBoundState")
    @ApiOperation("修改出库状态，并修改采购发票明细中的剩余数量和出库数量")
    public ResultData<Void> updateOutBoundState(@ApiParam("出库编号") @RequestParam(required = false) String outBoundID) {
        if (outBoundID==null){
            throw new NullPointerException("传入的数据存在空值");
        }
        outBoundService.updateOutBoundState(outBoundID);
        outBoundService.updateRemainOut(outBoundID, false);
        return new ResultData(ReturnCode.RC100);
    }

    @GetMapping("/getAllOutBound")
    @ApiOperation("得到所有的出库(分页)")
    public ResultData<PageInfo<OutBound>> getAllOutBound(@ApiParam("第几页")@RequestParam(required = false) Integer pageNum,
                                                         @ApiParam("页面大小")@RequestParam(required = false) Integer pageSize) {
        if (pageNum==null&&pageSize==null){
            throw new NullPointerException("传入的数据存在空值");
        }
        PageInfo<OutBound> allOutBound = outBoundService.getAllOutBound(pageNum, pageSize);
        return new ResultData<>(ReturnCode.RC100, allOutBound);
    }

    @GetMapping("/findOutBoundDetailsByOutBoundID")
    @ApiOperation("通过出库id来查找出库明细")
    public ResultData<List<OutBoundDetails>> findOutBoundDetailsByOutBoundID(@ApiParam("出库编号") @RequestParam(required = false) String outBoundID) {
        if (outBoundID==null){
            throw new NullPointerException("传入的数据存在空值");
        }
        List<OutBoundDetails> outBoundDetailsByOutBoundID = outBoundService.findOutBoundDetailsByOutBoundID(outBoundID);
        return new ResultData<>(ReturnCode.RC100, outBoundDetailsByOutBoundID);
    }

    @GetMapping("/getCountOutbound")
    @ApiOperation("获得出库数量")
    public ResultData<Integer> getCountOutbound() {
        int countOutbound = outBoundService.getCountOutbound();
        return new ResultData<>(ReturnCode.RC100, countOutbound);
    }

    @GetMapping("/getOutboundByConditions")
    @ApiOperation("根据条件查询")
    public ResultData<PageInfo<OutBound>> getOutboundByConditions(@ApiParam("出库编号")@RequestParam(required = false)String outBoundID,
                                                                  @ApiParam("出库类型")@RequestParam(required = false)String outBoundType,
                                                                  @ApiParam("出库状态")@RequestParam(required = false)String state,
                                                                  @ApiParam("第几页")@RequestParam(required = false)Integer pageNum,
                                                                  @ApiParam("页面大小")@RequestParam(required = false)Integer pageSize){
        PageInfo<OutBound> outboundByConditions = outBoundService.getOutboundByConditions(outBoundID, outBoundType, state, pageNum, pageSize);
        return new ResultData<>(ReturnCode.RC100,outboundByConditions);
    }
}
