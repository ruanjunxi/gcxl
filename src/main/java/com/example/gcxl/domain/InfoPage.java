package com.example.gcxl.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoPage {
    @NotNull(message = "页码不能为空")
    Integer pageNum;
    @NotNull(message = "页数不能为空")
    Integer pageSize;
}
