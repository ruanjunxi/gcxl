package com.example.gcxl.controller;

import com.example.gcxl.domain.*;
import com.example.gcxl.domain.query.ContractCondition;
import com.example.gcxl.domain.query.ContractResult;
import com.example.gcxl.service.SaleContractService;
import com.example.gcxl.service.SaleInvoiceService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.transaction.annotation.Transactional;
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
@Api("销售合同接口")
@RequestMapping("sale_contract")

public class SaleContractController extends BaseController{
    @Autowired
    private SaleContractService contractService;

    @Autowired
    private SaleInvoiceService invoiceService;


//    public ResultData addContract(@ApiParam("合同所属") String contractBelongTo, @ApiParam("合同编号") String contractId, @ApiParam("区域标识") String region,
//                                  @ApiParam("合同日期")Date contractDate, @ApiParam("采购单位") String purchasingUnit, @ApiParam("合同金额") float amount,
//                                  @ApiParam("备注信息") String comment){
//        Date createDate = new Date();
//        java.sql.Date sqlDate = new java.sql.Date(createDate.getTime());
//        SaleContract contract = new SaleContract(contractId,contractBelongTo,region,contractDate,purchasingUnit,amount,
//                comment,"d:rjx",0F,"未开票",sqlDate,"lyy");
//        service.AddContract(contract);
//    }
    //, HttpSession session
    @ApiOperation("新增销售合同")
    @PostMapping("add_new_Contract")
    @Transactional
    public ResultData addContract(@ApiParam("销售合同实体") @RequestBody SaleContract contract){
        if(contract.getComment()==null){
            contract.setComment("");
        }
//        Date createDate = new Date();
//        java.sql.Date sqlDate = new java.sql.Date(createDate.getTime());
//        contract.setCreationDate(sqlDate);
        contract.setCorrelationRate(0f);
        contract.setInvoiceState("未开票");
//        contract.setCreator(getUsernameFromSession(session));
       // System.out.println(contract.toString());
        contract.setCreator("lyy");
        contractService.AddContract(contract);
        return new ResultData<>(ReturnCode.RC100);
    }

    @ApiOperation("修改合同")
    @GetMapping("modify_contract")
    @Transactional
    public ResultData modifyContract(@ApiParam("合同所属")@RequestParam(value = "contractBelongsTo") String contractBelongsTo,
                                     @ApiParam("区域标识")@RequestParam(value = "region") String region,
                                     @ApiParam("合同编号")@RequestParam(value = "contractId") String ContractId,
                                     @ApiParam("合同日期")@RequestParam(value = "contractDate") String contractDate,
                                     @ApiParam("采购单位")@RequestParam(value = "purchasingUnit") String purchasingUnit,
                                     @ApiParam("合同金额")@RequestParam(value = "contractAmount") float contractAmount,
                                     @ApiParam("备注")@RequestParam(value = "comment",required = false) String comment
//                                     HttpSession session
    )
                                     {
        if(comment==null) comment="";
                                         System.out.println(contractBelongsTo);
       // java.sql.Date sqlDate = new java.sql.Date(contractDate.getTime());
        contractService.modifyContract(contractBelongsTo, region, ContractId,
//                contractDate, purchasingUnit, contractAmount, comment, oldContractId,getUsernameFromSession(session));
                contractDate, purchasingUnit, contractAmount, comment,"lyy");
        return new ResultData<>(ReturnCode.RC100);
    }
//    //    不分页
//    @ApiOperation("显示所有采购合同(不分页)")
//    @GetMapping("/all_contract")
//    public ResultData<List<SaleContract>> findAllContract() {
//        List<SaleContract> allContract = contractService.;
//        return new ResultData<>(ReturnCode.RC100, allContract);
//    }

    //    分页
//    @ApiOperation("显示所有销售合同(分页)")
//    @GetMapping("/all_contract")
//    public ResultData<QueryResult> findAllPagContract(@RequestParam(required = true,defaultValue = "1") int pageNum,
//                                                                       @RequestParam(required = true,defaultValue = "15")int pageSize) {
//        PageInfo<SaleContract> allPagContract = contractService.findAllPageContract(pageNum,pageSize);
//        int counts = contractService.getAllCounts();
//        Float money=contractService.getAllAmount();
//        QueryResult result = new QueryResult(allPagContract,money,counts);
//        return new ResultData<>(ReturnCode.RC100, result);
//    }
    //    分页
    @ApiOperation("显示条件查询的销售合同(分页)")
    @PostMapping("/all_contract_byCondition")
    public ResultData<ContractResult> findAllContractByCondition(@ApiParam("销售合同查询条件") @RequestBody @Valid ContractCondition condition, BindingResult bindResult) {
        ResultData<ContractResult> contractResultResultData = new ResultData<>();
        if (bindResult.hasErrors()) {
            contractResultResultData.setState(201);
            List<String> collect = bindResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
            contractResultResultData.setMessage(JSONUtils.valueToString(collect));
            return contractResultResultData;
        }
//        System.out.println(condition);
        List<SaleContract> list = contractService.findAllContractByCondition(condition);
        int counts = list.size();
        Float money = 0f;
        for (SaleContract item:list) {
            money+=item.getContractAmount();
        }
//        System.out.println(money);
        //分页
        PageInfo<SaleContract> info = contractService.findAllPageSaleContract(condition.getPageInfo().getPageNum(),condition.getPageInfo().getPageSize(),condition);
        ContractResult result = new ContractResult(info,money,counts);

        return new ResultData<>(ReturnCode.RC100, result);
    }
//    @ApiOperation("显示所有销售合同总金额")
//    @GetMapping("/get_amount")
//    public ResultData<Float> getAllContractAmount() {
//        Float allContractAmount = contractService.getAllAmount();
//        return new ResultData<>(ReturnCode.RC100, allContractAmount);
//    }

//    @ApiOperation("获取销售合同数量")
//    @GetMapping("/get_contract_num")
//    public ResultData<Integer> getCountPurchasingContract() {
//        int counts = contractService.getAllCounts();
//        return new ResultData<>(ReturnCode.RC100, counts);
//    }

    @ApiOperation("查询当前合同下的发票以及明细")
    @GetMapping("/get_invoiceAndDetail")
    public ResultData<List<SaleInvoice>> findInvoiceAndDetailByContract(@ApiParam("销售合同ID") @RequestParam String contractId){
        List<SaleInvoice> list = invoiceService.getSaleInvoiceByContractId(contractId);
        return new ResultData<>(ReturnCode.RC100, list);
    }

//    @ApiOperation("显示所有的合同所属")
//    @PostMapping("/all_contract_belongto")
    @ApiOperation("获取所有合同所属的名称")
    @GetMapping("/get_allBelongTo_name")
    public ResultData<List<String>> getAllBelongToName(){
        List<String> list = contractService.getAllBelongToName();
        return new ResultData<>(ReturnCode.RC100, list);
    }

    @ApiOperation("获取当前合同的修改日志")
    @GetMapping("/get_contract_modifyLog")
    public ResultData<List<ModifyLogWithDetails>> getContractModifyLog(@ApiParam("合同ID") @RequestParam String contractID){
        List<ModifyLogWithDetails> list = contractService.getModifyWithDetailByTypeID(contractID);
        return new ResultData<>(ReturnCode.RC100, list);
    }

    @ApiOperation("首页显示所有的销售合同数量")
    @GetMapping("/get_contractNums")
    public ResultData<Integer> getContractModifyLog(){
        Integer num= contractService.getAllCounts();
        return new ResultData<>(ReturnCode.RC100,num);
    }
}
