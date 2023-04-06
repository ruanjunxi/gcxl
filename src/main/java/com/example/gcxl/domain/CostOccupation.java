package com.example.gcxl.domain;

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
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("成本占用管理页面的展示结果")
public class CostOccupation {
    @ApiModelProperty("采购单位")
     String purchasingUnit;
    @ApiModelProperty("销售合同")
     String ContractId;
    @ApiModelProperty("销售发票编号")
     String invoiceID;
    @ApiModelProperty("开票日期")
     String invoiceDate;
    @ApiModelProperty("发票类型")
     String invoiceType;
    @ApiModelProperty("发票金额")
     String amount;
    @ApiModelProperty("关联率")
     Float correlationRate;
    @ApiModelProperty("状态")
     String status;
    @ApiModelProperty("操作")
    String operation;
}
