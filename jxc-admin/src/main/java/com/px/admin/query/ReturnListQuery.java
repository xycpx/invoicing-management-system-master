package com.px.admin.query;

import lombok.Data;

/**
 *
 */
@Data
public class ReturnListQuery extends BaseQuery{

    private String returnNumber;
    private Integer supplierId;
    private Integer state;
    private String startDate;
    private String endDate;
}
