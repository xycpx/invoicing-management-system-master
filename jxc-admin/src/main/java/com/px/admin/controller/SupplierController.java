package com.px.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.px.admin.model.RespBean;
import com.px.admin.pojo.Supplier;
import com.px.admin.query.SupplierQuery;
import com.px.admin.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 供应商控制器
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;
    @RequestMapping("index" )
    public String index(){
        return "/supplier/supplier";
    }
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> list(SupplierQuery supplierQuery){
        return this.supplierService.supplierList(supplierQuery);
    }
    @RequestMapping("addOrUpdateSupplierPage")
    public String addOrUpdatePage(Integer id, Model model){
        if (id!=null){
            Supplier supplier = this.supplierService.getOne(new QueryWrapper<Supplier>().eq("id", id));
            model.addAttribute("supplier",supplier);
        }
        return "/supplier/add_update";
    }

    @RequestMapping("save")
    @ResponseBody
    public RespBean save(Supplier supplier){
        this.supplierService.saveSupplier(supplier);
        return RespBean.success("保存成功");
    }
    @RequestMapping("delete")
    @ResponseBody
    public RespBean delete(Integer[] ids){
        this.supplierService.deleteSupplier(ids);
        return RespBean.success("删除成功");
    }
    @RequestMapping("update")
    @ResponseBody
    public RespBean update(Supplier supplier){
        this.supplierService.updateSupplier(supplier);
        return RespBean.success("更新成功");
    }


    @RequestMapping("allGoodsSuppliers")
    @ResponseBody
    public List<Supplier> allGoodsSuppliers(){
        return supplierService.list(new QueryWrapper<Supplier>().eq("is_del",0));
    }

}
