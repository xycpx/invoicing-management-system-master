package com.px.admin.query;

import lombok.Data;

/**
 * 封装了前端传回参数的一种格式
 */
@Data
public class RoleQuery extends BaseQuery{
    private String roleName;
}
