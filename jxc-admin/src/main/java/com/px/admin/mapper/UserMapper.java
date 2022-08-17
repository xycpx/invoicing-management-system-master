package com.px.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.px.admin.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author TianTian
 * @date
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
 public List<User> listuser();
 //通过id查询用户信息
 public User oneUser(@Param("username") String username);
 //通过id查询用户信息
 public User queryById(@Param("id") int id);
 //修改用户信息
 public int updateUserInfo(@Param("user") User user);
 //通过id修改用户密码
 public int updateUserPasswordById(@Param("id") int id,@Param("newPassword") String newPassword);
 public List<User> queryExUser(@Param("username") String userName);
 public int insertUser(@Param("user") User user);
 public int deleteUser(@Param("id") int id);
public int updateUserList(@Param("user") User user);
}
