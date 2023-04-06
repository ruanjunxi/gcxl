package com.example.gcxl.domain.query;

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
@ApiModel("核销查询首页返回结果")
public class WriteOffResult {
    @ApiModelProperty("采购单位")
    String purchasingUnit;
    @ApiModelProperty("合同ID")
    String contractID;
    @ApiModelProperty("合同日期")
    String dateContract;
    @ApiModelProperty("合同金额")
    Float contractAmount;
    @ApiModelProperty("关联率")
    Float correlationRate;
    @ApiModelProperty("关联状态")
    String relationStatus;
    @ApiModelProperty("发票状态")
    String invoiceState;
    @ApiModelProperty("数量")
    Integer counts;

    public WriteOffResult(String purchasingUnit, String contractID, String dateContract, Float contractAmount, Float correlationRate, String status, String invoiceState) {
        this.purchasingUnit = purchasingUnit;
        this.contractID = contractID;
        this.dateContract = dateContract;
        this.contractAmount = contractAmount;
        this.correlationRate = correlationRate;
        this.relationStatus = status;
        this.invoiceState = invoiceState;
    }

    public WriteOffResult() {
    }
}
