package com.example.gcxl.mapper;

import com.example.gcxl.domain.PurchaseInvoiceDetail;
import com.example.gcxl.domain.SaleInvoice;
import com.example.gcxl.domain.TakeUp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
//表示当前的类是一个测试类，不会随同项目一起打包
@SpringBootTest
//表示启动这个单元测试类，单元测试类是不能够运行的
@RunWith(SpringRunner.class)
public class SaleInvoiceTests {
    @Autowired
    SaleInvoiceMapper saleInvoiceMapper;
    @Autowired
    PurchaseInvoiceMapper purchaseInvoiceMapper;
    @Autowired
    CostOccupationMapper costOccupationMapper;
    @Test
    @Transactional
    public void test1(){
        PurchaseInvoiceDetail detail = purchaseInvoiceMapper.findInvoiceDetailByID(12);
        int res = saleInvoiceMapper.updateCorrelationNumberAndRemainNumberById(12,6);
        TakeUp takeup=new TakeUp(detail,"fp2204251748","采购合同0002","lyy",6);
        //添加takeup记录
        res = costOccupationMapper.addTakeup(takeup);
        System.out.println(res);
    }
    
}
