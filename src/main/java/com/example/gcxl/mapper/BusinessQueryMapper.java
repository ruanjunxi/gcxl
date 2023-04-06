package com.example.gcxl.mapper;

import com.example.gcxl.domain.User;
import com.example.gcxl.domain.query.BusinessQueryResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Eason
 * @ClassName:Usermapper
 * @data:2022/4/12 19:20
 */

public interface BusinessQueryMapper{


    List<BusinessQueryResult> getSaleInfo(String contractID);

    List<BusinessQueryResult> getTakeUpInfo(String contractID);
}
