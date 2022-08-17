package com.px.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.px.admin.mapper.UserRoleMapper;
import com.px.admin.pojo.UserRole;
import com.px.admin.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 用户角色表服务类
 */
@Service
public class userRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    @Override
    public List<String> findRoleByUserName(String userName) {
       return this.baseMapper.findRoleByUserName(userName);
    }
}
