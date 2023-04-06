package com.example.gcxl.service.impl;

import com.example.gcxl.domain.*;
import com.example.gcxl.mapper.SaleContractMapper;
import com.example.gcxl.mapper.SaleInvoiceMapper;
import com.example.gcxl.service.SaleInvoiceService;
import com.example.gcxl.service.ex.*;
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
public class SaleInvoiceServiceImpl extends BaseServiceImpl implements SaleInvoiceService {

    @Autowired
    SaleInvoiceMapper saleInvoiceMapper;
    @Autowired
    SaleContractMapper saleContractMapper;

    /**
     * 插入销售发票
     * @param invoice
     */
    @Override
    public void AddInvoice(SaleInvoice invoice) {
        if(saleInvoiceMapper.selectById(invoice.getInvoiceID())>0){
            throw new SaleInvoiceDoesExistException("销售发票已存在");
        }
        SaleContract contract = saleContractMapper.selectContractById(invoice.getContractID());
        if(contract==null){
            throw new PurchasingContractFoundException("销售发票对应的合同不存在");
        }
        double sum= saleInvoiceMapper.getSumSaleInvoiceByContractID(invoice.getContractID());
        if(contract.getContractAmount()<invoice.getAmount()+sum){
            throw new InvoiceTooLargeException("销售发票金额大于合同金额");
        }
        String purchasingUnit = contract.getPurchasingUnit();
//        System.out.println(purchasingUnit);
        invoice.setPurchasingUnit(purchasingUnit);
        Integer res =  saleInvoiceMapper.addInvoice(invoice);
        if(res!=1){
            throw new InsertException("插入数据产生异常");
        }

        //修改合同状态
        if (Math.abs(sum + invoice.getAmount() - contract.getContractAmount())<0.00001){
            saleContractMapper.updateInvoiceStateById(invoice.getContractID(),"已开票");
        }else {
            saleContractMapper.updateInvoiceStateById(invoice.getContractID(),"部分开票");
        }

        //添加log
        ModifyLog modifyLog = new ModifyLog("添加", invoice.getCreator(),"销售发票:"+ invoice.getInvoiceID(),invoice.getInvoiceID());
        boolean b = saleInvoiceMapper.insertModifyLog(modifyLog);
        if (!b){
            throw new InsertException("插入添加销售发票记录出现错误");
        }

    }

    /**
     * 批量插入发票明细
     * @param detailList
     */
    @Override
    public void addBatchInvoiceDetail(List<SaleInvoiceDetail> detailList) {
        for (SaleInvoiceDetail detail:detailList
             ) {
            detail.setAmount(detail.getPrivateIntAmount()*detail.getUnitPrice());
        }
        Integer res = saleInvoiceMapper.addBatchInvoiceDetail(detailList);
//        if(res!=detailList.size()){
//            throw new InsertException("插入数据产生异常");
//        }
    }


    @Override
    public void updateInvoiceById(String invoiceID, String date, String type, String account) {
        SaleInvoice invoice = saleInvoiceMapper.selectInvoiceById(invoiceID);
        if(invoice.getInvoiceType().equals(type) && invoice.getInvoiceDate().equals(date)){
            throw new ThereIsNoChangeInformationException("没有要修改的信息");
        }
        int res = saleInvoiceMapper.updateInvoiceById(invoiceID,date,type);
        if(res == 0){
            throw new UpdateException("更新销售发票产生异常");
        }
        //添加log
        ModifyLog modifyLog = new ModifyLog("修改", account,"销售发票:"+ invoiceID,invoiceID);
        boolean b = saleContractMapper.insertModifyLog(modifyLog);
        if (!b){
            throw new InsertException("插入修改销售发票记录出现错误");
        }
        if(!invoice.getInvoiceType().equals(type)){//添加销售发票类型修改记录
            b=saleContractMapper.insertModifyLogDetails(modifyLog.getId(), "发票类型", invoice.getInvoiceType(),type);
            if (!b){
                throw new InsertException("插入销售发票修改记录出现错误");
            }
        }
        if(!invoice.getInvoiceDate().equals(date)){//添加销售发票日期修改记录
            b=saleContractMapper.insertModifyLogDetails(modifyLog.getId(), "发票日期", invoice.getInvoiceDate(),date);
            if (!b){
                throw new InsertException("插入销售发票修改记录出现错误");
            }
        }
    }

    /**
     * 修改发票明细
     * @param saleInvoiceDetail
     * @param account
     */
    @Override
    public void updateInvoiceDetailById(SaleInvoiceDetail saleInvoiceDetail, String account) {
        //获取当前detail对应的发票ID
        String invoiceID=saleInvoiceMapper.getInvoiceIDByDetailId(saleInvoiceDetail.getId());
        saleInvoiceDetail.setInvoiceID(invoiceID);
        //获取当前发票对应的合同id
        String contractID=saleInvoiceMapper.getContractIdByInvoiceId(saleInvoiceDetail.getInvoiceID());
        SaleInvoiceDetail oldDetail = saleInvoiceMapper.selectInvoiceDetailById(saleInvoiceDetail.getId());

        if(oldDetail.getTradeName().equals(saleInvoiceDetail.getTradeName())&&oldDetail.getNameCommodity().equals(saleInvoiceDetail.getNameCommodity())&&
        oldDetail.getModel().equals(saleInvoiceDetail.getModel())&&oldDetail.getPrivateIntAmount()==saleInvoiceDetail.getPrivateIntAmount()&&
        Math.abs(oldDetail.getUnitPrice()-saleInvoiceDetail.getUnitPrice())<0.000001){
            throw new ThereIsNoChangeInformationException("没有要修改的信息");
        }

        int newNums=saleInvoiceDetail.getPrivateIntAmount();
        float newUnitPrice=saleInvoiceDetail.getUnitPrice();
        //该合同下除了当前发票的金额总数
        float otherInvoiceAmount=saleInvoiceMapper.getOtherInvoiceAmount(contractID,saleInvoiceDetail.getInvoiceID());
//        该合同的金额
        float contractAmount=saleContractMapper.getContractAmountById(contractID);
        if(newNums * newUnitPrice+otherInvoiceAmount-contractAmount>0.01){
            throw new InvoiceTooLargeException("销售发票金额大于合同金额");
        }
        saleInvoiceDetail.setAmount(newNums * newUnitPrice);
        int res = saleInvoiceMapper.updateInvoiceDetailById(saleInvoiceDetail);
        //修改完，如果单价或采购数量发生改变，则还需要修改发票金额
        if((!(oldDetail.getPrivateIntAmount()==saleInvoiceDetail.getPrivateIntAmount()))||
                (!(Math.abs(oldDetail.getUnitPrice()-saleInvoiceDetail.getUnitPrice())<0.000001))){
            saleInvoiceMapper.updateAmount(oldDetail.getInvoiceID(), newNums*newUnitPrice);
            //修改合同状态
            if(Math.abs(newNums * newUnitPrice+otherInvoiceAmount-contractAmount)<0.0000001){
                saleContractMapper.updateInvoiceStateById(contractID,"已开票");
            }
            else{
                saleContractMapper.updateInvoiceStateById(contractID,"部分开票");
            }
        }
        if(res == 0){
            throw new UpdateException("更新销售明细产生异常");
        }
//        添加log
        //添加log
        ModifyLog modifyLog = new ModifyLog("修改", account,"销售发票明细:"+ oldDetail.getTradeName()+oldDetail.getNameCommodity()+oldDetail.getModel(),
                Long.toString(oldDetail.getId()));
        boolean b = saleInvoiceMapper.insertModifyLog(modifyLog);
        if (!b){
            throw new InsertException("插入修改销售发票明细记录出现错误");
        }
        //添加品牌名修改记录
        if(!oldDetail.getTradeName().equals(saleInvoiceDetail.getTradeName())){
            b=saleContractMapper.insertModifyLogDetails(modifyLog.getId(), "品名", oldDetail.getTradeName(),saleInvoiceDetail.getTradeName());
            if (!b){
                throw new InsertException("插入销售发票修改记录出现错误");
            }
        }
        //添加商品名修改记录
        if(!oldDetail.getNameCommodity().equals(saleInvoiceDetail.getNameCommodity())){
            b=saleContractMapper.insertModifyLogDetails(modifyLog.getId(), "商品名称", oldDetail.getNameCommodity(),saleInvoiceDetail.getNameCommodity());
            if (!b){
                throw new InsertException("插入修改销售发票明细记录出现错误");
            }
        }
        //添加型号名修改记录
        if(!oldDetail.getModel().equals(saleInvoiceDetail.getModel())){
            b=saleContractMapper.insertModifyLogDetails(modifyLog.getId(), "型号", oldDetail.getModel(),saleInvoiceDetail.getModel());
            if (!b){
                throw new InsertException("插入销售发票修改记录出现错误");
            }
        }
        //添加采购数量修改记录
        if(!(oldDetail.getPrivateIntAmount()==saleInvoiceDetail.getPrivateIntAmount())){
            b=saleContractMapper.insertModifyLogDetails(modifyLog.getId(), "采购数量", Integer.toString(oldDetail.getPrivateIntAmount()),
                    Integer.toString(saleInvoiceDetail.getPrivateIntAmount()));
            if (!b){
                throw new InsertException("插入销售发票修改记录出现错误");
            }
        }
        //添加采购单价修改记录
        if(!(Math.abs(oldDetail.getUnitPrice()-saleInvoiceDetail.getUnitPrice())<0.000001)){
            b=saleContractMapper.insertModifyLogDetails(modifyLog.getId(), "采购单价", Float.toString(oldDetail.getUnitPrice()),
                    Float.toString(saleInvoiceDetail.getUnitPrice()));
            if (!b){
                throw new InsertException("插入销售发票修改记录出现错误");
            }
        }
    }

    /**
     * 获取销售合同下的发票信息
     * @param contractId
     * @return
     */
    @Override
    public List<SaleInvoice> getSaleInvoiceByContractId(String contractId) {
        List<SaleInvoice> invoices = saleInvoiceMapper.getSaleInvoiceByContractId(contractId);
        if(invoices.size()==0){
            return null;
        }
        for (SaleInvoice invoice:invoices
             ) {
            List<SaleInvoiceDetail> detailList = saleInvoiceMapper.getSaleInvoiceDetailByInvoiceId(invoice.getInvoiceID());
            invoice.setDetailList(detailList);
        }

        return  invoices;
    }

    @Override
    public List<SaleInvoice> getSaleInvoiceByCondition(String invoiceId, String purchasingUnit, String year, String month) {
        List<SaleInvoice> list = saleInvoiceMapper.getSaleInvoiceByCondition(invoiceId,purchasingUnit,year,month);
        return list;
    }

    @Override
    public List<SaleInvoiceDetail> getSaleInvoiceDetailByInvoiceId(String invoiceId) {
        List<SaleInvoiceDetail> list = saleInvoiceMapper.getSaleInvoiceDetailByInvoiceId(invoiceId);
        return list;
    }


}
