package com.example.gcxl.service;

import com.example.gcxl.domain.ModifyLogWithDetails;
import com.example.gcxl.domain.query.ContractCondition;
import com.example.gcxl.domain.SaleContract;
import com.example.gcxl.domain.query.WriteOffResult;
import com.github.pagehelper.PageInfo;


import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
public interface SaleContractService extends BaseService{
    void AddContract(SaleContract saleContract);

    void modifyContract(String contractBelongTo, String region, String newContractId,
                        String contractDate, String purchasingUnit, float contractAmount, String comment, String account);

    List<SaleContract> findAllContract();

    PageInfo<SaleContract> findAllPageSaleContract(int pageNum, int pageSize, ContractCondition condition);

    SaleContract findContractById(String contractId);

    int getAllCounts();

    float getAllAmount();

    List<SaleContract> findAllContractByCondition(ContractCondition condition);

    List<String> getAllBelongToName();

    List<WriteOffResult> writeOffQuery(String purchasingUnit, String contractID, Float lowRelationRate, Float highRelationRate, String status);
}
