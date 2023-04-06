package com.example.gcxl.service;

import com.example.gcxl.domain.CostOccupation;
import com.example.gcxl.domain.SaleInvoiceDetail;
import com.example.gcxl.domain.TakeUp;
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
public interface FareAdjustmentService extends BaseService{
    List<CostOccupation> findAllFareAdjustmentByCondition(CostOccupationCondition condition);

    void commodityRelated(List<RelationDetail> details, String usernameFromSession);

    void complete(String invoiceID);

    void recall(String invoiceID);
}
