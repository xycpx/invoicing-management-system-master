package com.px.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.px.admin.dto.TreeDto;
import com.px.admin.model.RespBean;
import com.px.admin.pojo.Menu;
import com.px.admin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 菜单控制器
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @RequestMapping("index")
    public String index(){
        return"/menu/menu";
    }
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> list(){
        return this.menuService.menuList();
    }
    @RequestMapping("queryAllMenus")
    @ResponseBody
    public List<TreeDto> queryAllMenu(Integer roleId){
        return this.menuService.queryAllMenu(roleId);
    }
    @RequestMapping("addMenuPage")
    public String addMenuPage(Integer grade,Integer pId, Model model){
        model.addAttribute("pId",pId);

        model.addAttribute("grade",grade);
        return "/menu/add";
    }
    @RequestMapping("save")
    @ResponseBody
    public RespBean save(Menu menu)
    {
     this.menuService.saveMenu(menu);
        return RespBean.success("保存成功");
    }
    @RequestMapping("delete")
    @ResponseBody
    public RespBean delete (Integer id){
        this.menuService.deleteMenu(id);
        return RespBean.success("删除成功");
    }
    @RequestMapping("updateMenuPage")
    public String updateMenuPage(Integer id,Model model){
        Menu temp = this.menuService.getOne(new QueryWrapper<Menu>().eq("id", id));
        model.addAttribute("menu",temp);
        return "/menu/update";
    }
    @RequestMapping("update")
    @ResponseBody
    public RespBean updeta(Menu menu){
        this.menuService.updateMenu(menu);
        return RespBean.success("修改成功");
    }
}
