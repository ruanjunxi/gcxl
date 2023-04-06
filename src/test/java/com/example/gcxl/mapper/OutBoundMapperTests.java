package com.example.gcxl.mapper;

import com.example.gcxl.domain.OutBound;
import com.example.gcxl.domain.OutBoundDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ImageBanner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;

/**
 * @ClassName: OutBoundMapperTests
 * @author: Eason
 * @data: 2022/4/19 22:18
 */
@SpringBootTest
//表示启动这个单元测试类，单元测试类是不能够运行的
@RunWith(SpringRunner.class)
public class OutBoundMapperTests {
    @Autowired
    private OutBoundMapper outBoundMapper;
    @Test
    public void test(){
        List<OutBoundDetails> outBound = outBoundMapper.findOutBoundDetailByTradeCommodityModel("123","","");
        for (OutBoundDetails outBoundDetails:outBound){
            System.out.println(outBoundDetails);
        }
    }
    @Test
    public void insertOutBoundDetails(){
        List<OutBoundDetails> outBoundDetails = new ArrayList<>();
        OutBoundDetails outBoundDetails1 = new OutBoundDetails(12, "出库001", "002", "002", "002", 10, 10, 10, "发票002", "采购合同0001");
        outBoundDetails.add(outBoundDetails1);
        outBoundMapper.insertOutBoundDetails(outBoundDetails);
    }
    @Test
    public void insertOutBound(){
        OutBound outBound = new OutBound("出库001", "2022-05-30", "1", "eason", "正常");
        outBoundMapper.insertOutBound(outBound);
    }
    @Test
    public void findOutBoundDetailsByOutBoundID(){
        List<OutBoundDetails> outBoundDetailsByOutBoundID = outBoundMapper.findOutBoundDetailsByOutBoundID("出库001");
        for (OutBoundDetails o:outBoundDetailsByOutBoundID){
            System.out.println(o);
        }
    }
    @Test
    public void updateRemainAndOutNumberById(){
        outBoundMapper.updateRemainAndOutNumberById(10,5,11);
    }
}
