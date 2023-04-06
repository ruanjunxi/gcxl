package com.example.gcxl.service.impl;

import com.example.gcxl.domain.*;
import com.example.gcxl.mapper.PurchasingContractMapper;
import com.example.gcxl.service.PurchasingContractService;
import com.example.gcxl.service.ex.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eason
 * @ClassName:PurchasingContractServiceImpl
 * @data:2022/4/17 15:00
 */
@Service
public class PurchasingContractServiceImpl extends BaseServiceImpl implements PurchasingContractService {
    @Autowired
    private PurchasingContractMapper purchasingContractMapper;
    @Override
    public void insertPurchasingContract(PurchasingContract purchasingContract) {
        String contractID = purchasingContract.getContractID();
        PurchasingContract result = purchasingContractMapper.findContractBycontractID(contractID);
        if (result!=null){
            throw new PurchasingContractFoundException("采购合同已存在");
        }
        boolean b = purchasingContractMapper.insertPurchasingContract(purchasingContract);
        if (!b){
            throw new InsertException("插入数据产生异常");
        }
    }

    //作废
//    @Override
//    public void updateAttachment(String contractID,String attachment) {
//        PurchasingContract result = purchasingContractMapper.findContractBycontractID(contractID);
//        if (result ==null){
//            throw new PurchasingContractNotFoundException("采购合同不存在");
//        }
//        boolean b = purchasingContractMapper.updateAttachment(attachment,contractID );
//        if (!b){
//            throw new UpdateException("更新附件出现错误");
//        }
//    }

    @Override
    public void updatePurchasingContract(String contractType, String newContractID, String dateContract, String supplier
            , double contractAmount, String noteInformation, String oldContractID,String account) {
        PurchasingContract result = purchasingContractMapper.findContractBycontractID(oldContractID);
        if (result ==null){
            throw new PurchasingContractNotFoundException("采购合同id不存在");
        }
        if (result.getNoteInformation()==null){
            result.setNoteInformation("");
        }
        if (result.getContractID().equals(oldContractID)&&result.getContractType().equals(contractType)&&
                result.getDateContract().equals(dateContract)&&result.getSupplier().equals(supplier)&&
                result.getNoteInformation().equals(noteInformation)&&Math.abs(result.getContractAmount()-contractAmount)<0.000001){
            throw new ThereIsNoChangeInformationException("没有要修改的信息");
        }
        boolean b = purchasingContractMapper.updatePurchasingContract(contractType, newContractID, dateContract,
                supplier, contractAmount, noteInformation, oldContractID);
        if (!b){
            throw new UpdateException("更新出现错误");
        }

        ModifyLog modifyLog = new ModifyLog("修改", account,"采购合同:"+ newContractID,newContractID);
        b=purchasingContractMapper.insertModifyLog(modifyLog);
        if (!b){
            throw new InsertException("插入出现错误");
        }
        if(!result.getContractID().equals(oldContractID)){
            b=purchasingContractMapper.insertModifyLogDetails(modifyLog.getId(),"合同ID"
                    ,result.getContractID(),newContractID);
            if (!b){
                throw new UpdateException("更新出现错误");
            }
        }
        if (!result.getContractType().equals(contractType)){
            b=purchasingContractMapper.insertModifyLogDetails(modifyLog.getId(),"合同类型"
                    ,result.getContractType(),contractType);
            if (!b){
                throw new UpdateException("更新出现错误");
            }
        }
        if (Math.abs(result.getContractAmount()-contractAmount)>0.000001){
            double sumPurchaseInvoiceByContractID = purchasingContractMapper.getSumPurchaseInvoiceByContractID(result.getContractID());
            if (sumPurchaseInvoiceByContractID>contractAmount){
                throw new PurchasingContractAmountTooSmall("合同金额小于发票金额");
            }
            if(Math.abs(sumPurchaseInvoiceByContractID-contractAmount)<0.000001){
                purchasingContractMapper.updatePurchasingInvoiceStateByContractID("已开票",result.getContractID());
            }else {
                purchasingContractMapper.updatePurchasingInvoiceStateByContractID("部分开票",result.getContractID());
            }
            b=purchasingContractMapper.insertModifyLogDetails(modifyLog.getId(),"合同金额",result.getContractAmount()+"",contractAmount+"");
            if (!b){
                throw new UpdateException("更新出现错误");
            }
        }
        if (!result.getDateContract().equals(dateContract)){
            System.out.println(result.getDateContract());
            System.out.println(dateContract);
            b=purchasingContractMapper.insertModifyLogDetails(modifyLog.getId(),"合同日期"
                    ,result.getDateContract(),dateContract);
            if (!b){
                throw new UpdateException("更新出现错误");
            }
        }
        if (!result.getSupplier().equals(supplier)){
            b=purchasingContractMapper.insertModifyLogDetails(modifyLog.getId(),"供应商"
                    ,result.getSupplier(),supplier);
            if (!b){
                throw new UpdateException("更新出现错误");
            }
        }
        if (!result.getNoteInformation().equals(noteInformation)){
            b=purchasingContractMapper.insertModifyLogDetails(modifyLog.getId(),"备注信息"
                    ,result.getNoteInformation(),noteInformation);
            if (!b){
                throw new UpdateException("更新出现错误");
            }
        }
    }

    @Override
    public List<PurchasingContract> findAllContract() {
        List<PurchasingContract> allContract = purchasingContractMapper.findAllContract();
        return allContract;
    }

    @Override
    public PageInfo<PurchasingContract> findAllPagContract(int pageNum, int pageSize){
        System.out.println(pageNum+"      "+pageSize);
        PageHelper.startPage(pageNum,pageSize);
        List<PurchasingContract> allContract = purchasingContractMapper.findAllContract();
        PageInfo<PurchasingContract> purchasingContractPageInfo = new PageInfo<>(allContract);
        return purchasingContractPageInfo;
    }



    @Override
    public int getAllContractAmount() {
        int allContractAmount = purchasingContractMapper.getAllContractAmount();
        return allContractAmount;
    }

    @Override
    public PurchasingContract findByContractID(String contractID) {
        PurchasingContract bycontractID = purchasingContractMapper.findContractBycontractID(contractID);
        return bycontractID;
    }

    @Override
    public int getCountPurchasingContract() {
        int countPurchasingContract = purchasingContractMapper.getCountPurchasingContract();
        return countPurchasingContract;
    }

    @Override
    public PageInfo<PurchasingContract> getContractsByConditions(String date, String nameCommodity, String model, String supplier, String noteInformation, String invoiceState, String contractID,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<PurchasingContract> contractsByConditions = purchasingContractMapper.getContractsByConditions(date, nameCommodity, model, supplier, noteInformation, invoiceState, contractID);
        PageInfo<PurchasingContract> purchasingContractPageInfo = new PageInfo<>(contractsByConditions);
        return purchasingContractPageInfo;
    }
}
