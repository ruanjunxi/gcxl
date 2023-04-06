package com.example.gcxl.domain.query;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("关联明细")
public class RelationDetail {
    @NotNull(message = "产品id不能为空")
    @ApiModelProperty("id")
    private int id;
    @ApiModelProperty("采购发票编号")
    private String PurchaseInvoiceID;
    @ApiModelProperty("采购合同编号")
    private String contractID;
    @ApiModelProperty("品名")
    private String tradeName;
    @ApiModelProperty("商品名称")
    private String nameCommodity;
    @ApiModelProperty("型号")
    private String model;
    @ApiModelProperty("单价")
    private double unitPrice;
    @ApiModelProperty("剩余数量=采购数量-关联数量")
    private int numberRemain;
    @NotNull(message = "关联数量不能为空")
    @ApiModelProperty("关联数量")
    private int correlationNumber;

    @NotNull(message = "销售发票编号不能为空")
    @ApiModelProperty("销售发票编号")
    private String SaleInvoiceID;

}
