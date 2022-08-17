package com.px.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.px.admin.pojo.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends BaseMapper<Role> {
    List<Map<String, Object>> queryAllRoles(Integer userId);

}
