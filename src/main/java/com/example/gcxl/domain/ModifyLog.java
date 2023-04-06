package com.example.gcxl.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: modifylog
 * @author: Eason
 * @data: 2022/4/18 11:12
 */
@Data
@ApiModel("修改日志")

public class ModifyLog implements Serializable {
    @ApiModelProperty("修改日志ID")
    private int id;
    @ApiModelProperty("操作类型（修改）")
    private String operationType;
    @ApiModelProperty("操作者，从session中获得")
    private String account;
    @ApiModelProperty("创建日期")
    private String creationDate;
    @ApiModelProperty("对应于标题，格式为（采购发票：ID）")
    private String type;
    @ApiModelProperty("对应的采购发票、合同、明细ID")
    private String typeID;
    @ApiModelProperty("外键ID")
    private String otherID;

    public ModifyLog(String operationType, String account, String creationDate, String type, String typeID, String otherID) {
        this.operationType = operationType;
        this.account = account;
        this.creationDate = creationDate;
        this.type = type;
        this.typeID = typeID;
        this.otherID = otherID;
    }

    public ModifyLog(String operationType, String account, String type, String typeID, String otherID) {
        this.operationType = operationType;
        this.account = account;
        this.type = type;
        this.typeID = typeID;
        this.otherID = otherID;
    }



    public ModifyLog(String operationType, String account, String type, String typeID) {
        this.operationType = operationType;
        this.account = account;
        this.type = type;
        this.typeID = typeID;
    }
}
