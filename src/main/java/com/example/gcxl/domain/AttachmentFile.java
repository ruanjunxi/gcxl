package com.example.gcxl.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
* 
* @TableName file
*/
@Data

public class AttachmentFile implements Serializable {


    @ApiModelProperty("id")
    private Integer id;
    /**
    * 文件名
    */
    @ApiModelProperty("文件名")
    private String fileName;
    /**
    * 上传时间
    */
    @ApiModelProperty("上传时间")
    private Date uploadTime;
    /**
    * 合同编号、文件编号
    */
    @ApiModelProperty("合同编号、文件编号")
    private String typeID;
    /**
    * 文件路径
    */
    @ApiModelProperty("文件路径")
    private String filePath;

    public AttachmentFile() {
    }

    public AttachmentFile(String fileName, String typeID, String filePath) {
        this.fileName = fileName;
        this.typeID = typeID;
        this.filePath = filePath;
    }
}
