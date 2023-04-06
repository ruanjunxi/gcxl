package com.example.gcxl.service.impl;

import com.example.gcxl.domain.OutBound;
import com.example.gcxl.domain.OutBoundDetails;
import com.example.gcxl.domain.PurchaseInvoiceDetail;
import com.example.gcxl.domain.PurchasingContract;
import com.example.gcxl.mapper.OutBoundMapper;
import com.example.gcxl.service.OutBoundService;
import com.example.gcxl.service.ex.InsertException;
import com.example.gcxl.service.ex.OutBoundIDExistException;
import com.example.gcxl.service.ex.OutBoundIDNotExistException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: OutBoundServiceImpl
 * @author: Eason
 * @data: 2022/4/20 9:46
 */
@Service
public class OutBoundServiceImpl implements OutBoundService {
    @Autowired
    private OutBoundMapper outBoundMapper;

    /**
     * 通过品名、商品名、型号查询（精确匹配）
     *
     * @param tradeName
     * @param nameCommodity
     * @param model
     * @return List<OutBoundDetails>
     */
    @Override
    public List<OutBoundDetails> findOutBoundDetailByTradeCommodityModel(String tradeName, String nameCommodity, String model) {
        List<OutBoundDetails> outBoundDetailByTradeCommodityModel = outBoundMapper.findOutBoundDetailByTradeCommodityModel(tradeName, nameCommodity, model);
        return outBoundDetailByTradeCommodityModel;
    }

    /**
     * 插入出库信息+出库明细
     * @param outBound
     * @param outBoundDetails
     */
    @Override
    public void insertOutBoundWithDetails(OutBound outBound, List<OutBoundDetails> outBoundDetails) {
        OutBound result = outBoundMapper.getOutBoundByID(outBound.getOutboundID());
        if (result != null) {
            throw new OutBoundIDExistException("出库编号已存在");
        }
        boolean b = outBoundMapper.insertOutBound(outBound);
        if (!b) {
            throw new InsertException("插入出现未知错误");
        }
        b = outBoundMapper.insertOutBoundDetails(outBoundDetails);
        if (!b) {
            throw new InsertException("插入出现未知错误");
        }
    }

    /**
     * 将通过出库ID将出库状态改为作废
     *
     * @param outBoundID
     */
    @Override
    public void updateOutBoundState(String outBoundID) {
        OutBound result = outBoundMapper.getOutBoundByID(outBoundID);
        if (result == null) {
            throw new OutBoundIDNotExistException("出库编号不存在");
        }
        boolean b = outBoundMapper.updateOutBoundState(outBoundID);
        if (!b) {
            throw new InsertException("插入出现未知错误");
        }
    }

    /**
     * 得到所有的出库ID 分页
     *
     * @return
     */
    @Override
    public PageInfo<OutBound> getAllOutBound(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OutBound> allOutBound = outBoundMapper.getAllOutBound();
        for (OutBound outBound : allOutBound) {
            String replace = outBound.getCreationDate().replace(".0", "");
            outBound.setCreationDate(replace);
        }
        PageInfo<OutBound> purchasingContractPageInfo = new PageInfo<>(allOutBound);
        return purchasingContractPageInfo;
    }

    /**
     * 通过出库ID获得对应的出库明细
     *
     * @param outBoundID
     * @return
     */
    @Override
    public List<OutBoundDetails> findOutBoundDetailsByOutBoundID(String outBoundID) {
        List<OutBoundDetails> outBoundDetailsByOutBoundID = outBoundMapper.findOutBoundDetailsByOutBoundID(outBoundID);
        return outBoundDetailsByOutBoundID;
    }


    /**
     * 修改采购发票明细中的剩余数量，出库数量
     * 插入时，新剩余数量=发票明细剩余数量-出库明细中的出库数量
     *        新出库数量=发票明细中的出库数量+出库明细的出库数量
     * 作废时，新剩余数量=发票明细中的剩余数量+出库明细中的出库数量
     *      新出库数量=发票明细中的出库数量-出库明细的出库数量
     *@param flag  flag为True时为插入时
     *             FALSE为作废时
     * @param outBoundID
     */
    @Override
    public void updateRemainOut(String outBoundID, boolean flag) {
        List<OutBoundDetails> outBoundDetails = outBoundMapper.findOutBoundDetailsByOutBoundID(outBoundID);
        for (OutBoundDetails o : outBoundDetails) {
            PurchaseInvoiceDetail invoiceDetail = outBoundMapper.findInvoiceDetailByID(o.getId());
            int invoiceDetailNumberRemain = invoiceDetail.getNumberRemain();
            int invoiceDetailOutboundNumber = invoiceDetail.getOutboundNumber();
            int outboundNumber = o.getOutboundNumber();
            int newNumberRemain = 0;
            int newOutboundNumber = 0;
            if (flag) {
                newNumberRemain = invoiceDetailNumberRemain - outboundNumber;
                newOutboundNumber = invoiceDetailOutboundNumber + outboundNumber;
            } else {
                newNumberRemain = invoiceDetailNumberRemain + outboundNumber;
                newOutboundNumber = invoiceDetailOutboundNumber - outboundNumber;
            }
            outBoundMapper.updateRemainAndOutNumberById(newNumberRemain, newOutboundNumber, o.getId());

        }
    }

    @Override
    public int getCountOutbound() {
        Integer countOutbound = outBoundMapper.getCountOutbound();
        return countOutbound;
    }

    @Override
    public PageInfo<OutBound> getOutboundByConditions(String outBoundID,String outBoundType,String state,int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<OutBound> outboundByConditions = outBoundMapper.getOutboundByConditions(outBoundID, outBoundType, state);
        for (OutBound outboundByCondition : outboundByConditions) {
            String replace = outboundByCondition.getCreationDate().replace(".0", "");
            outboundByCondition.setCreationDate(replace);
        }
        PageInfo<OutBound> purchasingContractPageInfo = new PageInfo<>(outboundByConditions);

        return purchasingContractPageInfo;
    }

}
