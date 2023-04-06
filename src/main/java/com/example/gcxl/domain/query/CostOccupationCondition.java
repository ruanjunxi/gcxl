package com.example.gcxl.domain.query;

import com.example.gcxl.domain.InfoPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("成本占用管理查询条件")
public class CostOccupationCondition {
    @NotNull(message = "分页信息不能为空")
    InfoPage pageInfo;
    @ApiModelProperty("合同编号")
    String contractId;
    @ApiModelProperty("采购单位")
    String purchasingUnit;
    @ApiModelProperty("发票编号")
    String invoiceId;
    @ApiModelProperty("占用率下界")
    float lowCorrelationRate;
    @ApiModelProperty("占用率下界")
    float highCorrelationRate;
    @ApiModelProperty("状态")
    String status;

//    public CostOccupationCondition() {
//        this.setHighCorrelationRate(Float.MAX_VALUE);
//        this.setLowCorrelationRate(0);
//        this.pageInfo.setPageNum(1);
//        this.pageInfo.setPageSize(15);
//    }
//
//    public CostOccupationCondition(String contractId, String purchasingUnit, String invoiceId, float lowCorrelationRate, float highCorrelationRate, String status, InfoPage pageInfo) {
//        this.contractId = contractId;
//        this.purchasingUnit = purchasingUnit;
//        this.invoiceId = invoiceId;
//        this.lowCorrelationRate = lowCorrelationRate;
//        this.highCorrelationRate = highCorrelationRate;
//        this.status = status;
//        this.pageInfo = pageInfo;
//    }
//
//    public CostOccupationCondition(String contractId, String purchasingUnit, String invoiceId, String status,InfoPage pageInfo) {
//        this.contractId = contractId;
//        this.purchasingUnit = purchasingUnit;
//        this.invoiceId = invoiceId;
//        this.status = status;
//        this.lowCorrelationRate = Float.MAX_VALUE;
//        this.highCorrelationRate = 0;
//        this.pageInfo=pageInfo;
//    }
//    public CostOccupationCondition(String json) throws IOException {
//        CostOccupationCondition condition = new ObjectMapper().readValue(json, CostOccupationCondition.class);
//        this.contractId = condition.getContractId();
//        this.purchasingUnit = condition.getPurchasingUnit();
//        this.invoiceId = condition.getInvoiceId();
//        this.lowCorrelationRate = condition.getLowCorrelationRate();
//        this.highCorrelationRate = condition.getHighCorrelationRate();
//        this.status = condition.getStatus();
////        this.pageInfo=pageInfo;
//}
}
