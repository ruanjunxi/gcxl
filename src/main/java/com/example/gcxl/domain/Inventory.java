package com.example.gcxl.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Inventory
 * @author: Eason
 * @data: 2022/4/23 14:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("库存类")
public class Inventory {
    @ApiModelProperty("合同编号")
    private String contractID;
    @ApiModelProperty("采购发票明细id")
    private int id;
    @ApiModelProperty("发票编号")
    private String invoiceID;
    @ApiModelProperty("开票日期")
    private String makeOutAnInvoiceDate;
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
    @ApiModelProperty("发票类型")
    private String invoiceType;
    @ApiModelProperty("备注信息")
    private String noteInformation;
}
