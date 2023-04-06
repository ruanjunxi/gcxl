package com.example.gcxl.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: ModifyLogWithDetails
 * @author: Eason
 * @data: 2022/4/19 14:25
 */
@Data
@ApiModel("修改日志+明细")
public class ModifyLogWithDetails implements Serializable {
    @ApiModelProperty("id")
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

//    private String fieldNames;
//    private String originalValue;
//    private String newValue;
    @ApiModelProperty("对应的明细列表")
    private List<ModifyLogDetails> modifyLogDetailsList;

    public ModifyLogWithDetails(ModifyLog modifyLog,List<ModifyLogDetails> modifyLogDetailsList){
        this.id=modifyLog.getId();
        this.operationType=modifyLog.getOperationType();
        this.account=modifyLog.getAccount();
        this.creationDate=modifyLog.getCreationDate();
        this.type=modifyLog.getType();
        this.typeID=modifyLog.getTypeID();
        this.otherID=modifyLog.getOtherID();
        this.modifyLogDetailsList=modifyLogDetailsList;
    }
}
