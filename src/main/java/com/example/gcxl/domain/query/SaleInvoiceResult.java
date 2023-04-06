package com.example.gcxl.domain.query;

import com.example.gcxl.domain.SaleContract;
import com.example.gcxl.domain.SaleInvoice;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("返回结果")
public class SaleInvoiceResult {
    PageInfo<SaleInvoice> saleInvoiceList;
//    List<SaleInvoice> saleInvoiceList;
    Integer counts;
    Float amount;
}
