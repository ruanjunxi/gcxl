package com.example.gcxl.domain.query;

import com.example.gcxl.domain.SaleContract;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("返回结果")
public class ContractResult {
    PageInfo<SaleContract> allPagContract;
    Float allContractAmount;
    int contractNum;
}
