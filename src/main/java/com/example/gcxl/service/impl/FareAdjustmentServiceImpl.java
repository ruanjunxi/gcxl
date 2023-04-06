package com.example.gcxl.service.impl;

import com.example.gcxl.domain.CostOccupation;
import com.example.gcxl.domain.PurchaseInvoiceDetail;
import com.example.gcxl.domain.SaleInvoice;
import com.example.gcxl.domain.TakeUp;
import com.example.gcxl.domain.query.CostOccupationCondition;
import com.example.gcxl.domain.query.RelationDetail;
import com.example.gcxl.mapper.*;
import com.example.gcxl.service.FareAdjustmentService;
import com.example.gcxl.service.ex.InsertException;
import com.example.gcxl.service.ex.UpdateException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
@Service
public class FareAdjustmentServiceImpl extends BaseServiceImpl implements FareAdjustmentService {

    @Autowired
    FareAdjustmentMapper fareAdjustmentMapper;
    @Autowired
    CostOccupationMapper costOccupationMapper;
    @Autowired
    SaleInvoiceMapper saleInvoiceMapper;
    @Autowired
    PurchaseInvoiceMapper purchaseInvoiceMapper;
    @Autowired
    SaleContractMapper saleContractMapper;
    @Override
    public List<CostOccupation> findAllFareAdjustmentByCondition(CostOccupationCondition condition) {
        List<CostOccupation> list = fareAdjustmentMapper.findFareAdjustmentByCondition(condition);

        return list;
    }

    @Override
    @Transactional
    public void commodityRelated(List<RelationDetail> details, String operator) {
        for (RelationDetail relationDetail:details) {
            //1.根据id查出商品
            PurchaseInvoiceDetail detail = purchaseInvoiceMapper.findInvoiceDetailByID(relationDetail.getId());
//            purchaseInvoiceDetailList.add(detail);
            Double relationAmount=detail.getUnitPrice()*relationDetail.getCorrelationNumber();
            //2.修改采购商品表关联数量和剩余数量
            int res = saleInvoiceMapper.updateCorrelationNumberAndRemainNumberById(relationDetail.getId(), relationDetail.getCorrelationNumber());
            if(res==0){
                throw new UpdateException("修改数据产生异常");
            }
            //3.写入takeUp表
            res=0;
            String purchaseContractID = saleInvoiceMapper.getPurchaseContractIdByCommodityId(relationDetail.getId());
            TakeUp takeup=new TakeUp(detail,relationDetail.getSaleInvoiceID(),purchaseContractID,operator,relationDetail.getCorrelationNumber());
            takeup.setTakeUpType("补票占用");
            //添加takeup记录
            res = costOccupationMapper.addTakeup(takeup);
            if(res==0){
                throw new InsertException("插入数据产生异常");
            }
            res=0;
            //4.修改销售发票的关联率和关联金额
            res = saleInvoiceMapper.updateSaleInvoiceWhenRelate(relationDetail.getSaleInvoiceID(),relationAmount);
            if(res==0){
                throw new UpdateException("修改数据产生异常");
            }
            else{
                //判断关联率是否达到已完成标准，达到则修改销售发票的状态为“已完成2”
                Integer relationRate = saleInvoiceMapper.getRelationRate();
                Float saleRelationRate = saleInvoiceMapper.getSaleInvoiceRelationRateById(relationDetail.getSaleInvoiceID());
                if(saleRelationRate>relationRate){
                    saleInvoiceMapper.setStatus("已完成22",relationDetail.getSaleInvoiceID());
                }
            }
            //5.修改销售合同的关联率
            String contractID=saleInvoiceMapper.getContractIdByInvoiceId(relationDetail.getSaleInvoiceID());
            Float avgRate=saleInvoiceMapper.getAVGCorrelationRate(contractID);
            saleContractMapper.updateCorrelationRateByID(avgRate,contractID);
        }
    }

    @Override
    public void complete(String invoiceID) {
//        判断发票操作是否可撤销
        Float relationRate = saleInvoiceMapper.getSaleInvoiceRelationRateById(invoiceID);
        Integer relationRateParam = saleInvoiceMapper.getRelationRate();
        if(relationRate<relationRateParam){
            saleInvoiceMapper.setOperation("撤销",invoiceID);
        }
        //设置发票状态为已完成11
        saleInvoiceMapper.setStatus("已完成12",invoiceID);
    }

    @Override
    public void recall(String invoiceID) {
        //设置发票操作字段为''
        saleInvoiceMapper.recall("","已完成11",invoiceID);
    }
}
