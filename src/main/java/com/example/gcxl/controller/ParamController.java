package com.example.gcxl.controller;

import com.example.gcxl.domain.Params;
import com.example.gcxl.domain.ResultData;
import com.example.gcxl.domain.ReturnCode;
import com.example.gcxl.service.ParamsService;
import com.example.gcxl.util.IpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassName: ParamController
 * @author: Eason
 * @data: 2022/4/24 14:32
 */
@RestController
@Api("参数接口")
@RequestMapping("param")
@Transactional(rollbackFor=Exception.class)

public class ParamController extends BaseController{
    @Autowired
    private ParamsService paramsService;

    @GetMapping("/updateParams")
    @ApiOperation("更新参数值")
    public ResultData<Void> updateParams(@ApiParam("参数值")@RequestParam(required = false) Integer paramDescription, HttpSession session){
        if (paramDescription==null){
            throw new NullPointerException("传入的数据存在空值");
        }
//        String account = getUsernameFromSession(session);
        String account ="eason";
        paramsService.updateParams(paramDescription,account);
        return new ResultData<>(ReturnCode.RC100);
    }

    @GetMapping("/getParams")
    @ApiOperation("获取参数")
    public ResultData<Params> getParams(HttpServletRequest request){
        Params params = paramsService.getParams();
        return new ResultData<>(ReturnCode.RC100, params);
    }

}
