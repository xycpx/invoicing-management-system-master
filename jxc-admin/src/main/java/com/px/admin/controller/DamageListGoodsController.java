package com.px.admin.controller;


import com.px.admin.query.DamageListGoodsQuery;
import com.px.admin.service.DamageListGoodsService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 报损单商品表控制器
 */
@Controller
@RequestMapping("/damageListGoods")
public class DamageListGoodsController {


    @Resource
    private DamageListGoodsService damageListGoodsService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> damageListGoodsList(DamageListGoodsQuery damageListGoodsQuery){
        return damageListGoodsService.damageListGoodsList(damageListGoodsQuery);
    }



}
