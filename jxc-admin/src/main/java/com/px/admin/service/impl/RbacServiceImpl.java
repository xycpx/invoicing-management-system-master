package com.px.admin.service.impl;

import com.px.admin.service.RbacService;
import com.px.admin.service.RoleMenuService;
import com.px.admin.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RbacServiceImpl implements RbacService {
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<String> findRoleByUserName(String userName) {
        return this.userRoleService.findRoleByUserName(userName);
    }

    @Override
    public List<String> findAuthoritiesByRoleName(List<String> roleName) {
       return this.roleMenuService.findAuthoritiesByRoleName(roleName);
    }
}
