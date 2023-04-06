package com.example.gcxl.service;

import com.example.gcxl.domain.*;

import java.util.List;

/**
 * @ClassName: PurchaseInvoiceService
 * @author: Eason
 * @data: 2022/4/18 16:58
 */
public interface PurchaseInvoiceService extends BaseService {
//    插入采购发票 修改合同上的发票状态
    void insertPurchaseInvoice(PurchaseInvoice purchaseInvoice);
//    插入采购发票明细
    void insertPurchaseInvoiceDetails(PurchaseInvoiceDetail purchaseInvoiceDetail);
//    修改采购发票,修改合同上的发票状态
    void updatePurchaseInvoice(String newInvoiceID,String newMakeOutAnInvoiceDate,String newInvoiceType,String oldInvoiceID,
                               String account);
//    修改采购发票明细，修改采购发票上的金额，修改明细次数加1，修改合同上的发票状态
    void updatePurchaseInvoiceDetail(PurchaseInvoiceDetail purchaseInvoiceDetail,String account);
//    显示所有采购发票及其明细
    List<PurchaseInvoiceHaveDetails> getAllPurchaseInvoiceWithDetail(String contract);
//    批量插入
    void bulkInsertPurchasingInvoiceDetail(List<PurchaseInvoiceDetail> purchaseInvoiceDetails);
}
