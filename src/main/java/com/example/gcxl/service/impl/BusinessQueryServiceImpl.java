package com.example.gcxl.service.impl;

import com.example.gcxl.domain.query.BusinessQueryResult;
import com.example.gcxl.mapper.BusinessQueryMapper;
import com.example.gcxl.service.BusinessQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
@Service
public class BusinessQueryServiceImpl implements BusinessQueryService {
    @Autowired
    BusinessQueryMapper queryMapper;
    @Override
    public List<BusinessQueryResult> getSaleInfo(String contractID) {
        List<BusinessQueryResult> list = queryMapper.getSaleInfo(contractID);
        return list;
    }

    @Override
    public List<BusinessQueryResult> getTakeUpInfo(String contractID) {
        List<BusinessQueryResult> list = queryMapper.getTakeUpInfo(contractID);
        return list;
    }
}
