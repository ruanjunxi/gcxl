package com.example.gcxl.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
@Data
@ApiModel("合同所属类")
public class ContractBelongTo {
        Integer id;
    @ApiModelProperty("名称")
        String name;
    @ApiModelProperty("联系人")
        String contact;
    @ApiModelProperty("号码")
        String phoneNumber;
    @ApiModelProperty("状态")
        String status;
    @ApiModelProperty("创建日期")
        String creationDate;
    @ApiModelProperty("创建者")
        String creator;

    public ContractBelongTo(String name, String contact, String phoneNumber, String status, String creationDate, String creator) {
        this.name = name;
        this.contact = contact;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.creationDate = creationDate;
        this.creator = creator;
    }

    public ContractBelongTo() {
    }

    public ContractBelongTo(Integer id, String name, String contact, String phoneNumber, String status, String creationDate, String creator) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.creationDate = creationDate;
        this.creator = creator;
    }
}
