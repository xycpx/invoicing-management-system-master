package com.px.admin.query;

import lombok.Data;

/**
 * 经销商的查询参数
 */
@Data
public class SupplierQuery extends BaseQuery{
    private String supplierName;
}
