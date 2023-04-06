package com.example.gcxl.domain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Eason
 * @ClassName:User
 * @data:2022/4/12 19:19
 */
@Data
@ApiModel("用户实体类")
public class User implements Serializable {
    @ApiModelProperty(value = "用户姓名")
    private String account;
    @ApiModelProperty(value = "用户密码")
    private String password;
    @ApiModelProperty("验证码")
    private String code;

    public User(String account, String password, String code) {
        this.account = account;
        this.password = password;
        this.code = code;
    }

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public User() {
    }
}

