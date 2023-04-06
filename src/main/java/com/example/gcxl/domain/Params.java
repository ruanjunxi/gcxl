package com.example.gcxl.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName: Param
 * @author: Eason
 * @data: 2022/4/24 14:03
 */
@Data
public class Params {
    private int id;
    private String paramName;
    private String creator;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;
    private String account;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date modifiedTime;
    private String paramDescription;

    public Params(String paramName, String creator, Date createDate, String account, Date modifiedTime, String paramDescription) {
        this.paramName = paramName;
        this.creator = creator;
        this.createDate = createDate;
        this.account = account;
        this.modifiedTime = modifiedTime;
        this.paramDescription = paramDescription;
    }

    public Params() {
    }

    public Params(int id, String paramName, String creator, Date createDate, String account, Date modifiedTime, String paramDescription) {
        this.id = id;
        this.paramName = paramName;
        this.creator = creator;
        this.createDate = createDate;
        this.account = account;
        this.modifiedTime = modifiedTime;
        this.paramDescription = paramDescription;
    }
}
