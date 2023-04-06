package com.example.gcxl.mapper;

import com.example.gcxl.domain.*;
import com.example.gcxl.domain.query.ContractCondition;
import com.example.gcxl.domain.query.CostOccupationCondition;
import com.example.gcxl.domain.query.TakeUpResult;
import io.swagger.models.auth.In;
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
public interface CostOccupationMapper extends BaseMapper{

    List<CostOccupation> findCostOccupationByCondition(CostOccupationCondition condition);

    Integer addTakeup(TakeUp takeUp);

    @Select("select *from takeup where invoiceID=#{saleInvoiceID}")
    List<TakeUp> findAllTakeUpBySaleInvoiceID(String saleInvoiceID);

    @Update("update takeup set status = '作废' where id=#{id}")
    Integer updateTakeUpStatusById(int id);

    @Update("update purchaseinvoicedetail set numberRemain=numberRemain+#{num},correlationNumber=correlationNumber-#{num} where id=#{id}")
    Integer updateStockById(int id,int num);
    @Select("select * from takeup where id=#{id}")
    TakeUp getTakeUpById(int id);

    @Select("select *from takeup where invoiceID=#{saleInvoiceID} and status=#{status}")
    List<TakeUp> findAllTakeUpBySaleInvoiceIDWithStatus(String saleInvoiceID, String status);

    List<TakeUpResult> findAllTakeUpByCondition(String supplier, String tradeName, String nameCommodity, String model);
}
