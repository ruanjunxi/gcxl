package com.example.gcxl.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: PurchaseInvoiceDetail
 * @author: Eason
 * @data: 2022/4/18 8:44
 */
@Data
@ApiModel("采购发票明细")
public class PurchaseInvoiceDetail implements Serializable {
    @ApiModelProperty("采购发票明细id")
    private int id;
    @ApiModelProperty("发票编号")
    private String invoiceID;
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
    @ApiModelProperty("发票金额=采购数量*单价")
    private double amount;
    @ApiModelProperty("关联数量")
    private int correlationNumber;
    @ApiModelProperty("出库数量")
    private int outboundNumber;
    @ApiModelProperty("明细更新次数")
    private int detailUpdateFrequency;

    public PurchaseInvoiceDetail(String invoiceID, String tradeName, String nameCommodity, String model, int privateIntAmount,
                                 double unitPrice, int numberRemain, double amount, int correlationNumber, int outboundNumber,int detailUpdateFrequency) {
        this.invoiceID = invoiceID;
        this.tradeName = tradeName;
        this.nameCommodity = nameCommodity;
        this.model = model;
        this.privateIntAmount = privateIntAmount;
        this.unitPrice = unitPrice;
        this.numberRemain = numberRemain;
        this.amount = amount;
        this.correlationNumber = correlationNumber;
        this.outboundNumber = outboundNumber;
        this.detailUpdateFrequency=detailUpdateFrequency;
    }

    public PurchaseInvoiceDetail(int id, String tradeName, String nameCommodity, String model, int privateIntAmount, double unitPrice, int numberRemain, double amount, int correlationNumber, int outboundNumber,int  detailUpdateFrequency) {
        this.id = id;
        this.tradeName = tradeName;
        this.nameCommodity = nameCommodity;
        this.model = model;
        this.privateIntAmount = privateIntAmount;
        this.unitPrice = unitPrice;
        this.numberRemain = numberRemain;
        this.amount = amount;
        this.correlationNumber = correlationNumber;
        this.outboundNumber = outboundNumber;
        this.detailUpdateFrequency=detailUpdateFrequency;
    }
}
