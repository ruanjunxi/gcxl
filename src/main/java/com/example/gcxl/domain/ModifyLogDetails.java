package com.example.gcxl.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("修改明细")

public class ModifyLogDetails implements Serializable {
    @ApiModelProperty("外键ID")
    private int id;
    @ApiModelProperty("字段名")
    private String fieldNames;
    @ApiModelProperty("原始值")
    private String originalValue;
    @ApiModelProperty("新值")
    private String newValue;
}
