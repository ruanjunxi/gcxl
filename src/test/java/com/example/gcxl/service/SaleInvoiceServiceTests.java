package com.example.gcxl.service;

import com.example.gcxl.domain.SaleInvoice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleInvoiceServiceTests {
    @Autowired
    SaleInvoiceService invoiceService;

    @Test
    public void test1(){
        List<SaleInvoice> list = invoiceService.getSaleInvoiceByContractId("sc2204251721");
        System.out.println(list);
    }
}
