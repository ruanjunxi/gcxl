package com.example.gcxl.mapper;

import com.example.gcxl.domain.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ClassName: BaseMapper
 * @author: Eason
 * @data: 2022/4/18 16:45
 * 通用的sql语句
 */
public interface BaseMapper {
    @Insert("insert into modifylog(creationDate,operationType,account,type,typeID,otherID) value (now(),#{operationType},#{account},#{type},#{typeID},#{otherID})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean insertModifyLog(ModifyLog modifyLog);

    @Insert("insert into modifylogdetails value (#{id},#{fieldNames},#{originalValue},#{newValue})")
    boolean insertModifyLogDetails(int id, String fieldNames, String originalValue, String newValue);

    //根据发票号、合同号、明细号查找修改记录
    @Select("select * from modifylog where typeID = #{typeID} and operationType = '修改'")
    List<ModifyLog> findModifyByTypeID(String typeID);

    @Select("select * from modifylog")
    List<ModifyLog> findAllModify();

    @Select("select * from modifylogdetails where id = #{id}")
    List<ModifyLogDetails> findLogDetailsById(int id);

    @Select("select * from purchasingcontract where contractID=#{contractID}")
    PurchasingContract findContractBycontractID(String contractID);

    @Select("select * from purchasingcontract")
    List<PurchasingContract> findAllContract();

    @Select("select sum(contractAmount) from purchasingcontract")
    int getAllContractAmount();

    @Select("select * from purchaseinvoice where invoiceID=#{invoiceID}")
    PurchaseInvoice findByInvoiceID(String invoiceID);

    @Select("select * from purchaseinvoice")
    List<PurchaseInvoice> findAllPurchaseInvoice();

    @Select("select * from purchaseinvoicedetail where invoiceID=#{invoiceID}")
    List<PurchaseInvoiceDetail> findDetailByInvoiceID(String invoiceID);

    @Select("select * from purchaseinvoicedetail where id=#{id}")
    PurchaseInvoiceDetail findInvoiceDetailByID(int id);

    @Select("select * from purchaseinvoicedetail")
    List<PurchaseInvoiceDetail> findAllDetail();

    @Select("select * from purchaseinvoice where contractID=#{contractID}")
    List<PurchaseInvoice> findInvoiceByContractID(String contractID);

    @Select("select COALESCE(sum(allAmount),0) from purchaseinvoice where contractID =#{contractID}")
    double getSumPurchaseInvoiceByContractID(String contractID);

    @Select("select COALESCE(count(*),0) from purchasingcontract")
    Integer getCountPurchasingContract();

    @Update("update purchasingcontract set invoiceState=#{invoiceState} where contractID= #{contractID}")
    boolean updatePurchasingInvoiceStateByContractID(String invoiceState,String contractID);

    @Insert("insert into file values(null,#{fileName},now(),#{typeID},#{filePath})")
    boolean insertFile(AttachmentFile attachmentFile);

    @Delete("delete from file where id=#{id}")
    boolean deleteFileByID(int id);

    @Select("select * from file where typeID=#{typeID}")
    List<AttachmentFile> getFilesByTypeID(String typeID);

    @Update("update purchasingcontract set numberAttachments= numberAttachments + #{numberAttachments} where contractID=#{contractID}")
    boolean updateNumberAttachments(int numberAttachments, String contractID);

    @Update("update purchaseinvoice set numberAttachments= numberAttachments + #{numberAttachments} where invoiceID=#{invoiceID}")
    boolean updateInvoiceNumberAttachments(int numberAttachments, String invoiceID);

}
