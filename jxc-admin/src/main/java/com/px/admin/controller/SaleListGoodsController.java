package com.px.admin.controller;


import com.px.admin.query.saleListGoodsQuery;

import com.px.admin.service.SaleListGoodsService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 销售单表控制器
 */
@Controller
@RequestMapping("/saleListGoods")
public class SaleListGoodsController {

    @Resource
    private SaleListGoodsService saleListGoodsService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> saleListGoodsList(saleListGoodsQuery saleListGoodsQuery){
        return saleListGoodsService.saleListGoodsList(saleListGoodsQuery);
    }


}
