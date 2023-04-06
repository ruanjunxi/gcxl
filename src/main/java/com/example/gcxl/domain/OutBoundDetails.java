package com.example.gcxl.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: OutBoundDetails
 * @author: Eason
 * @data: 2022/4/18 8:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("出库明细")
public class OutBoundDetails implements Serializable {
    @ApiModelProperty("采购发票明细id")
    private int id;
    @ApiModelProperty("出库编号")
    private String outboundID;
    @ApiModelProperty("品名")
    private String tradeName;
    @ApiModelProperty("商品名称")
    private String nameCommodity;
    @ApiModelProperty("型号")
    private String model;
    @ApiModelProperty("单价")
    private double unitPrice;
    @ApiModelProperty("剩余数量")
    private int numberRemain;
    @ApiModelProperty("出库数量")
    private int outboundNumber;
    @ApiModelProperty("发票编号")
    private String invoiceID;
    @ApiModelProperty("合同编号")
    private String contractID;
}
