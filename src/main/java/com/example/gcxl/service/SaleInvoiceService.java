package com.example.gcxl.service;

import com.example.gcxl.domain.SaleContract;
import com.example.gcxl.domain.SaleInvoice;
import com.example.gcxl.domain.SaleInvoiceDetail;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
public interface SaleInvoiceService extends BaseService{
    void AddInvoice(SaleInvoice invoice);

    void addBatchInvoiceDetail(List<SaleInvoiceDetail> detailList);

    //根据发票id就修改信息
    void updateInvoiceById(String invoiceID, String date, String type, String account);

    //修改发票的明细
    void updateInvoiceDetailById(SaleInvoiceDetail saleInvoiceDetail, String account);

    //根据合同编号查询发票
    List<SaleInvoice> getSaleInvoiceByContractId(String contractId);


    List<SaleInvoice> getSaleInvoiceByCondition(String invoiceId, String purchasingUnit, String year, String month);

    List<SaleInvoiceDetail> getSaleInvoiceDetailByInvoiceId(String invoiceId);
}
