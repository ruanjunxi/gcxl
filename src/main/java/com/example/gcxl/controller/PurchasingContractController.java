package com.example.gcxl.controller;

import com.example.gcxl.controller.ex.*;
import com.example.gcxl.domain.ModifyLogWithDetails;
import com.example.gcxl.domain.PurchasingContract;
import com.example.gcxl.domain.ResultData;
import com.example.gcxl.domain.ReturnCode;
import com.example.gcxl.service.PurchasingContractService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author Eason
 * @ClassName:PurchasingContractController
 * @data:2022/4/17 14:57
 */
@RestController
@Api("采购合同接口")
@RequestMapping("purchasingcontract")
@Transactional(rollbackFor=Exception.class)
@Slf4j
//@CrossOrigin(origins = "http://172.17.169.18:8080", maxAge = 3600,allowCredentials = "true")
public class PurchasingContractController extends BaseController {
    @Autowired
    private PurchasingContractService purchasingContractService;

    @ApiOperation("新增采购合同")
    @PostMapping("/insertpurchasingcontracts")
    public ResultData<Void> insertPurchasingContract(@ApiParam("采购合同实体类")@RequestBody PurchasingContract purchasingContract, HttpSession session) {
        if (purchasingContract==null){
            throw new NullPointerException("传入的数据存在空值");
        }
        System.out.println(purchasingContract);
        if (purchasingContract.getNoteInformation()==null){
            purchasingContract.setNoteInformation("");
        }
        System.out.println(purchasingContract.getDateContract());
        if (purchasingContract.getDateContract()==null){
            purchasingContract.setDateContract("2888-08-08");
        }
//        String gmt = purchasingContract.getDateContract().replace("GMT", "").replaceAll("\\(.*\\)", "");
//        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss z", Locale.ENGLISH);
//        try {
//            Date parse = format.parse(gmt);
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//            purchasingContract.setDateContract(df.format(parse).toString());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        purchasingContract.setInvoiceState("未开票");
        purchasingContract.setAccount("eason");
//        purchasingContract.setAccount(getUsernameFromSession(session));
        purchasingContractService.insertPurchasingContract(purchasingContract);
        return new ResultData<>(ReturnCode.RC100);
    }


    @ApiOperation("修改采购合同")
    @GetMapping("/updatepurchasingcontract")
    public ResultData<Void> updatePurchasingContract(@ApiParam("合同类型")@RequestParam(value = "contractType",required = false) String contractType,
                                                     @ApiParam("新合同ID") @RequestParam(value = "contractID",required = false)String contractID,
                                                     @ApiParam("合同日期") @RequestParam(value = "dateContract",required = false)String dateContract,
                                                     @ApiParam("供应商") @RequestParam(value = "supplier",required = false)String supplier,
                                                     @ApiParam("合同金额") @RequestParam(value = "contractAmount",required = false)Double contractAmount,
                                                     @ApiParam("备注信息") @RequestParam(value = "noteInformation",required = false)String noteInformation,
                                                     HttpSession session) {
        if (contractType==null||contractID==null||dateContract==null||supplier==null||contractAmount==null){
            throw new NullPointerException("传入的数据存在空值");
        }
        String account = getUsernameFromSession(session);
        if (noteInformation==null){
            noteInformation="";
        }
        String oldContractID=contractID;
        purchasingContractService.updatePurchasingContract(contractType, contractID, dateContract, supplier,
                contractAmount, noteInformation, oldContractID, account);
        return new ResultData<>(ReturnCode.RC100);
    }

//    不分页
    @ApiOperation("显示所有采购合同(不分页)")
    @GetMapping("/findAllContract")
    public ResultData<List<PurchasingContract>> findAllContract() {
        List<PurchasingContract> allContract = purchasingContractService.findAllContract();
        return new ResultData<>(ReturnCode.RC100, allContract);
    }

//    分页
    @ApiOperation("显示所有采购合同(分页)")
    @GetMapping("/findAllPagContract")
    public ResultData<PageInfo<PurchasingContract>> findAllPagContract(@RequestParam(required = false) Integer pageNum,
                                                                       @RequestParam(required = false) Integer pageSize) {
        if (pageNum==null||pageSize==null){
            throw new NullPointerException("传入的数据存在空值");
        }
        int i = pageNum.intValue();
        int j = pageSize.intValue();
        PageInfo<PurchasingContract> allPagContract = purchasingContractService.findAllPagContract(i, j);
        return new ResultData<>(ReturnCode.RC100, allPagContract);
    }



    @ApiOperation("显示所有采购合同总金额")
    @GetMapping("/getAllContractAmount")
    public ResultData<Integer> getAllContractAmount(HttpSession session) {

        int allContractAmount = purchasingContractService.getAllContractAmount();
        return new ResultData<>(ReturnCode.RC100, allContractAmount);
    }

    @ApiOperation("通过采购合同id来查找采购合同")
    @GetMapping("/findByContractID")
    public ResultData<PurchasingContract> findByContractID(@ApiParam("采购合同ID")@RequestParam(required = false) String contractID,HttpSession session) {
        log.info(session.getId());
        if (contractID==null){
            throw new NullPointerException("传入的数据存在空值");
        }
        PurchasingContract bycontractID = purchasingContractService.findByContractID(contractID);
        return new ResultData<>(ReturnCode.RC100, bycontractID);
    }

    @ApiOperation("通过typeID来查找返回修改日志和修改明细")
    @GetMapping("/getModifyWithDetailByTypeID")
    public ResultData<List<ModifyLogWithDetails>> getModifyWithDetailByTypeID(@ApiParam("TypeID")@RequestParam(required = false) String typeID) {
        if (typeID==null){
            throw new NullPointerException("传入的数据存在空值");
        }
        List<ModifyLogWithDetails> modifyWithDetailByTypeList = purchasingContractService.getModifyWithDetailByTypeID(typeID);
        return new ResultData<>(ReturnCode.RC100, modifyWithDetailByTypeList);
    }

    @ApiOperation("获取采购合同数量")
    @GetMapping("/getCountPurchasingContract")
    public ResultData<Integer> getCountPurchasingContract(HttpSession session) {
        log.info(session.getId());
        int countPurchasingContract = purchasingContractService.getCountPurchasingContract();
        return new ResultData<>(ReturnCode.RC100, countPurchasingContract);
    }

    @ApiOperation("通过条件获取采购合同")
    @GetMapping("/getContractsByConditions")
    public ResultData<PageInfo<PurchasingContract>> getContractsByConditions(@ApiParam("采购合同日期")@RequestParam(required = false)String date,
                                                                         @ApiParam("商品名称")@RequestParam(required = false)String nameCommodity,
                                                                         @ApiParam("型号")@RequestParam(required = false)String model,
                                                                         @ApiParam("供应商")@RequestParam(required = false)String supplier,
                                                                         @ApiParam("备注信息")@RequestParam(required = false)String noteInformation,
                                                                         @ApiParam("发票状态")@RequestParam(required = false)String invoiceState,
                                                                         @ApiParam("合同编号")@RequestParam(required = false)String contractID,

                                                                         @RequestParam(required = false) Integer pageNum,
                                                                         @RequestParam(required = false) Integer pageSize){
        PageInfo<PurchasingContract> contractsByConditions = purchasingContractService.getContractsByConditions(date, nameCommodity, model, supplier, noteInformation, invoiceState, contractID, pageNum, pageSize);
        return new ResultData<>(ReturnCode.RC100, contractsByConditions);
    }


//    /*设置文件上传的最大值*/
//    public static final int MAXSIZE = 10 * 1024 * 1024;
//    /*设置文件上传的类型*/
//    public static final List<String> AVATAR_TYPE = new ArrayList<>();
//
//    static {
//        AVATAR_TYPE.add("image/jpeg");
//        AVATAR_TYPE.add("image/png");
//        AVATAR_TYPE.add("image/bmp");
//        AVATAR_TYPE.add("image/gif");
//    }
//
//    @ApiOperation("新增采购合同(作废)")
//    @PostMapping("/insertpurchasingcontract")
//    public ResultData insertPurchasingContract(@ApiParam("采购合同实体") PurchasingContract purchasingContract,
//                                               HttpSession session,
//                                               @RequestParam("file") MultipartFile file) {
//        purchasingContractService.insertPurchasingContract(purchasingContract);
//        ResultData resultData = updateAttachment(session, file, purchasingContract.getContractID());
//        System.out.println("插入时上传文件" + resultData);
//        return new ResultData<>(ReturnCode.RC100, resultData);
//    }
//
//    @ApiOperation("上传采购合同附件(作废)")
//    @PostMapping("/updateattachment")
//    public ResultData updateAttachment(HttpSession session, @RequestParam("file") MultipartFile file, String contractID) {
//        if (file.isEmpty()) {
//            throw new FileEmptyException("文件为空");
//        }
//        if (file.getSize() > MAXSIZE) {
//            throw new FileSizeException("文件超出限制");
//        }
//        String contentType = file.getContentType();
//        if (!AVATAR_TYPE.contains(contentType)) {
//            throw new FileTypeException("文件类型不支持");
//        }
//        //上传的文件.../upload/文件.png
//        String parent = session.getServletContext().getRealPath("upload");
//        //file对象指向这个路径，file是否存在
//        File dir = new File(parent);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//        //获取到文件名称，UUID工具来生成一个新的字符串作为文件名
//        String originalFilename = file.getOriginalFilename();
//        int index = originalFilename.lastIndexOf(".");
//        String suffix = originalFilename.substring(index);
//        String filename = UUID.randomUUID().toString().toUpperCase(Locale.ROOT) + suffix;
//        File dest = new File(dir, filename);//创建一个空文件
//        try {
//            file.transferTo(dest);//将file文件中的数据写入到dest文件中
//        } catch (IOException e) {
//            throw new FileUploadIOException("文件读写异常");
//        } catch (FileStateException e) {
//            throw new FileStateException("文件状态异常");
//        }
//        String attachment = "/upload/" + filename;
//        purchasingContractService.updateAttachment(contractID, attachment);
//        //返回路径给前端，将来用于展示文件使用。
//        return new ResultData<>(ReturnCode.RC100, attachment);
//    }
}
