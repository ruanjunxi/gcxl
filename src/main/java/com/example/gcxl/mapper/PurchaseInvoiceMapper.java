package com.example.gcxl.mapper;

import com.example.gcxl.domain.PurchaseInvoice;
import com.example.gcxl.domain.PurchaseInvoiceDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ClassName: PurchaseInvoiceMapper
 * @author: Eason
 * @data: 2022/4/18 8:34
 */

public interface PurchaseInvoiceMapper extends BaseMapper {
    @Insert("insert into purchaseinvoice value (#{invoiceID},#{makeOutAnInvoiceDate},#{invoiceType},curdate(),#{account}," +
            "#{invoiceUpdateFrequency},#{contractID},#{allAmount},#{numberAttachments})")
    @Options(useGeneratedKeys = true, keyProperty = "invoiceID", keyColumn = "invoiceID")
    boolean insertPurchaseInvoice(PurchaseInvoice purchaseInvoice);

    @Insert("insert into purchaseinvoicedetail(invoiceID,tradeName,nameCommodity,model,privateIntAmount,unitPrice," +
            "numberRemain,amount,correlationNumber,outboundNumber,detailUpdateFrequency) " +
            "value (#{invoiceID},#{tradeName},#{nameCommodity},#{model},#{privateIntAmount}," +
            "#{unitPrice},#{numberRemain},#{amount},#{correlationNumber},#{outboundNumber},#{detailUpdateFrequency})")
    boolean insertPurchaseInvoiceDetails(PurchaseInvoiceDetail purchaseInvoiceDetail);

    @Update("update purchaseinvoice set invoiceID=#{newInvoiceID},makeOutAnInvoiceDate=#{newMakeOutAnInvoiceDate}," +
            "invoiceType=#{newInvoiceType},invoiceUpdateFrequency=invoiceUpdateFrequency+1 where invoiceID =#{oldInvoiceID}")
    boolean updatePurchaseInvoice(String newInvoiceID, String newMakeOutAnInvoiceDate, String newInvoiceType, String oldInvoiceID);

    @Update("update purchaseinvoice set allAmount=#{allAmount} where invoiceID =#{invoiceID}")
    boolean updatePurchaseInvoiceAmount(double allAmount,String invoiceID);

    @Update("update purchaseinvoicedetail set tradeName=#{tradeName},nameCommodity=#{nameCommodity},model=#{model}" +
            ",privateIntAmount=#{privateIntAmount},unitPrice=#{unitPrice},numberRemain=#{numberRemain},amount=#{amount},detailUpdateFrequency=detailUpdateFrequency+1" +
            " where id=#{id}")
    boolean updatePurchaseInvoiceDetail(PurchaseInvoiceDetail purchaseInvoiceDetail);

//    @Update("update purchaseinvoice set detailUpdateFrequency=detailUpdateFrequency+1 where invoiceID =#{invoiceID}")
//    boolean updateDetailUpdateFrequency(String invoiceID);


    @Insert({
            "<script>",
            "insert into  purchaseinvoicedetail(invoiceID,tradeName,nameCommodity,model,privateIntAmount," +
                    "unitPrice,numberRemain,amount,correlationNumber,outboundNumber,detailUpdateFrequency) values ",
            "<foreach collection='item' item='item' index='index' separator=','>",
            "(#{item.invoiceID}, #{item.tradeName},#{item.nameCommodity},#{item.model},#{item.privateIntAmount}," +
                    "#{item.unitPrice},#{item.privateIntAmount},#{item.amount},#{item.correlationNumber},#{item.outboundNumber},#{item.detailUpdateFrequency})",
            "</foreach>",
            "</script>"
    })
    int bulkInsertPurchasingInvoiceDetail(@Param("item") List<PurchaseInvoiceDetail> purchaseInvoiceDetails);

}
