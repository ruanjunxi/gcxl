package com.example.gcxl;

import com.example.gcxl.domain.SaleInvoice;
import com.example.gcxl.mapper.SaleInvoiceMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GcxlApplicationTests {
    @Autowired
    SaleInvoiceMapper invoiceMapper;

    @Test
    void contextLoads() {


        List<SaleInvoice> list = invoiceMapper.getSaleInvoiceByContractId("sc2204251721");
        System.out.println(list);
    }

}
