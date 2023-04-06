package com.example.gcxl.mapper;

import com.example.gcxl.domain.OutBound;
import com.example.gcxl.domain.OutBoundDetails;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ClassName: OutBound
 * @author: Eason
 * @data: 2022/4/19 20:41
 */

public interface OutBoundMapper extends BaseMapper{
    @Insert({
            "<script>",
            "insert into  outbounddetails values ",
            "<foreach collection='item' item='item' index='index' separator=','>",
            "(#{item.id},#{item.outboundID},#{item.tradeName},#{item.nameCommodity},#{item.model}," +
                    "#{item.unitPrice},#{item.outboundNumber},#{item.invoiceID},#{item.contractID})",
            "</foreach>",
            "</script>"
    })
//    @Insert("insert into outbounddetails value (id=#{id},outboundID=#{outboundID},tradeName=#{tradeName},nameCommodity=#{nameCommodity},model=#{model}," +
//            "unitPrice=#{unitPrice},numberRemain=#{numberRemain},invoiceID=#{invoiceID},contractID=#{contractID})")
    boolean insertOutBoundDetails(@Param("item") List<OutBoundDetails> outBoundDetails);

    @Select("<script>" +
            "select id,tradeName,nameCommodity,model,unitPrice,numberRemain,invoiceID,contractID from  purchaseinvoicedetail join purchaseinvoice using(invoiceID)" +
            "<where>" +
            "1=1 "+
            "<if test=\"tradeName !=null  and tradeName!=''\"> and  tradeName like '%${tradeName}%' </if>" +
            "<if test=\"nameCommodity !=null  and nameCommodity!=''\"> and nameCommodity like '%${nameCommodity}%' </if> "+
            "<if test=\"model !=null and model!=''\"> and model like '%${model}%' </if>" +
            "</where>" +
            "</script>"
    )

//    @Select("select id,tradeName,nameCommodity,model,unitPrice,numberRemain,invoiceID,contractID from purchaseinvoicedetail join purchaseinvoice using(invoiceID) " )
    List<OutBoundDetails> findOutBoundDetailByTradeCommodityModel(String tradeName, String nameCommodity, String model);

    @Insert("insert into outbound value (#{outboundID},#{deliveryDate},#{outBoundType},#{account},now(),#{state})")
    boolean insertOutBound(OutBound outBound);

    @Select("select * from outbound")
    List<OutBound> getAllOutBound();

    @Select("select * from outbounddetails where outBoundID=#{outBoundID}")
    List<OutBoundDetails> findOutBoundDetailsByOutBoundID(String outBoundID);

    @Select("select * from outbound where outBoundID=#{outBoundID}")
    OutBound getOutBoundByID(String outBoundID);

    @Update("Update outbound set state=\"作废\" where outBoundID=#{outBoundID}")
    boolean updateOutBoundState(String outBoundID);

    @Update("update purchaseinvoicedetail set numberRemain=#{newNumberRemain},outboundNumber=#{outboundNumber} where id =#{id}")
    boolean updateRemainAndOutNumberById(int newNumberRemain,int outboundNumber,int id);

    @Select("select count(*) from outbound")
    Integer getCountOutbound();

    @Select("<script>" +
            "select * from outbound " +
            "<where>" +
            "1=1 "+
            "<if test=\"outBoundID !=null  and outBoundID!=''\"> and outBoundID like '%${outBoundID}%' </if>" +
            "<if test=\"outBoundType !=null  and outBoundType!=''\"> and outBoundType=#{outBoundType} </if> "+
            "<if test=\"state !=null and state!=''\"> and state =#{state} </if>" +
            "</where>" +
            "</script>"
    )
    List<OutBound> getOutboundByConditions(String outBoundID,String outBoundType,String state);
}
