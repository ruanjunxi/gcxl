package com.example.gcxl.service;

import com.example.gcxl.domain.query.BusinessQueryResult;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
public interface BusinessQueryService {
    List<BusinessQueryResult> getSaleInfo(String contractID);

    List<BusinessQueryResult> getTakeUpInfo(String contractID);
}
