package com.example.gcxl.mapper;

import com.example.gcxl.domain.SaleInvoice;
import com.example.gcxl.domain.SaleInvoiceDetail;
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
public interface SaleInvoiceMapper extends BaseMapper{

    //添加发票
    Integer addInvoice(SaleInvoice invoice);

    //批量添加发票明细
    Integer addBatchInvoiceDetail(List<SaleInvoiceDetail> detailList);

    @Select("select count(*) from saleinvoice where invoiceID = #{invoiceID}")
    Integer selectById(String invoiceID);
    //根据发票ID来查询发票
    @Select("select * from saleinvoice where invoiceID = #{invoiceID}")
    SaleInvoice selectInvoiceById(String invoiceID);
    //

    /**
     *
     * @param id
     * @return
     *  根据明细id来查询id
     */
    @Select("select * from saleinvoicedetail where id = #{id}")
    SaleInvoiceDetail selectInvoiceDetailById(long id);

    @Select("select COALESCE(sum(amount),0) from saleinvoice where contractID =#{contractID}")
    float getSumSaleInvoiceByContractID(String contractID);

    //修改发票信息
    @Update("update saleinvoice set invoiceDate = #{date}, invoiceType=#{type}, updateFrequency=updateFrequency+1 where invoiceID=#{invoiceID}")
    Integer updateInvoiceById(String invoiceID,String date, String type);

    //修改明细
    Integer updateInvoiceDetailById(SaleInvoiceDetail detail);

//根据合同编号查询发票
    @Select("select * from saleinvoice where contractID =#{contractID}")
    List<SaleInvoice> getSaleInvoiceByContractId(String contractID);
//根据发票号来查询明细
    @Select("select * from saleinvoicedetail where invoiceID =#{invoiceID}")
    List<SaleInvoiceDetail> getSaleInvoiceDetailByInvoiceId(String invoiceID);

    @Update("update purchaseinvoicedetail set correlationNumber=correlationNumber+#{num},numberRemain=numberRemain-#{num} where id =#{id}")
    Integer updateCorrelationNumberAndRemainNumberById(int id, int num);

    @Update("update saleinvoice set relatedAmount=#{relatedAmount},correlationRate=#{relatedAmount}*100/amount where invoiceID =#{invoiceID}")
    Integer updateSaleInvoiceWhenRelate(String invoiceID,double relatedAmount);

    @Update("update saleinvoice set amount=#{amount}, where invoiceID =#{invoiceID}")
    Integer updateAmount(String invoiceID, float amount);

    @Select("select sum(amount) from saleinvoice where contractID =#{contractID} and invoiceID!=#{invoiceID}")
    float getOtherInvoiceAmount(String contractID, String invoiceID);

    @Select("select contractID from saleinvoice where invoiceID =#{invoiceID}")
    String getContractIdByInvoiceId(String invoiceID);

    @Select("select avg(correlationRate) from saleinvoice where contractID=#{contractID}")
    float getAVGCorrelationRate(String contractID);
    @Update("update saleinvoice set relatedAmount=relatedAmount-#{relationAmount}, correlationRate=relatedAmount*100/amount where invoiceID =#{invoiceID}")
    Integer updateSaleInvoiceWhenCancelRelation(String invoiceID,double relationAmount);

    List<SaleInvoice> getSaleInvoiceByCondition(String invoiceId, String purchasingUnit, String date1,String date2);

    @Select("select invoiceID from saleinvoicedetail where id=#{id}")
    String getInvoiceIDByDetailId(long id);

    @Update("update saleinvoice set status=#{status} where invoiceID=#{saleInvoiceID}")
    void setStatus(String status,String saleInvoiceID );
    @Select("select contractID from purchaseinvoice i left join purchaseinvoicedetail d on (i.invoiceID=d.invoiceID) where d.id=#{id}")
    String getPurchaseContractIdByCommodityId(int id);

    @Select("select param_description from param where param_name='关联率'")
    Integer getRelationRate();

    @Select("select correlationRate from saleinvoice where invoiceID=#{saleInvoiceID}")
    Float getSaleInvoiceRelationRateById(String saleInvoiceID);

    @Update("update saleinvoice set operation=#{s} where invoiceID=#{invoiceID}")
    void setOperation(String s, String invoiceID);
    @Update("update saleinvoice set operation=#{s},status=#{status} where invoiceID=#{invoiceID}")
    void recall(String s, String status, String invoiceID);
}
