package com.example.gcxl.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: PurchaseInvoiceWithDetail
 * @author: Eason
 * @data: 2022/4/18 21:23
 */
@Data
@ApiModel("采购发票+明细")
public class PurchaseInvoiceWithDetail implements Serializable {
    @ApiModelProperty("发票编号")
    private String invoiceID;
    @ApiModelProperty("开票日期")
    private String makeOutAnInvoiceDate;
    @ApiModelProperty("发票类型")
    private String invoiceType;
    @ApiModelProperty("创建者")
    private String account;
    @ApiModelProperty("发票更新次数")
    private int invoiceUpdateFrequency;
    @ApiModelProperty("明细更新次数")
    private int detailUpdateFrequency;
    @ApiModelProperty("合同编号")
    private String contractID;
    @ApiModelProperty("发票金额")
    private double allAmount;

    @ApiModelProperty("id")
    private int id;
    @ApiModelProperty("品名")
    private String tradeName;
    @ApiModelProperty("商品名称")
    private String nameCommodity;
    @ApiModelProperty("型号")
    private String model;
    @ApiModelProperty("采购数量")
    private int privateIntAmount;
    @ApiModelProperty("单价")
    private double unitPrice;
    @ApiModelProperty("剩余数量=采购数量-关联数量")
    private int numberRemain;
    @ApiModelProperty("发票明细金额=采购数量*单价")
    private double amount;
    @ApiModelProperty("关联数量")
    private int correlationNumber;
    @ApiModelProperty("出库数量")
    private int outboundNumber;

    public PurchaseInvoiceWithDetail() {
    }

    public PurchaseInvoiceWithDetail(PurchaseInvoice purchaseInvoice, PurchaseInvoiceDetail purchaseInvoiceDetail){
        this.invoiceID = purchaseInvoice.getInvoiceID();
        this.makeOutAnInvoiceDate = purchaseInvoice.getMakeOutAnInvoiceDate();
        this.invoiceType = purchaseInvoice.getInvoiceType();
        this.account = purchaseInvoice.getAccount();
        this.invoiceUpdateFrequency = purchaseInvoice.getInvoiceUpdateFrequency();
        this.detailUpdateFrequency = purchaseInvoiceDetail.getDetailUpdateFrequency();
        this.contractID = purchaseInvoice.getContractID();
        this.allAmount = purchaseInvoice.getAllAmount();
        this.id = purchaseInvoiceDetail.getId();
        this.tradeName = purchaseInvoiceDetail.getTradeName();
        this.nameCommodity = purchaseInvoiceDetail.getNameCommodity();
        this.model = purchaseInvoiceDetail.getModel();
        this.privateIntAmount = purchaseInvoiceDetail.getPrivateIntAmount();
        this.unitPrice = purchaseInvoiceDetail.getUnitPrice();
        this.numberRemain = purchaseInvoiceDetail.getNumberRemain();
        this.amount = purchaseInvoiceDetail.getAmount();
        this.correlationNumber = purchaseInvoiceDetail.getCorrelationNumber();
        this.outboundNumber = purchaseInvoiceDetail.getOutboundNumber();
    }
}
