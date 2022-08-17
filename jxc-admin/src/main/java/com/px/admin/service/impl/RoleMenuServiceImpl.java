package com.px.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.px.admin.mapper.RoleMenuMapper;
import com.px.admin.pojo.RoleMenu;
import com.px.admin.service.RoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {
  @Override
  public List<Integer> queryExRoles(Integer roles) {
    return this.baseMapper.queryExRoles(roles);
  }

  @Override
  public List<String> findAuthoritiesByRoleName(List<String> roleName) {
      if (roleName.isEmpty()) {
          return roleName;
      }
      return this.baseMapper.findAuthoritiesByRoleName(roleName);
  }
}
