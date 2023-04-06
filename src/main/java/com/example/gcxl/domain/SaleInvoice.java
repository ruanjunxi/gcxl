package com.example.gcxl.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
@ApiModel("销售发票")
@Data
public class SaleInvoice {
    @NotNull(message = "发票编号不能为空")
    @Valid
    @ApiModelProperty("发票编号")
    String invoiceID;
    @ApiModelProperty("开票日期")
    String invoiceDate;
    @ApiModelProperty("发票类型")
    String invoiceType;
    @ApiModelProperty("附件")
    String attachment;
    @ApiModelProperty("发票更新次数")
    int updateFrequency;
    @ApiModelProperty("创建日期")
    String creationDate;
    @ApiModelProperty("创建者")
    String creator;
    @NotNull(message = "发票对应的合同编号不能为空")
    @ApiModelProperty("合同编号")
    String contractID;
    @NotNull(message = "发票金额不能为空")
    @ApiModelProperty("发票金额")
    Float amount;
    @ApiModelProperty("发票明细")
    List<SaleInvoiceDetail> detailList;
    String purchasingUnit;
    Float correlationRate;
    String status;
    //无amount的构造函数
    public SaleInvoice(String invoiceID, String invoiceDate, String invoiceType, String attachment, String contractID, List<SaleInvoiceDetail> detailList) {
        this.invoiceID = invoiceID;
        this.invoiceDate = invoiceDate;
        this.invoiceType = invoiceType;
        this.attachment = attachment;
        this.contractID = contractID;
        this.amount = 0f;
        for (SaleInvoiceDetail item:detailList
             ) {
            this.amount+=item.getAmount();
        }
        this.detailList = detailList;
        this.updateFrequency = 0;
    }
    //有amount的构造函数
    public SaleInvoice(String invoiceID, String invoiceDate, String invoiceType, String attachment, String contractID, Float amount, List<SaleInvoiceDetail> detailList) {
        this.invoiceID = invoiceID;
        this.invoiceDate = invoiceDate;
        this.invoiceType = invoiceType;
        this.attachment = attachment;
        this.contractID = contractID;
        this.amount = amount;
        this.detailList = detailList;
        this.updateFrequency = 0;
    }

    public SaleInvoice() {
    }

    public SaleInvoice(String invoiceID, String invoiceDate, String invoiceType, String attachment, String creationDate, String creator, String contractID,  Float amount,List<SaleInvoiceDetail> detailList) {
        this.invoiceID = invoiceID;
        this.invoiceDate = invoiceDate;
        this.invoiceType = invoiceType;
        this.attachment = attachment;
        this.creationDate = creationDate;
        this.creator = creator;
        this.contractID = contractID;
        this.amount = amount;
        this.detailList = detailList;
        this.updateFrequency = 0;
    }

    public SaleInvoice(String invoiceID, String invoiceDate, String invoiceType, String attachment, int updateFrequency, String creationDate, String creator, Float amount) {
        this.invoiceID = invoiceID;
        this.invoiceDate = invoiceDate;
        this.invoiceType = invoiceType;
        this.attachment = attachment;
        this.updateFrequency = updateFrequency;
        this.creationDate = creationDate;
        this.creator = creator;
        this.amount = amount;
    }

    public SaleInvoice(String invoiceID, String invoiceDate, String invoiceType, String creationDate, String creator, Float amount, String purchasingUnit) {
        this.invoiceID = invoiceID;
        this.invoiceDate = invoiceDate;
        this.invoiceType = invoiceType;
        this.creationDate = creationDate;
        this.creator = creator;
        this.amount = amount;
        this.purchasingUnit = purchasingUnit;
    }

    public SaleInvoice(String invoiceID, String invoiceDate, String invoiceType, String attachment, int updateFrequency, String creationDate, String creator, String contractID, Float amount, List<SaleInvoiceDetail> detailList, String purchasingUnit, Float correlationRate, String status) {
        this.invoiceID = invoiceID;
        this.invoiceDate = invoiceDate;
        this.invoiceType = invoiceType;
        this.attachment = attachment;
        this.updateFrequency = updateFrequency;
        this.creationDate = creationDate;
        this.creator = creator;
        this.contractID = contractID;
        this.amount = amount;
        this.detailList = detailList;
        this.purchasingUnit = purchasingUnit;
        this.correlationRate = correlationRate;
        this.status = status;
    }
}
