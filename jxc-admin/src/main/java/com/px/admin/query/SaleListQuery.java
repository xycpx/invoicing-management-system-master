package com.px.admin.query;

import lombok.Data;

import java.util.List;

/**
 *
 */
@Data
public class SaleListQuery extends BaseQuery{

    private String saleNumber;
    private Integer customerId;
    private Integer state;



    private String startDate;
    private String endDate;
    private String goodsName;
    private Integer typeId;
    private List<Integer> typeIds;

    public Integer index;
}
