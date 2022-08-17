package com.px.admin.service;

import com.px.admin.pojo.CustomerReturnList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.px.admin.pojo.CustomerReturnListGoods;
import com.px.admin.query.CustomerReturnListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户退货单表 服务类
 * </p>
 */
public interface CustomerReturnListService extends IService<CustomerReturnList> {

    String getNextCustomerReturnNumber();

    void saveCustomerReturnList(CustomerReturnList customerReturnList, List<CustomerReturnListGoods> slgList);

    Map<String, Object> customerReturnList(CustomerReturnListQuery customerReturnListQuery);

    void deleteCustomerReturn(Integer id);
}
