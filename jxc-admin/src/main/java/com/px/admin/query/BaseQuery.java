package com.px.admin.query;


import lombok.Data;

/**
 * 分页基础格式
 */
@Data
public class BaseQuery {
    private Integer page=1;
    private Integer limit=10;

}
