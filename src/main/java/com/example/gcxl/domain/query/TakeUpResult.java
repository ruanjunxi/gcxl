package com.example.gcxl.domain.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
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
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("发票关联页面,查询的返回结果")
public class TakeUpResult {
    @ApiModelProperty("takeUpID")
    Integer id;
    @ApiModelProperty("采购单位")
    String supplier;
    @ApiModelProperty("品名")
    String tradeName;
    @ApiModelProperty("商品名")
    String nameCommodity;
    @ApiModelProperty("型号")
    String model;
    @ApiModelProperty("单价")
    Float unitPrice;
    @ApiModelProperty("剩余数量")
    Integer numberRemain;
    @ApiModelProperty("发票编号")
    String invoiceID;
    @ApiModelProperty("合同编号")
    String contractID;
    @ApiModelProperty("备注信息")
    String  noteInformation;
}
