package com.example.gcxl.service.impl;

import com.example.gcxl.domain.*;
import com.example.gcxl.mapper.BaseMapper;
import com.example.gcxl.service.BaseService;
import com.example.gcxl.service.ex.InsertException;
import com.example.gcxl.service.ex.PurchaseInvoiceDoesNotExistException;
import com.example.gcxl.service.ex.PurchasingContractNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: BaseServiceImpl
 * @author: Eason
 * @data: 2022/4/19 19:14
 */
public class BaseServiceImpl implements BaseService {
    @Autowired
    private BaseMapper baseMapper;
    @Override
    public List<ModifyLogWithDetails> getModifyWithDetailByTypeID(String typeID) {
        List<ModifyLogWithDetails> list = new ArrayList<>();
        List<ModifyLog> modifyLogs = baseMapper.findModifyByTypeID(typeID);
        for (ModifyLog m:modifyLogs){
            String replace = m.getCreationDate().replace(".0", "");
            m.setCreationDate(replace);
            List<ModifyLogDetails> logDetails = baseMapper.findLogDetailsById(m.getId());
            ModifyLogWithDetails modifyLogWithDetails = new ModifyLogWithDetails(m, logDetails);
            list.add(modifyLogWithDetails);
        }
        return list;
    }

    @Override
    public void insertFile(AttachmentFile attachmentFile) {
        boolean b = baseMapper.insertFile(attachmentFile);
        if (!b){
            throw new InsertException("插入错误");
        }
    }

    @Override
    public void deleteFile(int id) {
        baseMapper.deleteFileByID(id);
    }

    @Override
    public List<AttachmentFile> getFilesByTypeID(String typeID) {
        List<AttachmentFile> filesByTypeID = baseMapper.getFilesByTypeID(typeID);
        return filesByTypeID;
    }

    @Override
    public void updateNumberAttachments(int numberAttachments, String contractID) {
        baseMapper.updateNumberAttachments(numberAttachments,contractID);
        PurchasingContract contractBycontractID = baseMapper.findContractBycontractID(contractID);
        if (contractBycontractID==null){
            throw new PurchasingContractNotFoundException("采购合同不存在");
        }
    }

    @Override
    public void updateInvoiceNumberAttachments(int numberAttachments, String invoiceID) {
        baseMapper.updateInvoiceNumberAttachments(numberAttachments, invoiceID);
        PurchaseInvoice byInvoiceID = baseMapper.findByInvoiceID(invoiceID);
        if (byInvoiceID==null){
            throw new PurchaseInvoiceDoesNotExistException("发票不存在");
        }
    }

}
