package com.px.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.px.admin.pojo.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRole> {
    public List<String> findRoleByUserName(@Param("username") String username);
}
