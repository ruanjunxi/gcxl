package com.example.gcxl.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
@Data
@ApiModel("成本占用表")
public class TakeUp {
    Integer id;
    String invoiceID;
    String tradeName;
    String nameCommodity;
    String model;
    double unitPrice;
    int associatedNumber;
    String purchaseInvoiceID;
    String purchaseContractID;
    String operator;
    String operationDate;
    String takeUpType;
    String status;
    int commodityID;

    //做关联的时候
    public TakeUp(PurchaseInvoiceDetail detail,String saleInvoiceID,String purchaseContractID,String operator,int associatedNumber) {
        this.invoiceID=saleInvoiceID;
        this.tradeName=detail.getTradeName();
        this.nameCommodity=detail.getNameCommodity();
        this.model=detail.getModel();
        this.unitPrice=detail.getUnitPrice();
        this.associatedNumber=associatedNumber;
        this.purchaseContractID=purchaseContractID;
        this.purchaseInvoiceID=detail.getInvoiceID();
        this.operator=operator;
        this.commodityID=detail.getId();
    }

    public TakeUp() {
    }
    public TakeUp(SaleInvoiceDetail detail) {
        this.tradeName=detail.getTradeName();
        this.nameCommodity=detail.getNameCommodity();
        this.model=detail.getModel();
        this.unitPrice=detail.getUnitPrice();
        this.associatedNumber=detail.getPrivateIntAmount();
    }

    public TakeUp(String saleInvoiceID, String tradeName, String nameCommodity, String model, double unitPrice,
                  int associatedNumber, String purchaseInvoiceID, String purchaseContractID, String operator,
                  String operationDate, String takeUpType, String status, int commodityID) {
        this.invoiceID = saleInvoiceID;
        this.tradeName = tradeName;
        this.nameCommodity = nameCommodity;
        this.model = model;
        this.unitPrice = unitPrice;
        this.associatedNumber = associatedNumber;
        this.purchaseInvoiceID = purchaseInvoiceID;
        this.purchaseContractID = purchaseContractID;
        this.operator = operator;
        this.operationDate = operationDate;
        this.takeUpType = takeUpType;
        this.status = status;
        this.commodityID=commodityID;
    }
//取消关联时
    public TakeUp(Integer id, String saleInvoiceID, String tradeName, String nameCommodity, String model,
                  double unitPrice, int associatedNumber, String purchaseInvoiceID, String purchaseContractID,
                  String operator, String operationDate, String takeUpType, String status, int commodityID) {
        this.id = id;
        this.invoiceID = saleInvoiceID;
        this.tradeName = tradeName;
        this.nameCommodity = nameCommodity;
        this.model = model;
        this.unitPrice = unitPrice;
        this.associatedNumber = associatedNumber;
        this.purchaseInvoiceID = purchaseInvoiceID;
        this.purchaseContractID = purchaseContractID;
        this.operator = operator;
        this.operationDate = operationDate;
        this.takeUpType = takeUpType;
        this.status = status;
        this.commodityID = commodityID;
    }
}
