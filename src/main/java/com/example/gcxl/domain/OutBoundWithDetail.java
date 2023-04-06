package com.example.gcxl.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName: OutBoundWithDetail
 * @author: Eason
 * @data: 2022/4/23 16:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutBoundWithDetail {
    @ApiModelProperty("类型")
    private String type;
    @ApiModelProperty("出库数量")
    private int outboundNumber;
    @ApiModelProperty("出库id")
    private String outboundID;
    @ApiModelProperty("创建者")
    private String account;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    private Date creationDate;
}
