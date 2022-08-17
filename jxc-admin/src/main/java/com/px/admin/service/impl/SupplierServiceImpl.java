package com.px.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.px.admin.mapper.SupplierMapper;
import com.px.admin.pojo.Supplier;
import com.px.admin.query.SupplierQuery;
import com.px.admin.service.SupplierService;
import com.px.admin.utils.AssertUtil;
import com.px.admin.utils.PageResultUtil;
import com.px.admin.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 供应商服务类
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements SupplierService {

    @Override
    public Map<String, Object> supplierList(SupplierQuery supplierQuery) {
        IPage<Supplier> page =new Page<Supplier>(supplierQuery.getPage(),supplierQuery.getLimit());
        QueryWrapper<Supplier> queryWrapper =new QueryWrapper<Supplier>();
        queryWrapper.eq("is_del",0);
        if(StringUtil.isNotEmpty(supplierQuery.getSupplierName())){
            queryWrapper.like("name",supplierQuery.getSupplierName());
        }
        page =  this.baseMapper.selectPage(page,queryWrapper);
        return PageResultUtil.setResult(page.getTotal(),page.getRecords());
    }

    @Override
    public Supplier findSupplierByName(String name) {
        return this.getOne(new QueryWrapper<Supplier>().eq("is_del",0).eq("name",name));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveSupplier(Supplier supplier) {
        /**
         * 供应商名称  联系人 联系电话   非空
         * 名称不可重复
         * isDel 0
         */
        checkParams(supplier.getName(),supplier.getContact(),supplier.getNumber());
        AssertUtil.isTrue(null !=this.findSupplierByName(supplier.getName()),"供应商已存在!");
        supplier.setIsDel(0);
        AssertUtil.isTrue(!(this.save(supplier)),"记录添加失败!");

    }



    private void checkParams(String name, String contact, String number) {
        AssertUtil.isTrue(StringUtil.isEmpty(name),"请输入供应商名称!");
        AssertUtil.isTrue(StringUtil.isEmpty(contact),"请输入联系人!");
        AssertUtil.isTrue(StringUtil.isEmpty(number),"请输入联系电话!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateSupplier(Supplier supplier) {
        AssertUtil.isTrue(null == this.getById(supplier.getId()),"请选择供应商记录!");
        checkParams(supplier.getName(),supplier.getContact(),supplier.getNumber());
        Supplier temp = this.findSupplierByName(supplier.getName());
        AssertUtil.isTrue(null !=temp && !(temp.getId().equals(supplier.getId())),"供应商已存在!");
        AssertUtil.isTrue(!(this.updateById(supplier)),"记录更新失败!");
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteSupplier(Integer[] ids) {
        AssertUtil.isTrue(null == ids || ids.length==0,"请选择待删除记录id");
        List<Supplier> supplierList =new ArrayList<Supplier>();
        for (Integer id : ids) {
            Supplier temp =this.getById(id);
            temp.setIsDel(1);
            supplierList.add(temp);
        }
        AssertUtil.isTrue(!(this.updateBatchById(supplierList)),"记录删除失败!");

    }
}
