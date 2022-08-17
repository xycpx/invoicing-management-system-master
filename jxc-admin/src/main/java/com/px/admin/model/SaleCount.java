package com.px.admin.model;

import lombok.Data;


/**
 * 销售金额model
 */
@Data
public class SaleCount {
    private float amountCost; // 成本总金额

    private float amountSale; // 销售总金额

    private float amountProfit; // 销售利润

    private String date; // 日期
}
