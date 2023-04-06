package com.example.gcxl.domain.query;

import com.example.gcxl.domain.ContractBelongTo;
import com.example.gcxl.domain.SaleContract;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
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
public class ContractBelongToResult {
    PageInfo<ContractBelongTo> allPagContractBelongTo;
    Integer Num;
}
