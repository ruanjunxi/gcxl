package com.example.gcxl.domain.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("核销查询：具体销售合同下的信息")
public class BusinessQueryResult {
    @ApiModelProperty("采购合同ID")
    String purchasecontractID;
    @ApiModelProperty("供应商")
    String supplier;
    @ApiModelProperty("发票编号")
    String invoiceID;
    @ApiModelProperty("开票日期")
    String invoiceDate;
    @ApiModelProperty("品名")
    String tradeName;
    @ApiModelProperty("商品名称")
    String nameCommodity;
    @ApiModelProperty("型号")
    String model;
    @ApiModelProperty("单价")
    Float unitPrice;
    @ApiModelProperty("数量")
    Integer privateIntAmount;
    @ApiModelProperty("金额")
    Float amount;
    @ApiModelProperty("操作者")
    String operator;
    @ApiModelProperty("操作日期")
    String creationDate;
    @ApiModelProperty("发票类型")
    String invoiceType;

//    public BusinessQueryResult(String invoiceID, String invoiceDate, String tradeName,
//                               String nameCommodity, String model, Float unitPrice, Integer privateIntAmount, Float amount,
//                               String account, String creationDate,String invoiceType) {
//        this.invoiceID = invoiceID;
//        this.invoiceDate = invoiceDate;
//        this.tradeName = tradeName;
//        this.nameCommodity = nameCommodity;
//        this.model = model;
//        this.unitPrice = unitPrice;
//        this.privateIntAmount = privateIntAmount;
//        this.amount = amount;
//        this.operator = account;
//        this.creationDate = creationDate;
//        this.invoiceType=invoiceType;
//    }
//    public BusinessQueryResult(String purchasecontractID, String supplier, String purchaseInvoiceID, String makeOutAnInvoiceDate, String tradeName,
//                               String nameCommodity, String model, Float unitPrice, Integer privateIntAmount, Float amount,
//                               String operator, String operationDate,String invoiceType) {
//        this.purchasecontractID = purchasecontractID;
//        this.supplier = supplier;
//        this.invoiceID = purchaseInvoiceID;
//        this.invoiceDate = makeOutAnInvoiceDate;
//        this.tradeName = tradeName;
//        this.nameCommodity = nameCommodity;
//        this.model = model;
//        this.unitPrice = unitPrice;
//        this.privateIntAmount = privateIntAmount;
//        this.amount = amount;
//        this.operator = operator;
//        this.creationDate = operationDate;
//        this.invoiceType=invoiceType;
//    }

//    public BusinessQueryResult() {
//    }
}
