package com.px.admin.query;

import lombok.Data;

/**
 *
 */
@Data
public class CustomerReturnListQuery extends BaseQuery{
    // 退货单号
    private String customerReturnNumber;

    // 客户
    private Integer customerId;

    // 是否付款
    private Integer state;

    // 开始时间
    private String startDate;

    // 结束时间
    private String endDate;


}
