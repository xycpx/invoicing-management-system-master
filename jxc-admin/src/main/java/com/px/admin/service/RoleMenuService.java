package com.px.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.px.admin.pojo.RoleMenu;

import java.util.List;
/**
 * 角色菜单表服务类
 */
public interface RoleMenuService extends IService<RoleMenu> {
    public List<Integer> queryExRoles(Integer roleid);
    public List<String> findAuthoritiesByRoleName(List<String> roleName);

}
