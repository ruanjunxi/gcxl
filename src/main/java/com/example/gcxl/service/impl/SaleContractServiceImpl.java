package com.example.gcxl.service.impl;

import com.example.gcxl.domain.ModifyLog;
import com.example.gcxl.domain.ModifyLogWithDetails;
import com.example.gcxl.domain.query.ContractCondition;
import com.example.gcxl.domain.SaleContract;
import com.example.gcxl.domain.query.WriteOffResult;
import com.example.gcxl.mapper.ContractBelongToMapper;
import com.example.gcxl.mapper.SaleContractMapper;
import com.example.gcxl.mapper.SaleInvoiceMapper;
import com.example.gcxl.service.SaleContractService;
import com.example.gcxl.service.ex.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class SaleContractServiceImpl extends BaseServiceImpl implements SaleContractService {
    @Autowired
    SaleContractMapper saleContractMapper;
    @Autowired
    SaleInvoiceMapper saleInvoiceMapper;
    @Autowired
    ContractBelongToMapper belongToMapper;
    //添加销售合同
    @Override
    public void AddContract(SaleContract saleContract) {
        if(saleContractMapper.selectById(saleContract.getContractID())>0){
            throw new PurchasingContractFoundException("销售合同已存在");
        }
       Integer res =  saleContractMapper.AddContract(saleContract);
        if(res!=1){
            throw new InsertException("插入数据产生异常");
        }
        //添加log
        ModifyLog modifyLog = new ModifyLog("添加", saleContract.getCreator(),"销售合同:"+ saleContract.getContractID(),saleContract.getContractID());
        boolean b = saleContractMapper.insertModifyLog(modifyLog);
        if (!b){
            throw new InsertException("插入修改记录出现错误");
        }
    }

    //修改合同
    @Override
    public void modifyContract(String contractBelongTo, String region, String newContractId,
                               String contractDate, String purchasingUnit, float contractAmount, String comment, String account) {
        String oldContractId=newContractId;
        boolean isModify = false;
        SaleContract oldContract =  saleContractMapper.selectContractById(oldContractId);
//       // System.out.println(oldContract.getDateContract().toString());
//        if(oldContract==null){
//            throw new PurchasingContractFoundException("销售合同不存在存在");
//        }
            if(oldContract.getContractID().equals(newContractId)&&oldContract.getContractBelongsTo().equals(contractBelongTo)&&oldContract.getRegion().equals(region)
                &&oldContract.getDateContract().equals(contractDate)&&oldContract.getPurchasingUnit().equals(purchasingUnit)&&Math.abs(oldContract.getContractAmount()-contractAmount)<0.000001
                &&oldContract.getComment().equals(comment)){
                throw new ThereIsNoChangeInformationException("没有要修改的信息");
            }
        if(!(Math.abs(oldContract.getContractAmount()-contractAmount)<0.0000001)){ //修改了合同金额
            //获得当前合同下所有发票的总金额
            float allInvoiceAmount = saleInvoiceMapper.getSumSaleInvoiceByContractID(oldContractId);
            if(allInvoiceAmount<0.0001){
                saleContractMapper.updateInvoiceStateById(newContractId,"未开票");
            }
            else if(Math.abs(allInvoiceAmount-contractAmount)<0.0000001){
                //修改合同的状态为 已开票
                saleContractMapper.updateInvoiceStateById(newContractId,"已开票");
            }
            else if(allInvoiceAmount<contractAmount){
                //修改合同的状态为 已开票
                saleContractMapper.updateInvoiceStateById(newContractId,"部分开票");
            }
            else if(allInvoiceAmount>contractAmount){
                throw new InvoiceTooLargeException("发票总金额大于合同金额");
            }

        }
        Integer res = saleContractMapper.updateContract(contractBelongTo, region, newContractId,
                contractDate, purchasingUnit, contractAmount, comment, oldContractId);
        if(res!=1){
            throw new UpdateException("更新合同出现错误");
        }

        //添加log
        ModifyLog modifyLog = new ModifyLog("修改", account,"销售合同:"+ newContractId,newContractId);
        boolean b = saleContractMapper.insertModifyLog(modifyLog);
        if (!b){
            throw new InsertException("插入修改记录出现错误");
        }
        if(!oldContract.getContractBelongsTo().equals(contractBelongTo)){  //添加合同所属修改记录
            b=saleContractMapper.insertModifyLogDetails(modifyLog.getId(), "合同所属", oldContract.getContractBelongsTo(),contractBelongTo);
            if (!b){
                throw new InsertException("插入修改记录出现错误");
            }
        }
        if(!oldContract.getRegion().equals(region)){  //添区域标识修改记录
            b=saleContractMapper.insertModifyLogDetails(modifyLog.getId(), "区域标识", oldContract.getRegion(),region);
            if (!b){
                throw new InsertException("插入修改记录出现错误");
            }
        }
        if(!oldContract.getDateContract().equals(contractDate)){  //添加合同时间修改记录
            b=saleContractMapper.insertModifyLogDetails(modifyLog.getId(), "合同时间", oldContract.getDateContract(),contractDate);
            if (!b){
                throw new InsertException("插入修改记录出现错误");
            }
        }
        if(!oldContract.getPurchasingUnit().equals(purchasingUnit)){  //添加采购单位修改记录
            b=saleContractMapper.insertModifyLogDetails(modifyLog.getId(), "采购单位", oldContract.getPurchasingUnit(),purchasingUnit);
            if (!b){
                throw new InsertException("插入修改记录出现错误");
            }
        }
        if(Math.abs(oldContract.getContractAmount()-contractAmount)>0.000001){  //添加合同金额修改记录
            b=saleContractMapper.insertModifyLogDetails(modifyLog.getId(), "合同金额", Float.toString(oldContract.getContractAmount()), Float.toString(contractAmount));
            if (!b){
                throw new InsertException("插入修改记录出现错误");
            }
        }
        if(!oldContract.getComment().equals(comment)){  //添加备注修改记录
            b=saleContractMapper.insertModifyLogDetails(modifyLog.getId(), "备注", oldContract.getComment(),comment);
            if (!b){
                throw new InsertException("插入修改记录出现错误");
            }
        }
    }

    @Override
    public List<SaleContract> findAllContract() {
        List<SaleContract> list = saleContractMapper.getAllContract();
        return list;
    }

    @Override
    public PageInfo<SaleContract> findAllPageSaleContract(int pageNum, int pageSize, ContractCondition condition) {
        PageHelper.startPage(pageNum,pageSize);
        List<SaleContract> allContract = saleContractMapper.getContractByCondition(condition);
        PageInfo<SaleContract> info = new PageInfo<>(allContract);
        return info;
    }

    @Override
    public SaleContract findContractById(String contractId) {
        return saleContractMapper.selectContractById(contractId);
    }

    @Override
    public int getAllCounts() {
        return saleContractMapper.getAllCounts();
    }

    @Override
    public float getAllAmount() {
        return saleContractMapper.getAllSaleContractAmount();
    }

    @Override
    public List<SaleContract> findAllContractByCondition(ContractCondition condition) {
        return saleContractMapper.getContractByCondition(condition);
    }

    @Override
    public List<String> getAllBelongToName() {
        return belongToMapper.getAllBelongToName();
    }

    @Override
    public List<WriteOffResult> writeOffQuery(String purchasingUnit, String contractID, Float lowRelationRate, Float highRelationRate, String status) {
        List<WriteOffResult> list = saleContractMapper.writeOffQuery(purchasingUnit,contractID,lowRelationRate,highRelationRate,status);
        return list;
    }


}
