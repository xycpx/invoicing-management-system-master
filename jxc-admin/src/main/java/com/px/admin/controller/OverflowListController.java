package com.px.admin.controller;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.px.admin.model.RespBean;
import com.px.admin.pojo.OverflowList;
import com.px.admin.pojo.OverflowListGoods;
import com.px.admin.query.OverFlowListQuery;
import com.px.admin.service.OverflowListService;
import com.px.admin.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 *
 *
 */
@Controller
@RequestMapping("/overflow")
public class OverflowListController {

    @Resource
    private OverflowListService overflowListService;

    @Resource
    private UserService userService;

    /**
     * 商品报溢主页
     * @return
     */
    @RequestMapping("index")
    public String index(Model model){
        model.addAttribute("overflowNumber",overflowListService.getOverflowNumber());
        return "overflow/overflow";
    }


    @RequestMapping("save")
    @ResponseBody
    public RespBean save(OverflowList overflowList, String goodsJson, Principal principal){
        String userName = principal.getName();
        overflowList.setUserId(userService.findForName(userName).getId());
        Gson gson = new Gson();
        List<OverflowListGoods> plgList = gson.fromJson(goodsJson,new TypeToken<List<OverflowListGoods>>(){}.getType());
        overflowListService.saveOverflowList(overflowList,plgList);
        return RespBean.success("商品报溢入库成功!");
    }


    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> overFlowList(OverFlowListQuery overFlowListQuery){
        return overflowListService.overFlowList(overFlowListQuery);
    }
    @RequestMapping("delete")
    @ResponseBody
    public RespBean delete(Integer id){
        overflowListService.deleteoverflowList(id);
        return RespBean.success("删除成功");
    }



}
