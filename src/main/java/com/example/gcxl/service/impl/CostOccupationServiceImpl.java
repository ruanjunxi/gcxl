package com.example.gcxl.service.impl;

import com.example.gcxl.domain.*;
import com.example.gcxl.domain.query.CostOccupationCondition;
import com.example.gcxl.domain.query.RelationDetail;
import com.example.gcxl.domain.query.TakeUpResult;
import com.example.gcxl.mapper.CostOccupationMapper;
import com.example.gcxl.mapper.PurchaseInvoiceMapper;
import com.example.gcxl.mapper.SaleContractMapper;
import com.example.gcxl.mapper.SaleInvoiceMapper;
import com.example.gcxl.service.CostOccupationService;
import com.example.gcxl.service.PurchaseInvoiceService;
import com.example.gcxl.service.ex.InsertException;
import com.example.gcxl.service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
@Service
public class CostOccupationServiceImpl extends BaseServiceImpl implements CostOccupationService {
    @Autowired
    CostOccupationMapper costOccupationMapper;
    @Autowired
    SaleInvoiceMapper saleInvoiceMapper;
    @Autowired
    PurchaseInvoiceMapper purchaseInvoiceMapper;
    @Autowired
    SaleContractMapper saleContractMapper;
    @Override

    public List<CostOccupation> findAllCostOccupationByCondition(CostOccupationCondition condition) {
        List<CostOccupation> list = costOccupationMapper.findCostOccupationByCondition(condition);

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
                   saleInvoiceMapper.setStatus("已完成21",relationDetail.getSaleInvoiceID());
               }
           }
            //5.修改销售合同的关联率
            String contractID=saleInvoiceMapper.getContractIdByInvoiceId(relationDetail.getSaleInvoiceID());
            Float avgRate=saleInvoiceMapper.getAVGCorrelationRate(contractID);
            saleContractMapper.updateCorrelationRateByID(avgRate,contractID);
        }

    }

//    @Override
//    @Transactional
//    public void commodityRelated(int[] id, int[] nums, String saleInvoiceID,String operator) {
//        List<PurchaseInvoiceDetail> purchaseInvoiceDetailList = new ArrayList<>();
//        for(int i=0;i<id.length;i++){
//            //1.根据id查出商品
//            PurchaseInvoiceDetail detail = purchaseInvoiceMapper.findInvoiceDetailByID(id[i]);
//            purchaseInvoiceDetailList.add(detail);
//            //2.修改采购商品表关联数量和剩余数量
//           int res = saleInvoiceMapper.updateCorrelationNumberAndRemainNumberById(id[i],nums[i]);
//            if(res==0){
//                throw new UpdateException("修改数据产生异常");
//            }
//            //3.写入takeUp表
//            res=0;
//            String purchaseContractID = saleInvoiceMapper.getPurchaseContractIdByCommodityId(id[i]);
//            TakeUp takeup=new TakeUp(detail,saleInvoiceID,purchaseContractID,operator,nums[i]);
//            //添加takeup记录
//            res = costOccupationMapper.addTakeup(takeup);
//           if(res==0){
//               throw new InsertException("插入数据产生异常");
//           }
//           res=0;
//           //4.修改销售发票的关联率和关联金额
//           res = saleInvoiceMapper.updateSaleInvoiceWhenRelate(saleInvoiceID,detail.getAmount());
//           if(res==0){
//                throw new UpdateException("修改数据产生异常");
//            }
//           //修改销售合同的关联率
//            String contractID=saleInvoiceMapper.getContractIdByInvoiceId(saleInvoiceID);
//            Float avgRate=saleInvoiceMapper.getAVGCorrelationRate(contractID);
//            saleContractMapper.updateCorrelationRateByID(avgRate,contractID);
//        }
//
//
//
//    }

    @Override
    public List<TakeUp> findAllTakeUpBySaleInvoiceID(String saleInvoiceID) {
        List<TakeUp> takeUpList = costOccupationMapper.findAllTakeUpBySaleInvoiceID(saleInvoiceID);
//        List<TakeUp> res = new ArrayList<>();
//        List<SaleInvoiceDetail> detailList = saleInvoiceMapper.getSaleInvoiceDetailByInvoiceId(saleInvoiceID);
//        for (SaleInvoiceDetail detail:detailList
//             ) {
//            TakeUp takeUp=new TakeUp(detail);
//            takeUpList.add(0,takeUp);
//        }
        return takeUpList;
    }

    @Override
    public List<SaleInvoiceDetail> findSaleInvoiceDetailByInvoiceId(String saleInvoiceID) {
        List<SaleInvoiceDetail> detailList = saleInvoiceMapper.getSaleInvoiceDetailByInvoiceId(saleInvoiceID);
        return detailList;
    }

    @Override
    public void cancelRelation(int id) {
        TakeUp takeUp = costOccupationMapper.getTakeUpById(id);
        String saleInvoiceID = takeUp.getInvoiceID();
        System.out.println(takeUp);
        //1.修改takeup表状态为作废
        Integer res = costOccupationMapper.updateTakeUpStatusById(id);
        if(res==0){
            throw new UpdateException("修改数据产生异常");
        }
        //2.修改采购明细表的关联数量和剩余数量
        res=0;
        res = costOccupationMapper.updateStockById(takeUp.getCommodityID(), takeUp.getAssociatedNumber());
        if(res==0){
            throw new UpdateException("修改数据产生异常");
        }
        //3.修改销售发票的关联金额和关联率
//        BigDecimal
        double relationAmount = takeUp.getAssociatedNumber()*takeUp.getUnitPrice();
        System.out.println(relationAmount);
        res = saleInvoiceMapper.updateSaleInvoiceWhenCancelRelation(saleInvoiceID,relationAmount);
        if(res==0){
            throw new UpdateException("修改数据产生异常");
        }
        else{
            //判断关联率是否低于标准，低于则修改销售发票的状态为“待完成”
            Integer relationRate = saleInvoiceMapper.getRelationRate();
            Float saleRelationRate = saleInvoiceMapper.getSaleInvoiceRelationRateById(saleInvoiceID);
            if(saleRelationRate<relationRate){
                saleInvoiceMapper.setStatus("待完成",saleInvoiceID);
            }
        }
        //
        //4.修改销售合同的关联率
        String contractID=saleInvoiceMapper.getContractIdByInvoiceId(saleInvoiceID);
        float avgRate=saleInvoiceMapper.getAVGCorrelationRate(contractID);
        saleContractMapper.updateCorrelationRateByID(avgRate,contractID);
    }

    @Override
    public List<TakeUp> findAllTakeUpBySaleInvoiceID(String saleInvoiceID, String status) {
        List<TakeUp> takeUpList = costOccupationMapper.findAllTakeUpBySaleInvoiceIDWithStatus(saleInvoiceID,status);
        return takeUpList;
    }

    @Override
    public List<TakeUpResult> findAllTakeUpByCondition(String supplier, String tradeName, String nameCommodity, String model) {
        List<TakeUpResult> list = costOccupationMapper.findAllTakeUpByCondition(supplier,tradeName,nameCommodity,model);
        return list;
    }

    @Override
    public void complete(String invoiceID) {
       // 判断发票操作是否可撤销
        Float relationRate = saleInvoiceMapper.getSaleInvoiceRelationRateById(invoiceID);
        if(relationRate<0.001){
            saleInvoiceMapper.setOperation("撤销",invoiceID);
            //设置发票状态为已完成11
            saleInvoiceMapper.setStatus("已完成111",invoiceID);
        }
        else{
            //设置发票状态为已完成11
            saleInvoiceMapper.setStatus("已完成11",invoiceID);
        }
    }

    @Override
    public void recall(String invoiceID) {
        //设置发票操作字段为''
        saleInvoiceMapper.recall("","待完成",invoiceID);
    }


}
