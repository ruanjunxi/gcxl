package com.example.gcxl.service.impl;

import com.example.gcxl.domain.Inventory;
import com.example.gcxl.domain.OutBoundWithDetail;
import com.example.gcxl.domain.PurchasingContract;
import com.example.gcxl.domain.RelatedSales;
import com.example.gcxl.mapper.InventoryMapper;
import com.example.gcxl.service.InventoryService;
import com.example.gcxl.service.ex.UpdateException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: InventoryServiceImpl
 * @author: Eason
 * @data: 2022/4/23 16:48
 */
@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryMapper inventoryMapper;
    @Override
    public  PageInfo<Inventory> getAllInventory(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Inventory> allInventory = inventoryMapper.getAllInventory();
        PageInfo<Inventory> purchasingContractPageInfo = new PageInfo<>(allInventory);
        return purchasingContractPageInfo;
    }

    @Override
    public PageInfo<Inventory> getInventoryByIDTradeCommodityModel(String tradeName, String nameCommodity, String model, String contractID, String invoiceID, String order, String orderType,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Inventory> inventoryByIDTradeCommodityModel = inventoryMapper.getInventoryByIDTradeCommodityModel(tradeName, nameCommodity, model, contractID, invoiceID, order, orderType);
        PageInfo<Inventory> purchasingContractPageInfo = new PageInfo<>(inventoryByIDTradeCommodityModel);
        return purchasingContractPageInfo;
    }

    @Override
    public List<OutBoundWithDetail> getOutBoundByID(int id) {
        List<OutBoundWithDetail> outBoundByID = inventoryMapper.getOutBoundByID(id);
        if (outBoundByID==null){
            throw new NullPointerException("为空");
        }
        return outBoundByID;
    }

    @Override
    public void updateDetailNote(String noteInformation,int id) {
        boolean b = inventoryMapper.updateDetailNote(noteInformation,id);
        if (!b){
            throw new UpdateException("更新错误");
        }

    }

    @Override
    public List<RelatedSales> getRelatedSalesByID(int id) {
        List<RelatedSales> relatedSalesByID = inventoryMapper.getRelatedSalesByID(id);
        return relatedSalesByID;
    }
}
