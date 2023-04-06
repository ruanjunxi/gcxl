package com.example.gcxl.domain;

import com.example.gcxl.util.helper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.text.ParseException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
@Data
//@NoArgsConstructor
//@AllArgsConstructor
@ApiModel("销售合同")
public class SaleContract {
   @ApiModelProperty("合同编号")
   private String contractID;
   @ApiModelProperty("合同所属")
   private String contractBelongsTo;
   @ApiModelProperty("区域标识")
   private String region;
   @ApiModelProperty("合同日期")
   //@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
   private String dateContract;
   @ApiModelProperty("采购单位")
   private String purchasingUnit;
   @ApiModelProperty("合同金额")
   private float contractAmount;
   @ApiModelProperty("备注")
   private String comment;
   @ApiModelProperty("附件")
   private String attachment;
   @ApiModelProperty("关联率")
   private float correlationRate;
   @ApiModelProperty("发票状态")
   private String invoiceState;
   @ApiModelProperty("合同创建时间")
   private String creationDate;
   @ApiModelProperty("创建者")
   private String creator;
   @ApiModelProperty("修改次数")
   private int updateFrequency = 0;

   public SaleContract(String contractID, String contractBelongsTo, String region, String dateContract, String purchasingUnit, float contractAmount, String comment , String attachment) {
      this.contractID = contractID;
      this.contractBelongsTo = contractBelongsTo;
      this.region = region;
      this.dateContract = dateContract;
      this.purchasingUnit = purchasingUnit;
      this.contractAmount = contractAmount;
      this.comment = comment;
      this.attachment = attachment;
   }

   public SaleContract() {
   }

   public SaleContract(String contractID, String contractBelongsTo, String region, String dateContract, String purchasingUnit,
                       float contractAmount, String comment, String attachment, float correlationRate, String invoiceState,
                       Date creationDate, String creator) throws ParseException {
      helper help = new helper();
      this.contractID = contractID;
      this.contractBelongsTo = contractBelongsTo;
      this.region = region;
      this.dateContract = dateContract;
      this.purchasingUnit = purchasingUnit;
      this.contractAmount = contractAmount;
      this.comment = comment;
      this.attachment = attachment;
      this.correlationRate = correlationRate;
      this.invoiceState = invoiceState;
      this.creationDate = help.dateConversion(creationDate);
      this.creator = creator;
   }

}
