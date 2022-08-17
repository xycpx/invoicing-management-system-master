package com.px.admin.service;

import com.px.admin.pojo.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.px.admin.query.CustomerQuery;

import java.util.Map;

/**
 * 客户表服务
 */
public interface CustomerService extends IService<Customer> {

    Map<String, Object> customerList(CustomerQuery customerQuery);

    void saveCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(Integer[] ids);

    Customer findCustomerByName(String name);
}
