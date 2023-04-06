package com.example.gcxl.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

/**
* 
* @TableName logontolog
*/
@Data
public class Logontolog implements Serializable {

    public Logontolog(@NotBlank(message = "[用户名]不能为空") @Size(max = 255, message = "编码长度不能超过255") @Length(max = 255, message = "编码长度不能超过255") String account, @NotBlank(message = "[IP地址]不能为空") @Size(max = 255, message = "编码长度不能超过255") @Length(max = 255, message = "编码长度不能超过255") String ip) {
        this.account = account;
        this.ip = ip;
    }

    public Logontolog(@NotBlank(message = "[用户名]不能为空") @Size(max = 255, message = "编码长度不能超过255") @Length(max = 255, message = "编码长度不能超过255") String account, @NotBlank(message = "[IP地址]不能为空") @Size(max = 255, message = "编码长度不能超过255") @Length(max = 255, message = "编码长度不能超过255") String ip, @NotNull(message = "[登录时间]不能为空") Date time) {
        this.account = account;
        this.ip = ip;
        this.time = time;
    }

    public Logontolog() {
    }

    /**
    * 用户名
    */
    @ApiModelProperty("用户名")
    private String account;
    /**
    * IP地址
    */
    @ApiModelProperty("IP地址")
    private String ip;
    /**
    * 登录时间
    */
    @ApiModelProperty("登录时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date time;


}
