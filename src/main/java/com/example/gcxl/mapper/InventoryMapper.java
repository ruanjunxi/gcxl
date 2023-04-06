package com.example.gcxl.mapper;

import com.example.gcxl.domain.Inventory;
import com.example.gcxl.domain.OutBoundWithDetail;
import com.example.gcxl.domain.RelatedSales;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @ClassName: InventoryMapper
 * @author: Eason
 * @data: 2022/4/23 14:58
 */
public interface InventoryMapper {
    @Select("select contractID,id,tradeName,nameCommodity,model,unitPrice,amount,invoiceID,invoiceType,makeOutAnInvoiceDate,privateIntAmount,correlationNumber,outboundNumber,numberRemain,noteInformation " +
            " from purchaseinvoicedetail LEFT JOIN purchaseinvoice using(invoiceID) order by numberRemain")
    List<Inventory> getAllInventory();

    @Select("<script>" +
            "select contractID,id,tradeName,nameCommodity,model,unitPrice,amount,invoiceID,invoiceType,makeOutAnInvoiceDate,privateIntAmount,correlationNumber,outboundNumber,numberRemain,noteInformation" +
            " from purchaseinvoicedetail LEFT JOIN purchaseinvoice using(invoiceID)" +
            "<where>" +
            "1=1 "+
            "<if test=\"tradeName !=null  and tradeName!=''\"> and tradeName like '%${tradeName}%' </if>" +
            "<if test=\"nameCommodity !=null  and nameCommodity!=''\"> and nameCommodity like '%${nameCommodity}%' </if> "+
            "<if test=\"model !=null and model!=''\"> and model like '%${model}%'</if>" +
            "<if test=\"contractID !=null and contractID!=''\"> and contractID like '%${contractID}%'</if>" +
            "<if test=\"invoiceID !=null and invoiceID!=''\"> and invoiceID like '%${invoiceID}%'</if>" +
            "order by ${order} ${orderType}"+
            "</where>" +
            "</script>"
    )
    List<Inventory> getInventoryByIDTradeCommodityModel(String tradeName, String nameCommodity, String model,String contractID,String invoiceID,@Param("order") String order,@Param("orderType")String orderType);

    @Select("SELECT outboundNumber,outboundID,account,creationDate FROM outbound JOIN outbounddetails USING(outboundID) WHERE id = #{id}")
    List<OutBoundWithDetail> getOutBoundByID(int id);

    @Update("update purchaseinvoicedetail set noteInformation=#{noteInformation} where id=#{id}")
    boolean updateDetailNote(String noteInformation,int id);

    @Select("SELECT associatedNumber,takeup.invoiceID,contractID,purchasingUnit,operator,operationDate " +
            "FROM takeup join salesinvoice using(invoiceID) JOIN salescontract using(contractID) where commodityID=#{id}")
    List<RelatedSales> getRelatedSalesByID(int id);


}
