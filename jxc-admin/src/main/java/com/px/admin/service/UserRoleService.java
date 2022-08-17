package com.px.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.px.admin.pojo.UserRole;

import java.util.List;

/**
 * 用户角色表服务类
 */
public interface UserRoleService extends IService<UserRole> {
    public List<String> findRoleByUserName(String userName);
}
