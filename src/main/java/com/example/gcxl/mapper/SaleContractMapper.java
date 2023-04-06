package com.example.gcxl.mapper;

import com.example.gcxl.domain.query.ContractCondition;
import com.example.gcxl.domain.SaleContract;
import com.example.gcxl.domain.query.WriteOffResult;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
public interface SaleContractMapper extends BaseMapper {
    /**
     * 插入合同表
     * @param contract
     * @return 受影响行数
     */

    Integer AddContract(SaleContract contract);

    @Select("select count(*) from salecontract where contractID = #{ContractId}")
    Integer selectById(String ContractId);

    Integer updateContract(String contractBelongTo, String region, String newContractId,
                           String contractDate, String purchasingUnit, float contractAmount, String comment, String oldContractId);
    @Select("select *from salecontract where contractID = #{ContractId}")
    SaleContract selectContractById(String ContractId);

    @Select("select count(*) from salecontract")
    Integer getAllCounts();

    @Select("select * from salecontract")
    List<SaleContract> getAllContract();

    @Select("select sum(contractAmount) from salecontract")
    Float getAllSaleContractAmount();

    List<SaleContract> getContractByCondition(ContractCondition condition);

    @Update("UPDATE salecontract SET invoiceState = #{state} where ContractId = #{ContractId}")
    Integer updateInvoiceStateById(String ContractId, String state);

    @Select("select contractAmount from salecontract where contractID=#{contractID}")
    float getContractAmountById(String contractID);

    @Update("update salecontract set correlationRate=#{rate} where contractID=#{contractID}")
    Integer updateCorrelationRateByID(float rate, String contractID);

    @Select("select purchasingUnit from salecontract where  contractID=#{contractID}")
    String getPurchasingUnitByContractID(String contractID);

    List<WriteOffResult> writeOffQuery(String purchasingUnit, String contractID, Float lowRelationRate, Float highRelationRate, String status);
}
