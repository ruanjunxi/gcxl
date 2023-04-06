package com.example.gcxl.service;

import com.example.gcxl.domain.Params;

/**
 * @ClassName: ParamService
 * @author: Eason
 * @data: 2022/4/24 14:29
 */
public interface ParamsService {
    void updateParams(int paramDescription, String account);
    Params getParams();
}
