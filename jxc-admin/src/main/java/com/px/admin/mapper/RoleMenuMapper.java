package com.px.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.px.admin.pojo.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    List<Integer> queryExRoles(@Param("roleId") Integer roleId);

    public List<String> findAuthoritiesByRoleName(@Param("rolename")List<String>roleName);
}
