package com.example.gcxl.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName: PurchaseInvoiceHaveDetails
 * @author: Eason
 * @data: 2022/4/20 16:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseInvoiceHaveDetails {
    private PurchaseInvoice purchaseInvoice;
    private List<PurchaseInvoiceDetail> purchaseInvoiceDetailList;
}
