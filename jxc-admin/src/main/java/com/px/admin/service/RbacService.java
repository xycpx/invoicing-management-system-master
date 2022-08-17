package com.px.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
/**
 * 权限验证服务类
 */
public interface RbacService{
    /**
     * 根据登录用户名查角色
     * @param userName
     * @return
     */
    public List<String> findRoleByUserName(String userName);

    /**
     * 根据登录用户名查权限
     * @param roleName
     * @return
     */
    public List<String> findAuthoritiesByRoleName(List<String> roleName);}
