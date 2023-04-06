package com.example.gcxl.service;

import com.example.gcxl.domain.ContractBelongTo;
import com.example.gcxl.domain.query.ContractBelongToCondition;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
public interface ContractBelongToService {
    List<ContractBelongTo> findAllBelongByCondition(ContractBelongToCondition condition);

    void addContractBelongTo(ContractBelongTo belongTo);

    void modifyContractBelongTo(ContractBelongTo belongTo);
}
