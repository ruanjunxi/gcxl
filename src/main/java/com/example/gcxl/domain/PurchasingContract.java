package com.example.gcxl.domain;

import com.sun.org.apache.bcel.internal.generic.NEW;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author Eason
 * @ClassName:PurchasingContract
 * @data:2022/4/14 9:01
 */
@Data
@ApiModel("采购合同")
public class PurchasingContract implements Serializable {
    /*DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月和小时的格式为两个大写字母
java.util.Date date = new Date();//获得当前时间
String birthday = df.format(date);//将当前时间转换成特定格式的时间字符串，这样便可以插入到数据库中
*/
    @ApiModelProperty("合同编号")
    private String contractID;
    @ApiModelProperty("合同类型")
    private String contractType;
    @ApiModelProperty("合同日期")
    private String dateContract;
    @ApiModelProperty("供应商")
    private String supplier;
    @ApiModelProperty("合同金额")
    private double contractAmount;
    @ApiModelProperty("发票状态")
    private String invoiceState;
//    @ApiModelProperty("附件")
//    private String attachment;
    @ApiModelProperty("更新次数")
    private int updateFrequency;
    @ApiModelProperty("创建日期")
    private String creationDate;
    @ApiModelProperty("备注信息")
    private String noteInformation;
    @ApiModelProperty("创建者")
    private String account;
    @ApiModelProperty("附件个数")
    private int numberAttachments;


    public PurchasingContract(String contractID, String contractType, String dateContract, String supplier, double contractAmount, String invoiceState, int updateFrequency, String noteInformation, String account,int numberAttachments) {
        this.contractID = contractID;
        this.contractType = contractType;
        this.dateContract = dateContract;
        this.supplier = supplier;
        this.contractAmount = contractAmount;
        this.invoiceState = invoiceState;
        this.updateFrequency = updateFrequency;
        this.noteInformation = noteInformation;
        this.account = account;
        this.numberAttachments=numberAttachments;
    }

    public PurchasingContract(String contractID, String contractType, String dateContract, String supplier, double contractAmount, String invoiceState, int updateFrequency, String noteInformation, String account, String creationDate,int numberAttachments) {
        this.contractID = contractID;
        this.contractType = contractType;
        this.dateContract = dateContract;
        this.supplier = supplier;
        this.contractAmount = contractAmount;
        this.invoiceState = invoiceState;
        this.updateFrequency = updateFrequency;
        this.noteInformation = noteInformation;
        this.account = account;
        this.creationDate = creationDate;
        this.numberAttachments=numberAttachments;
    }

    public PurchasingContract() {
    }
}
