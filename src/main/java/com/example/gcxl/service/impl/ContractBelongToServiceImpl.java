package com.example.gcxl.service.impl;

import com.example.gcxl.domain.ContractBelongTo;
import com.example.gcxl.domain.query.ContractBelongToCondition;
import com.example.gcxl.mapper.ContractBelongToMapper;
import com.example.gcxl.service.ContractBelongToService;
import com.example.gcxl.service.ex.ContractBelongToNameHasExistException;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
@Service
public class ContractBelongToServiceImpl implements ContractBelongToService {
    @Autowired
    ContractBelongToMapper belongToMapper;

    @Override
    public List<ContractBelongTo> findAllBelongByCondition(ContractBelongToCondition condition) {
        List<ContractBelongTo> list = belongToMapper.findAllBelongByCondition(condition);
        return list;
    }

    @Override
    public void addContractBelongTo(ContractBelongTo belongTo) {
        ContractBelongTo hasExist = belongToMapper.findBelongToByName(belongTo.getName());
        if(hasExist!=null){
            throw new ContractBelongToNameHasExistException("合同所属已存在");
        }
        Integer res = belongToMapper.addContractBelongTo(belongTo);
    }

    @Override
    public void modifyContractBelongTo(ContractBelongTo belongTo) {
        ContractBelongTo hasExist = belongToMapper.findBelongToByName(belongTo.getName());
        if(hasExist!=null){
            throw new ContractBelongToNameHasExistException("合同所属已存在");
        }
        Integer res = belongToMapper.modifyContractBelongTo(belongTo);
    }
}
