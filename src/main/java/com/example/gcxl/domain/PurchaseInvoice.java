package com.example.gcxl.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: PurchaseInvoice
 * @author: Eason
 * @data: 2022/4/18 8:34
 */
@Data
@ApiModel("采购发票")
public class PurchaseInvoice implements Serializable {
    @ApiModelProperty("发票编号")
    private String invoiceID;
    @ApiModelProperty("开票日期")
    private String makeOutAnInvoiceDate;
    @ApiModelProperty("发票类型")
    private String invoiceType;
    @ApiModelProperty("创建者")
    private String account;
    @ApiModelProperty("创建日期")
    private String creationDate;
    @ApiModelProperty("发票更新次数")
    private int invoiceUpdateFrequency;
//    @ApiModelProperty("明细更新次数")
//    private int detailUpdateFrequency;
    @ApiModelProperty("合同编号")
    private String contractID;
    @ApiModelProperty("发票金额")
    private double allAmount;
    @ApiModelProperty("附件个数")
    private int numberAttachments;

    public PurchaseInvoice() {
    }

    public PurchaseInvoice(String invoiceID, String makeOutAnInvoiceDate, String invoiceType, String account, String creationDate, int invoiceUpdateFrequency, String contractID, double allAmount,int numberAttachments) {
        this.invoiceID = invoiceID;
        this.makeOutAnInvoiceDate = makeOutAnInvoiceDate;
        this.invoiceType = invoiceType;
        this.account = account;
        this.creationDate = creationDate;
        this.invoiceUpdateFrequency = invoiceUpdateFrequency;
        this.contractID = contractID;
        this.allAmount = allAmount;
        this.numberAttachments=numberAttachments;
    }

    public PurchaseInvoice(String invoiceID, String makeOutAnInvoiceDate, String invoiceType, String account, int invoiceUpdateFrequency, String contractID, double allAmount,int numberAttachments) {
        this.invoiceID = invoiceID;
        this.makeOutAnInvoiceDate = makeOutAnInvoiceDate;
        this.invoiceType = invoiceType;
        this.account = account;
        this.invoiceUpdateFrequency = invoiceUpdateFrequency;
        this.contractID = contractID;
        this.allAmount = allAmount;
        this.numberAttachments=numberAttachments;
    }
}
