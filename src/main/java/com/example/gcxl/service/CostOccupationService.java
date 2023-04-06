package com.example.gcxl.service;

import com.example.gcxl.domain.*;
import com.example.gcxl.domain.query.CostOccupationCondition;
import com.example.gcxl.domain.query.RelationDetail;
import com.example.gcxl.domain.query.TakeUpResult;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
public interface CostOccupationService extends BaseService{
    List<CostOccupation> findAllCostOccupationByCondition(CostOccupationCondition condition);
    void commodityRelated(List<RelationDetail> details, String operator);
    List<TakeUp> findAllTakeUpBySaleInvoiceID(String saleInvoiceID);
    List<SaleInvoiceDetail> findSaleInvoiceDetailByInvoiceId(String saleInvoiceID);

    void cancelRelation(int id);

    List<TakeUp> findAllTakeUpBySaleInvoiceID(String saleInvoiceID, String status);

    List<TakeUpResult> findAllTakeUpByCondition(String supplier, String tradeName, String nameCommodity, String model);

    void complete(String invoiceID);

    void recall(String invoiceID);
}
