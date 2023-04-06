package com.example.gcxl.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: OutBound
 * @author: Eason
 * @data: 2022/4/18 8:52
 */
@Data
@ApiModel("出库")
public class OutBound implements Serializable {
    @ApiModelProperty("出库编号")
    private String outboundID;
    @ApiModelProperty("出库日期")
    private String deliveryDate;
    @ApiModelProperty("出库类型")
    private String outBoundType;
    @ApiModelProperty("创建者")
    private String account;
    @ApiModelProperty("创建日期")
    private String creationDate;
    @ApiModelProperty("状态")
    private String state;

    public OutBound() {
    }

    public OutBound(String outboundID, String deliveryDate, String outBoundType, String account, String creationDate, String state) {
        this.outboundID = outboundID;
        this.deliveryDate = deliveryDate;
        this.outBoundType = outBoundType;
        this.account = account;
        this.creationDate = creationDate;
        this.state = state;
    }

    public OutBound(String outboundID, String deliveryDate, String outBoundType, String account, String state) {
        this.outboundID = outboundID;
        this.deliveryDate = deliveryDate;
        this.outBoundType = outBoundType;
        this.account = account;
        this.state = state;
    }

}
