package com.px.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.px.admin.dto.TreeDto;
import com.px.admin.pojo.Menu;

import java.util.List;
import java.util.Map;
/**
 * 菜单表服务类
 */
public interface MenuService extends IService<Menu> {
    public List<TreeDto> queryAllMenu(Integer roleId);
    public Map<String,Object> menuList();
    public void saveMenu(Menu menu);
    public Menu findMenuByNameAndGrade(String name, Integer grade);
    public Menu findMenuByAclValue(String aclValue);
    public void deleteMenu(Integer id);
    public List findFather(Integer id);
    public void updateMenu(Menu menu);
}
