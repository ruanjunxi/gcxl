package com.example.gcxl.mapper;

import com.example.gcxl.domain.ModifyLog;
import com.example.gcxl.domain.PurchasingContract;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;
import sun.management.counter.AbstractCounter;

import java.util.List;

/**
 * @ClassName: PurchasingContractMapper
 * @author: Eason
 * @data: 2022/4/17 15:19
 */

public interface PurchasingContractMapper extends BaseMapper{
//    (contractID,contractType,dateContract,supplier,contractAmount,invoiceState," +
//            "updateFrequency,creationDate,noteInformation,account)
    @Insert("insert into purchasingcontract value (#{contractID},#{contractType}" +
            ",#{dateContract},#{supplier},#{contractAmount},#{invoiceState}" +
            ",#{updateFrequency},curdate(),#{noteInformation}" +
            ",#{account},#{numberAttachments})")
    boolean insertPurchasingContract(PurchasingContract purchasingContract);

    @Update("update purchasingcontract set contractType=#{contractType},contractID=#{newContractID}" +
            ",dateContract=#{dateContract},supplier=#{supplier},contractAmount=#{contractAmount}" +
            ",noteInformation=#{noteInformation},updateFrequency=updateFrequency+1 where contractID=#{oldContractID}")
    boolean updatePurchasingContract(String contractType,String newContractID,String dateContract,String supplier
            ,double contractAmount,String noteInformation,String oldContractID);


//    @Select("select * from purchasingcontract where") like   '%${value}%'
    @Select("<script>" +
            "select purchasingcontract.* FROM purchasingcontract join purchaseinvoice  join purchaseinvoicedetail " +
            "<where>" +
            "1=1 "+
            "<if test=\"nameCommodity !=null  and nameCommodity!=''\">and nameCommodity like '%${nameCommodity}%'</if>" +
            "<if test=\"model !=null and model!=''\">and model like '%${model}%'</if>" +
            "<if test=\"supplier !=null and supplier!=''\">and supplier like '%${supplier}%'</if>" +
            "<if test=\"date !=null and date!=''\">and purchasingcontract.dateContract > #{date}</if>" +
            "<if test=\"noteInformation !=null and noteInformation!=''\">and purchasingcontract.noteInformation like '%${noteInformation}%'</if>" +
            "<if test=\"invoiceState !=null and invoiceState!=''\">and purchasingcontract.invoiceState like '%${invoiceState}%'</if>" +
            "<if test=\"contractID !=null and contractID!=''\">and purchasingcontract.contractID like '%${contractID}%'</if>" +
            "Group by purchasingcontract.contractID"+
            "</where>" +
            "</script>"
    )
    List<PurchasingContract> getContractsByConditions(String date,String nameCommodity,String model,String supplier,String noteInformation,String invoiceState,String contractID);

}
