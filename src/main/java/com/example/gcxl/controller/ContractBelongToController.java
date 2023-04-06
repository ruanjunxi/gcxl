package com.example.gcxl.controller;

import com.example.gcxl.domain.ContractBelongTo;
import com.example.gcxl.domain.ResultData;
import com.example.gcxl.domain.ReturnCode;
import com.example.gcxl.domain.query.ContractBelongToCondition;
import com.example.gcxl.domain.query.ContractBelongToResult;
import com.example.gcxl.service.ContractBelongToService;
import com.example.gcxl.service.CostOccupationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
@RestController
@Api("合同所属")
@RequestMapping("contract_belongTo")
public class ContractBelongToController extends BaseController {
    @Autowired
    CostOccupationService costOccupationService;
    @Autowired
    ContractBelongToService belongToService;

    //    分页
    @ApiOperation("显示条件查询的合同所属管理页面数据")
    @PostMapping("/all_contractBelongTo_byCondition")
    public ResultData<ContractBelongToResult> findAllContractBelongToByCondition(@ApiParam("合同所属查询条件") @RequestBody @Valid ContractBelongToCondition condition, BindingResult bindResult) {
        System.out.println(condition);
        ResultData<ContractBelongToResult> contractResultResultData = new ResultData<>();
        if (bindResult.hasErrors()) {
            contractResultResultData.setState(201);
            List<String> collect = bindResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
            contractResultResultData.setMessage(JSONUtils.valueToString(collect));
            return contractResultResultData;
        }
//        System.out.println(condition);
        List<ContractBelongTo> list = belongToService.findAllBelongByCondition(condition);
        int counts = list.size();
        //分页
        PageHelper.startPage(condition.getPageInfo().getPageNum(), condition.getPageInfo().getPageSize());
        List<ContractBelongTo> list2 = belongToService.findAllBelongByCondition(condition);
        PageInfo<ContractBelongTo> info = new PageInfo<>(list2);
        ContractBelongToResult result = new ContractBelongToResult(info, counts);

        return new ResultData<>(ReturnCode.RC100, result);
    }

    @ApiOperation("添加合同所属")
    @GetMapping("/add_ContractBelongTo")
    public ResultData addContractBelongTo(@ApiParam("所属名称") @RequestParam String name,
                                          @ApiParam("联系人") @RequestParam(required = false, defaultValue = "") String contact,
                                          @ApiParam("联系号码") @RequestParam(required = false, defaultValue = "") String phone,
                                          @ApiParam("状态") @RequestParam String status
//                                          ,HttpSession session
    ) {
        ContractBelongTo belongTo = new ContractBelongTo();
        belongTo.setName(name);
        belongTo.setContact(contact);
        belongTo.setPhoneNumber(phone);
        belongTo.setStatus(status);
//        belongTo.setCreator(getUsernameFromSession(session));
        belongTo.setCreator("lyy");
        belongToService.addContractBelongTo(belongTo);
        return new ResultData<>(ReturnCode.RC100);
    }
    @ApiOperation("修改合同所属")
    @GetMapping("/modify_ContractBelongTo")
    public ResultData modifyContractBelongTo(@ApiParam("id") @RequestParam Integer id,
                                          @ApiParam("所属名称") @RequestParam String name,
                                          @ApiParam("联系人") @RequestParam(required = false, defaultValue = "") String contact,
                                          @ApiParam("联系号码") @RequestParam(required = false, defaultValue = "") String phone,
                                          @ApiParam("状态") @RequestParam String status
//            ,HttpSession session
    ) {
        ContractBelongTo belongTo = new ContractBelongTo();
        belongTo.setName(name);
        belongTo.setContact(contact);
        belongTo.setPhoneNumber(phone);
        belongTo.setStatus(status);
        belongTo.setId(id);
//        belongTo.setCreator(getUsernameFromSession(session));
        belongTo.setCreator("lyy");
        belongToService.modifyContractBelongTo(belongTo);
        return new ResultData<>(ReturnCode.RC100);
    }
}
