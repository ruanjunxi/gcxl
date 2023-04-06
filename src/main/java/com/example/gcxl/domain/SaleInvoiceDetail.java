package com.example.gcxl.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
@ApiModel("销售发票明细")
@Data
public class SaleInvoiceDetail {
    @ApiModelProperty("明细id")
    long id;
    @ApiModelProperty("发票号")
//    @NotNull(message = "明细对应的发票号不能为空")
    String invoiceID;
    @ApiModelProperty("品牌名")
    String tradeName;
    @ApiModelProperty("商品名称")
    String nameCommodity;
    @ApiModelProperty("型号")
    String model;
    @ApiModelProperty("采购数量")
    int privateIntAmount;
    @ApiModelProperty("采购单价")
    float unitPrice;
    @NotNull(message = "明细的采购总金额不能为空")
    @ApiModelProperty("采购总额")
    float amount;
    @ApiModelProperty("更新次数")
    int updateFrequency;

    //从数据库中获取时，需要id
    public SaleInvoiceDetail(long id,String invoiceID, String tradeName, String nameCommodity, String model, int privateIntAmount, float unitPrice,int updateFrequency) {
        this.id=id;
        this.invoiceID = invoiceID;
        this.tradeName = tradeName;
        this.nameCommodity = nameCommodity;
        this.model = model;
        this.privateIntAmount = privateIntAmount;
        this.unitPrice = unitPrice;
        this.amount = privateIntAmount*unitPrice;
        this.updateFrequency=updateFrequency;
    }
    //添加明细时，不需要id,但需要发票号
    public SaleInvoiceDetail(String invoiceID, String tradeName, String nameCommodity, String model, int privateIntAmount, float unitPrice,Float amount) {
        this.invoiceID = invoiceID;
        this.tradeName = tradeName;
        this.nameCommodity = nameCommodity;
        this.model = model;
        this.privateIntAmount = privateIntAmount;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.updateFrequency=0;
    }
    //修改明细时，需要id，不带发票id
    public SaleInvoiceDetail(long id,String tradeName, String nameCommodity, String model, int privateIntAmount, float unitPrice,Float amount) {
        this.id=id;
        this.tradeName = tradeName;
        this.nameCommodity = nameCommodity;
        this.model = model;
        this.privateIntAmount = privateIntAmount;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.updateFrequency=0;
    }
//    //添加明细时，不需要id，不带发票id,带有总金额
//    public SaleInvoiceDetail(String tradeName, String nameCommodity, String model, int privateIntAmount, float unitPrice,Float amount) {
//        this.tradeName = tradeName;
//        this.nameCommodity = nameCommodity;
//        this.model = model;
//        this.privateIntAmount = privateIntAmount;
//        this.unitPrice = unitPrice;
//        this.amount = amount;
//        this.updateFrequency=0;
//    }
    public SaleInvoiceDetail() {
    }
}
