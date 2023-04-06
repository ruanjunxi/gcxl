package com.example.gcxl.domain.query;

import com.example.gcxl.domain.InfoPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("所属查询条件")
public class ContractBelongToCondition {
    @NotNull(message = "分页对象不能为空")
    @Valid
    InfoPage pageInfo;
    @ApiModelProperty("所属名称")
    String name;
    @ApiModelProperty("状态")
    String status;
}
