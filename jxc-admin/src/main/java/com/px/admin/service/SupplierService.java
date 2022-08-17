package com.px.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.px.admin.pojo.Supplier;
import com.px.admin.query.SupplierQuery;

import java.util.Map;

/**
 * 供应商服务类
 */
public interface SupplierService extends IService<Supplier> {
    public Map<String, Object> supplierList(SupplierQuery supplierQuery);
    public Supplier findSupplierByName(String name);

    public void saveSupplier(Supplier supplier);

    public void updateSupplier(Supplier supplier);

    public void deleteSupplier(Integer[] ids);


    }
