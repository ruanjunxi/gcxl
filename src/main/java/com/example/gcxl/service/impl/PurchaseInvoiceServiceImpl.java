package com.example.gcxl.service.impl;

import com.example.gcxl.domain.*;
import com.example.gcxl.mapper.PurchaseInvoiceMapper;
import com.example.gcxl.service.PurchaseInvoiceService;
import com.example.gcxl.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: PurchaseInvoiceServiceImpl
 * @author: Eason
 * @data: 2022/4/18 17:01
 */
@Service
public class PurchaseInvoiceServiceImpl extends BaseServiceImpl implements PurchaseInvoiceService {
    @Autowired
    private PurchaseInvoiceMapper purchaseInvoiceMapper;

    /**
     * 插入采购发票
     *
     * @param purchaseInvoice
     */
    @Override
    public void insertPurchaseInvoice(PurchaseInvoice purchaseInvoice) {
        PurchaseInvoice result = purchaseInvoiceMapper.findByInvoiceID(purchaseInvoice.getInvoiceID());
        if (result != null) {
            throw new PurchaseInvoiceDoesExistException("采购发票已存在");
        }
        PurchasingContract contract = purchaseInvoiceMapper.findContractBycontractID(purchaseInvoice.getContractID());
        if (contract == null) {
            throw new PurchasingContractNotFoundException("采购发票对应的合同不存在");
        }
        double sumPurchaseInvoice = purchaseInvoiceMapper.getSumPurchaseInvoiceByContractID(purchaseInvoice.getContractID());
        if (sumPurchaseInvoice + purchaseInvoice.getAllAmount() > contract.getContractAmount()) {
            throw new InvoiceTooLargeException("采购发票金额大于合同金额");
        }
        boolean b = purchaseInvoiceMapper.insertPurchaseInvoice(purchaseInvoice);
        if (!b) {
            throw new InsertException("插入过程产生错误");
        }
        System.out.println(Math.abs(sumPurchaseInvoice + purchaseInvoice.getAllAmount() - contract.getContractAmount()));
        if (Math.abs(sumPurchaseInvoice + purchaseInvoice.getAllAmount() - contract.getContractAmount()) < 0.00001) {
            purchaseInvoiceMapper.updatePurchasingInvoiceStateByContractID("已开票", purchaseInvoice.getContractID());
        } else {
            purchaseInvoiceMapper.updatePurchasingInvoiceStateByContractID("部分开票", purchaseInvoice.getContractID());
        }
    }

    /**
     * 插入采购发票明细
     *
     * @param purchaseInvoiceDetail
     */

    @Override
    public void insertPurchaseInvoiceDetails(PurchaseInvoiceDetail purchaseInvoiceDetail) {
        PurchaseInvoice result = purchaseInvoiceMapper.findByInvoiceID(purchaseInvoiceDetail.getInvoiceID());
        if (result.getInvoiceID() == null) {
            throw new PurchaseInvoiceDoesNotExistException("采购发票不存在");
        }
        boolean b = purchaseInvoiceMapper.insertPurchaseInvoiceDetails(purchaseInvoiceDetail);
        if (!b) {
            throw new InsertException("插入过程产生错误");
        }
    }

    /*批量插入采购发票明细*/
    @Override
    public void bulkInsertPurchasingInvoiceDetail(List<PurchaseInvoiceDetail> purchaseInvoiceDetails) {
        for (PurchaseInvoiceDetail p : purchaseInvoiceDetails) {
            PurchaseInvoice result = purchaseInvoiceMapper.findByInvoiceID(p.getInvoiceID());
            if (result == null) {
                throw new PurchaseInvoiceDoesNotExistException("采购发票不存在");
            }
        }
        purchaseInvoiceMapper.bulkInsertPurchasingInvoiceDetail(purchaseInvoiceDetails);
    }

    /**
     * 更新采购发票并记录在更新日志中
     *
     * @param newInvoiceID
     * @param newMakeOutAnInvoiceDate
     * @param newInvoiceType
     * @param oldInvoiceID
     */
    @Override
    public void updatePurchaseInvoice(String newInvoiceID, String newMakeOutAnInvoiceDate, String newInvoiceType,
                                      String oldInvoiceID, String account) {
        PurchaseInvoice result = purchaseInvoiceMapper.findByInvoiceID(oldInvoiceID);
        if (result == null) {
            throw new PurchaseInvoiceDoesNotExistException("采购发票不存在");
        }
        if (result.getInvoiceID().equals(newInvoiceID) && result.getMakeOutAnInvoiceDate().equals(newMakeOutAnInvoiceDate)
                && result.getInvoiceType().equals(newInvoiceType)) {
            throw new ThereIsNoChangeInformationException("没有要修改的信息");
        }
        boolean b = purchaseInvoiceMapper.updatePurchaseInvoice(newInvoiceID, newMakeOutAnInvoiceDate, newInvoiceType, oldInvoiceID);
        if (!b) {
            throw new UpdateException("更新过程产生错误");
        }
        ModifyLog modifyLog = new ModifyLog("修改", account, "采购发票:" + newInvoiceID, newInvoiceID, result.getContractID());
        b = purchaseInvoiceMapper.insertModifyLog(modifyLog);
        if (!b) {
            throw new InsertException("插入出现错误");
        }
        if (!result.getInvoiceID().equals(newInvoiceID)) {
            purchaseInvoiceMapper.insertModifyLogDetails(modifyLog.getId(), "发票编号", result.getInvoiceID(), newInvoiceID);
            if (!b) {
                throw new UpdateException("更新出现错误");
            }
        }
        if (!result.getInvoiceType().equals(newInvoiceType)) {
            purchaseInvoiceMapper.insertModifyLogDetails(modifyLog.getId(), "发票类型", result.getInvoiceType(), newInvoiceType);
            if (!b) {
                throw new UpdateException("更新出现错误");
            }
        }
        if (!result.getMakeOutAnInvoiceDate().equals(newMakeOutAnInvoiceDate)) {
            purchaseInvoiceMapper.insertModifyLogDetails(modifyLog.getId(), "发票日期", result.getMakeOutAnInvoiceDate(), newMakeOutAnInvoiceDate);
            if (!b) {
                throw new UpdateException("更新出现错误");
            }
        }

    }


    /**
     * 修改发票明细并记录在更新日志中
     *
     * @param purchaseInvoiceDetail
     * @param account
     */

    @Override
    public void updatePurchaseInvoiceDetail(PurchaseInvoiceDetail purchaseInvoiceDetail, String account) {
        int id = purchaseInvoiceDetail.getId();
        String newTradeName = purchaseInvoiceDetail.getTradeName();
        String newNameCommodity = purchaseInvoiceDetail.getNameCommodity();
        String newModel = purchaseInvoiceDetail.getModel();
        int newPrivateIntAmount = purchaseInvoiceDetail.getPrivateIntAmount();
        double newUnitPrice = purchaseInvoiceDetail.getUnitPrice();
        double newAmount = purchaseInvoiceDetail.getAmount();
        int newNumberRemain = purchaseInvoiceDetail.getNumberRemain();
        PurchaseInvoiceDetail result = purchaseInvoiceMapper.findInvoiceDetailByID(id);
        if (result == null) {
            throw new PurchaseInvoiceDetailDoesNotExistException("采购发票明细不存在");
        }
        if (result.getTradeName().equals(newTradeName) && result.getNameCommodity().equals(newNameCommodity)
                && result.getModel().equals(newModel) && Math.abs(result.getPrivateIntAmount() - newPrivateIntAmount) < 0.0000001 &&
                Math.abs(result.getUnitPrice() - newUnitPrice) < 0.000001) {
            throw new ThereIsNoChangeInformationException("无要更新的参数");
        }
//        newTradeName +"-" + newNameCommodity + "-" + newModel
        ModifyLog modifyLog = new ModifyLog("修改", account, "采购发票明细:" + newTradeName + "-" + newNameCommodity + "-" + newModel,
                Integer.toString(purchaseInvoiceDetail.getId()), purchaseInvoiceDetail.getInvoiceID());
        boolean b = purchaseInvoiceMapper.insertModifyLog(modifyLog);
        if (!b) {
            throw new InsertException("插入出现错误");
        }
        if (!result.getTradeName().equals(newTradeName)) {
            b = purchaseInvoiceMapper.insertModifyLogDetails(modifyLog.getId(), "品名", result.getTradeName(), newTradeName);
            if (!b) {
                throw new UpdateException("更新出现错误");
            }
        }
        if (!result.getNameCommodity().equals(newNameCommodity)) {
            b = purchaseInvoiceMapper.insertModifyLogDetails(modifyLog.getId(), "商品名称", result.getNameCommodity(), newNameCommodity);
            if (!b) {
                throw new UpdateException("更新出现错误");
            }
        }
        if (!result.getModel().equals(newModel)) {
            b = purchaseInvoiceMapper.insertModifyLogDetails(modifyLog.getId(), "型号", result.getModel(), newModel);
            if (!b) {
                throw new UpdateException("更新出现错误");
            }
        }
        if (Math.abs(result.getPrivateIntAmount() - newPrivateIntAmount) > 0.0000001) {
            if (newPrivateIntAmount < result.getOutboundNumber() + result.getCorrelationNumber()) {
                throw new PrivateIntAmountLackException("采购数量小于关联数量+出库数量");
            }
            if (newPrivateIntAmount > result.getPrivateIntAmount()) {
                newNumberRemain += newPrivateIntAmount - result.getPrivateIntAmount();
            } else {
                newNumberRemain -= newPrivateIntAmount - result.getPrivateIntAmount();
            }
            b = purchaseInvoiceMapper.insertModifyLogDetails(modifyLog.getId(), "采购数量", result.getPrivateIntAmount() + "", newPrivateIntAmount + "");
            if (!b) {
                throw new UpdateException("更新出现错误");
            }
        }
        if (Math.abs(result.getUnitPrice() - newUnitPrice) > 0.000001) {
            b = purchaseInvoiceMapper.insertModifyLogDetails(modifyLog.getId(), "单价", result.getUnitPrice() + "", newUnitPrice + "");
            if (!b) {
                throw new UpdateException("更新出现错误");
            }
        }
        //计算新的金额
        newAmount = newPrivateIntAmount * newUnitPrice;
        double oldAmount = result.getAmount();
        PurchaseInvoice purchaseInvoice = purchaseInvoiceMapper.findByInvoiceID(purchaseInvoiceDetail.getInvoiceID());
        double sumPurchaseInvoiceByContractID = purchaseInvoiceMapper.getSumPurchaseInvoiceByContractID(purchaseInvoice.getContractID());
        if (sumPurchaseInvoiceByContractID - oldAmount + newAmount > purchaseInvoiceMapper.findContractBycontractID(purchaseInvoice.getContractID()).getContractAmount()) {
            throw new InvoiceTooLargeException("采购发票金额大于合同金额");
        }
        if (Math.abs(sumPurchaseInvoiceByContractID - oldAmount + newAmount - purchaseInvoiceMapper.findContractBycontractID(purchaseInvoice.getContractID()).getContractAmount()) < 0.00001) {
            purchaseInvoiceMapper.updatePurchasingInvoiceStateByContractID("已开票", purchaseInvoice.getContractID());
        } else {
            purchaseInvoiceMapper.updatePurchasingInvoiceStateByContractID("部分开票", purchaseInvoice.getContractID());
        }
        double amount = purchaseInvoice.getAllAmount() - oldAmount + newAmount;
        purchaseInvoiceDetail.setAmount(newAmount);
        purchaseInvoiceDetail.setNumberRemain(newNumberRemain);
        b = purchaseInvoiceMapper.updatePurchaseInvoiceDetail(purchaseInvoiceDetail);
        if (!b) {
            throw new UpdateException("更新出现错误");
        }
        purchaseInvoiceMapper.updatePurchaseInvoiceAmount(amount, purchaseInvoice.getInvoiceID());
//        b = purchaseInvoiceMapper.updateDetailUpdateFrequency(purchaseInvoiceDetail.getInvoiceID());
//        if (!b) {
//            throw new UpdateException("更新出现错误");
//        }


    }

    /**
     * 得到采购合同下的所有发票及其对应的发票明细
     *
     * @param contractID
     * @return
     */
    @Override
//    public List<PurchaseInvoiceWithDetail> getAllPurchaseInvoiceWithDetail(String contractID) {
////        String contractID = purchasingContract.getContractID();
//        List<PurchaseInvoice> purchaseInvoices = purchaseInvoiceMapper.findInvoiceByContractID(contractID);
//        PurchaseInvoice noPurchaseInvoice = new PurchaseInvoice();
//        List<PurchaseInvoiceWithDetail> purchaseInvoiceWithDetails = new ArrayList<>();
//        for (int j = 0; j < purchaseInvoices.size(); j++) {
//            PurchaseInvoice purchaseInvoice = purchaseInvoices.get(j);
//            String invoiceID = purchaseInvoice.getInvoiceID();
//            List<PurchaseInvoiceDetail> detail = purchaseInvoiceMapper.findDetailByInvoiceID(invoiceID);
//            if (detail.size() == 1) {
//                PurchaseInvoiceWithDetail purchaseInvoiceWithDetail = new PurchaseInvoiceWithDetail(purchaseInvoice, detail.get(0));
//            } else {
//                for (int i = 0; i < detail.size(); i++) {
//                    if (i == 0) {
//                        PurchaseInvoiceWithDetail purchaseInvoiceWithDetail = new PurchaseInvoiceWithDetail(purchaseInvoice, detail.get(0));
//                        purchaseInvoiceWithDetails.add(purchaseInvoiceWithDetail);
//                    } else {
//                        PurchaseInvoiceWithDetail purchaseInvoiceWithDetail = new PurchaseInvoiceWithDetail(noPurchaseInvoice, detail.get(i));
//                        purchaseInvoiceWithDetails.add(purchaseInvoiceWithDetail);
//                    }
//                }
//            }
//        }
//        return purchaseInvoiceWithDetails;
//    }
    public List<PurchaseInvoiceHaveDetails> getAllPurchaseInvoiceWithDetail(String contractID) {
        List<PurchaseInvoiceHaveDetails> purchaseInvoiceHaveDetailsList = new ArrayList<>();
        List<PurchaseInvoice> purchaseInvoices = purchaseInvoiceMapper.findInvoiceByContractID(contractID);
        for (PurchaseInvoice p : purchaseInvoices) {
            List<PurchaseInvoiceDetail> detailByInvoiceID = purchaseInvoiceMapper.findDetailByInvoiceID(p.getInvoiceID());
            PurchaseInvoiceHaveDetails purchaseInvoiceHaveDetails = new PurchaseInvoiceHaveDetails(p, detailByInvoiceID);
            purchaseInvoiceHaveDetailsList.add(purchaseInvoiceHaveDetails);
        }
        return purchaseInvoiceHaveDetailsList;
    }


}
