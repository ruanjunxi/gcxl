package com.example.gcxl.domain.query;

import com.example.gcxl.domain.InfoPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
@ApiModel("查询条件")
public class ContractCondition {
    @NotNull(message = "分页对象不能为空")
    @Valid
    InfoPage pageInfo;
    @ApiModelProperty("查询年月")
    String date;
    @ApiModelProperty("商品名称")
//    @Length(max = 10, message = "商品名称不能超过10")
    String nameCommodity;
    @ApiModelProperty("型号")
    String model;
//    @Length(max = 10, message = "合同编号不能超过10")
    @ApiModelProperty("合同编号")
    String contractId;
//    @NotBlank(message = "采购单位不能为空")
    @ApiModelProperty("采购单位")
    String purchasingUnit;
    @ApiModelProperty("备注")
    String comment;
    @ApiModelProperty("发票状态")
    String invoiceState;
//    @ApiModelProperty("分页号")
//    int pageNum;
//    @ApiModelProperty("分页大小")
//    int pageSize;

}
