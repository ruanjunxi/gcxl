package com.example.gcxl.mapper;

import com.example.gcxl.domain.ContractBelongTo;
import com.example.gcxl.domain.OutBound;
import com.example.gcxl.domain.OutBoundDetails;
import com.example.gcxl.domain.query.ContractBelongToCondition;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSException;

import java.util.List;

/**
 * @ClassName: OutBound
 * @author: Eason
 * @data: 2022/4/19 20:41
 */
public interface ContractBelongToMapper extends BaseMapper{
   List<ContractBelongTo> findAllBelongByCondition(ContractBelongToCondition condition);

    Integer addContractBelongTo(ContractBelongTo belongTo);

    @Select("SELECT * FROM contractbelongto WHERE name=#{name}")
    ContractBelongTo findBelongToByName(String name);

    Integer modifyContractBelongTo(ContractBelongTo belongTo);

    @Select("select name from contractbelongto")
    List<String> getAllBelongToName();
}
