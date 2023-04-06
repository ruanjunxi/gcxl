package com.example.gcxl.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName: RelatedSales
 * @author: Eason
 * @data: 2022/5/3 19:28
 */
@Data
public class RelatedSales {
    @ApiModelProperty("类型")
    private String type;
    @ApiModelProperty("关联数量")
    private int associatedNumber;
    @ApiModelProperty("发票编号")
    private String invoiceID;
    @ApiModelProperty("合同编号")
    private String contractID;
    @ApiModelProperty("采购单位")
    private String purchasingUnit;
    @ApiModelProperty("创建者")
    private String operator;
    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date operationDate;
}
