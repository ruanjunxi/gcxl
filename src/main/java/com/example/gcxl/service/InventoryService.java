package com.example.gcxl.service;

import com.example.gcxl.domain.Inventory;
import com.example.gcxl.domain.OutBoundWithDetail;
import com.example.gcxl.domain.RelatedSales;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: InventoryService
 * @author: Eason
 * @data: 2022/4/23 16:21
 */
public interface InventoryService {

    PageInfo<Inventory> getAllInventory(int pageNum, int pageSize);

    PageInfo<Inventory> getInventoryByIDTradeCommodityModel(String tradeName, String nameCommodity, String model,String contractID,String invoiceID, String order,String orderType,int pageNum, int pageSize);

    List<OutBoundWithDetail> getOutBoundByID(int id);

    void updateDetailNote(String noteInformation,int id);

    List<RelatedSales> getRelatedSalesByID(int id);
}
