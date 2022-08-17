package com.px.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.px.admin.mapper.RoleMapper;
import com.px.admin.pojo.Role;
import com.px.admin.pojo.RoleMenu;
import com.px.admin.query.RoleQuery;
import com.px.admin.service.RoleMenuService;
import com.px.admin.service.RoleService;
import com.px.admin.utils.AssertUtil;
import com.px.admin.utils.PageResultUtil;
import com.px.admin.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceimpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMenuService roleMenuService;

    public Map<String, Object> roleList(RoleQuery roleQuery) {
        IPage<Role> page = new Page<Role>(roleQuery.getPage(),roleQuery.getLimit());
        QueryWrapper<Role> queryWrapper =new QueryWrapper<Role>();
        queryWrapper.eq("is_del",0);
        if(StringUtil.isNotEmpty(roleQuery.getRoleName())){
            queryWrapper.like("name",roleQuery.getRoleName());
        }
        page = this.baseMapper.selectPage(page,queryWrapper);
        return PageResultUtil.setResult(page.getTotal(),page.getRecords());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveRole(Role role) {
        AssertUtil.isTrue(StringUtil.isEmpty(role.getName()),"用户名为空");
        AssertUtil.isTrue(null!=this.findRoleByRoleName(role.getName()),"用户已存在");
//        role.setIsDel(1);
        AssertUtil.isTrue(!this.save(role),"添加失败");

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateRole(Role role) {
        AssertUtil.isTrue(StringUtil.isEmpty(role.getName()),"用户名为空");
        Role temp = this.findRoleByRoleName(role.getName());
        AssertUtil.isTrue(temp!=null&&!(temp.getId().equals(role.getId())),"用户名存在");
        AssertUtil.isTrue(!this.updateById(role),"修改失败");
    }
    @Override
    public Role findRoleByRoleName(String roleName) {
        return this.baseMapper.selectOne(new QueryWrapper<Role>().eq("is_del",0).eq("name",roleName));
    }

    @Override
    public void deleteRole(Integer id) {
        AssertUtil.isTrue(null==id,"删除失败");
        Role temp = this.getById(id);
        temp.setIsDel(1);
       AssertUtil.isTrue(!this.updateById(temp),"删除失败");

    }

    @Override
    public List<Map<String, Object>> queryAllRoles(Integer userId) {
        return this.baseMapper.queryAllRoles(userId);
    }

    @Override
    public void addRole(Integer[] mids, Integer roleId) {
        AssertUtil.isTrue(roleId==null,"用户为空");
        QueryWrapper<RoleMenu> roleMenuQueryWrapper = new QueryWrapper<>();
        int count = roleMenuService.count(roleMenuQueryWrapper.eq("role_id", roleId));
         if (count>0){
           AssertUtil.isTrue( !this.roleMenuService.remove(roleMenuQueryWrapper.eq("role_id",roleId)),"授权失败");
         }
         if(mids!=null){
             ArrayList<RoleMenu> roleMenus = new ArrayList<>();
             for (Integer i:mids
                  ) {
                 RoleMenu roleMenu = new RoleMenu();
                 roleMenu.setRoleId(roleId);
                 roleMenu.setMenuId(i);
                 roleMenus.add(roleMenu);
             }
             AssertUtil.isTrue(!this.roleMenuService.saveBatch(roleMenus),"授权失败");
         }

    }

}
