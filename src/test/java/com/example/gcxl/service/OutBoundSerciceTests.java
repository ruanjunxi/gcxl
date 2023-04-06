package com.example.gcxl.service;

import com.example.gcxl.domain.OutBound;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName: OutBoundSerciceTests
 * @author: Eason
 * @data: 2022/4/20 10:25
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class OutBoundSerciceTests {
    @Autowired
    private OutBoundService outBoundService;

    @Autowired
    CostOccupationService costOccupationService;
//    @Test
//    public void getAllOutBound(){
//        costOccupationService.commodityRelated(id,nums,saleInvoiceID,purchasingContractID,getUsernameFromSession(session));
//    }
}

