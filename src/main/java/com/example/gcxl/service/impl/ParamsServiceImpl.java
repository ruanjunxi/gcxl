package com.example.gcxl.service.impl;

import com.example.gcxl.domain.Params;
import com.example.gcxl.mapper.ParamsMapper;
import com.example.gcxl.service.ParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ParamServiceImpl
 * @author: Eason
 * @data: 2022/4/24 14:30
 */
@Service
public class ParamsServiceImpl implements ParamsService {
    @Autowired
    private ParamsMapper paramsMapper;
    @Override
    public void updateParams(int paramDescription, String account) {
        paramsMapper.updateParams(paramDescription,account);
    }

    @Override
    public Params getParams() {
        Params params = paramsMapper.getParams();
//        String replace = params.getModifiedTime().replace(".0", "");
//        params.setModifiedTime(replace);
        return params;
    }
}
