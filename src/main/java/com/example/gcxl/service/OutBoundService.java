package com.example.gcxl.service;

import com.example.gcxl.domain.OutBound;
import com.example.gcxl.domain.OutBoundDetails;
import com.example.gcxl.domain.PurchasingContract;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @ClassName: OutBoundService
 * @author: Eason
 * @data: 2022/4/20 9:31
 */
public interface OutBoundService {
    /*通过品名、商品名、型号查询（精确匹配）*/
    List<OutBoundDetails> findOutBoundDetailByTradeCommodityModel(String tradeName, String nameCommodity, String model);
    /*插入出库信息+出库明细*/
    void insertOutBoundWithDetails(OutBound outBound ,List<OutBoundDetails> outBoundDetails);
    /*将通过出库ID将出库状态改为作废*/
    void updateOutBoundState(String outBoundID);
    /*得到所有的出库，用于前端显示（分页）*/
    PageInfo<OutBound> getAllOutBound(int pageNum, int pageSize);
    /*通过出库ID获得对应的出库明细*/
    List<OutBoundDetails> findOutBoundDetailsByOutBoundID(String outBoundID);
    /*修改采购发票明细中的剩余数量，出库数量*/
    void updateRemainOut(String outBoundID, boolean flag);

    int getCountOutbound();

    PageInfo<OutBound> getOutboundByConditions(String outBoundID,String outBoundType,String state,int pageNum,int pageSize);

}
