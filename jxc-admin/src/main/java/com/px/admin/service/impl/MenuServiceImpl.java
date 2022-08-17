package com.px.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.px.admin.dto.TreeDto;
import com.px.admin.mapper.MenuMapper;
import com.px.admin.pojo.Menu;
import com.px.admin.service.MenuService;
import com.px.admin.service.RoleMenuService;
import com.px.admin.utils.AssertUtil;
import com.px.admin.utils.PageResultUtil;
import com.px.admin.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<TreeDto> queryAllMenu(Integer roleId) {
        List<TreeDto> treeDtos = this.baseMapper.queryAllMenu();
        List<Integer> menuId = this.roleMenuService.queryExRoles(roleId);
        for (TreeDto td:treeDtos
             ) {
            if(menuId.contains(td.getId())){
                td.setChecked(true);
            }
        }
return treeDtos;
    }

    @Override
    public Map<String, Object> menuList() {

        List<Menu> menus = this.baseMapper.selectList(new QueryWrapper<Menu>().eq("is_del", 0));
      return  PageResultUtil.setResult((long)menus.size(),menus);

    }

    @Override
    public void saveMenu(Menu menu) {
        /**
         * 菜单名非空且唯一
         * 只有三级菜单
         */
        AssertUtil.isTrue(menu==null,"添加失败");
        AssertUtil.isTrue(null!=findMenuByNameAndGrade(menu.getName(),menu.getGrade()),"菜单名重复");
        Integer grade = menu.getGrade();
        AssertUtil.isTrue(null == grade || !(grade==0||grade==1||grade==2),"菜单层级不合法!");
        AssertUtil.isTrue(null!=this.findMenuByAclValue(menu.getAclValue()),"权限码重复");
        AssertUtil.isTrue(menu.getpId()==null,"上级目录有误");
        AssertUtil.isTrue(!this.save(menu),"添加失败");
    }

    @Override
    public Menu findMenuByNameAndGrade(String name, Integer grade) {
        return this.getOne(new QueryWrapper<Menu>().eq("grade",grade).eq("name",name));
    }

    @Override
    public Menu findMenuByAclValue(String aclValue) {
        return this.getOne( new QueryWrapper<Menu>().eq("acl_value",aclValue));
    }

    @Override
    public void deleteMenu(Integer id) {
        AssertUtil.isTrue(null==id,"选择删除对象");
        AssertUtil.isTrue(null!=findFather(id),"存在子目录");
        Menu temp = this.getOne(new QueryWrapper<Menu>().eq("id", id));
        temp.setIsDel(1);
        AssertUtil.isTrue(!this.updateById(temp),"删除失败");
    }

    @Override
    public List<Menu> findFather(Integer id) {
     return this.baseMapper.selectList(new QueryWrapper<Menu>().eq("p_id",id).eq("is_del",0));
    }

    @Override

    public void updateMenu(Menu menu) {
        /*
         这里有地方需要推敲  修改菜单层级是否正确
         一共就三层菜单
         如果父菜单降级 子菜单怎么办
         同级 违反树形
         一起降级 超过三层菜单

         */
        AssertUtil.isTrue(null == menu.getId(),"待更新的记录不存在!");
        AssertUtil.isTrue(StringUtil.isEmpty(menu.getName()),"菜单名不能为空!");
        Integer grade = menu.getGrade();
        AssertUtil.isTrue(null == grade || !(grade==0||grade==1||grade==2),"菜单层级不合法!");
        Menu temp = this.findMenuByNameAndGrade(menu.getName(),menu.getGrade());
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(menu.getId())) ,"该层级下菜单已存在!");
        temp = this.findMenuByAclValue(menu.getAclValue());
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(menu.getId())) ,"权限码已存在!");
        AssertUtil.isTrue(null == menu.getpId() ||
                null == this.findFather(menu.getpId()),"请指定上级菜单!");
        AssertUtil.isTrue(!(this.updateById(menu)),"菜单记录更新失败!");
    }
}

