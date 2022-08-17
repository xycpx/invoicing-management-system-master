package com.px.admin.controller;

import com.px.admin.model.RespBean;
import com.px.admin.pojo.Role;
import com.px.admin.query.RoleQuery;
import com.px.admin.service.impl.RoleServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 角色控制器
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleServiceimpl roleServiceimpl;


    @RequestMapping("toAddGrantPage")
    public String AddGrantPage(Integer roleId,Model model){
        model.addAttribute("roleId",roleId);
        return "/role/grant";
    }


    /**
     * 角色管理主页
     * @return
     */
    @PreAuthorize("hasAnyAuthority('101003')")
    @RequestMapping("index")
    public String index(){
        return "/role/role";
    }

    /**
     * 角色列表查询
     * @param roleQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map list(RoleQuery roleQuery){
        return roleServiceimpl.roleList(roleQuery);
    }

    /**
     * 添加|更新角色
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("addOrUpdateRolePage")
    public String addOrUpdateRole(Integer id, Model model){
        if (id!=null){
            Role temp = this.roleServiceimpl.getById(id);
            model.addAttribute("role",temp);
        }
        return "/role/add_update";
    }


    @RequestMapping("save")
    @ResponseBody
    public RespBean save(Role role){
        roleServiceimpl.saveRole(role);
        return RespBean.success("保存成功");
    }
    @RequestMapping("update")
    @ResponseBody
    public RespBean update(Role role){
        roleServiceimpl.updateRole(role);
        return RespBean.success("修改成功");
    }
    @RequestMapping("delete")
    @ResponseBody
    public RespBean delete(Integer id){
        this.roleServiceimpl.deleteRole(id);
        return RespBean.success("删除成功");
    }

    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Map<String, Object>> queryAllRole(Integer userId){
    return this.roleServiceimpl.queryAllRoles(userId);
    }


    @RequestMapping("addGrant")
    @ResponseBody
    public RespBean addGrant(Integer[] mids,Integer roleId){
        this.roleServiceimpl.addRole(mids,roleId);
        return RespBean.success("授权成功");
    }
}
