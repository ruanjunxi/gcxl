package com.example.gcxl.service;

import com.example.gcxl.domain.ModifyLog;
import com.example.gcxl.domain.ModifyLogWithDetails;
import com.example.gcxl.domain.PurchasingContract;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PurchasingContractService extends BaseService {
    /**
     * 插入采购合同
     * @param purchasingContract
     */
    void insertPurchasingContract(PurchasingContract purchasingContract);

    /**
     * 更新附件（作废）
     * @param contractID
     * @param attachment
     */
//    void updateAttachment(String contractID,String attachment);

    /**
     * 更新采购合同
     * @param contractType
     * @param newContractID
     * @param dateContract
     * @param supplier
     * @param contractAmount
     * @param noteInformation
     * @param oldContractID
     * @param account
     */
    void  updatePurchasingContract(String contractType,String newContractID,String dateContract,String supplier
            ,double contractAmount,String noteInformation,String oldContractID,String account);
//    不分页

    /**
     * 不分页得到所有采购合同
     * @return
     */
    List<PurchasingContract> findAllContract();

    /**
     * 获得所有合同的金额之和
     * @return
     */
    int getAllContractAmount();

    /**
     * 通过合同ID获得合同
     * @param contractID
     * @return
     */
    PurchasingContract findByContractID(String contractID);

    /**
     * 获得采购合同数量
     * @return
     */
    int getCountPurchasingContract();
//    分页

    /**
     * 分页得到所有合同
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<PurchasingContract>  findAllPagContract(int pageNum, int pageSize);

    /**
     * 模糊匹配查询采购合同
     * @param date
     * @param nameCommodity
     * @param model
     * @param supplier
     * @param noteInformation
     * @param invoiceState
     * @param contractID
     * @return
     */
    PageInfo<PurchasingContract> getContractsByConditions(String date,String nameCommodity,String model,String supplier,String noteInformation,String invoiceState,String contractID,int pageNum,int pageSize);

}
