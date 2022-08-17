package com.px.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.px.admin.mapper.UserMapper;
import com.px.admin.pojo.User;
import com.px.admin.pojo.UserRole;
import com.px.admin.service.UserRoleService;
import com.px.admin.service.UserService;
import com.px.admin.utils.AssertUtil;
import com.px.admin.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 用户表类
 */
@Service
public class UserServiceimpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public boolean updateUser(int id) {
        return false;
    }

    @Override
    public User login(String username, String password) {
        User user = findForName(username);
        AssertUtil.isTrue(user == null, "找不到该用户");
        AssertUtil.isTrue((!user.getUsername().equals(username)), "用户名不存在");
        AssertUtil.isTrue(!(user.getPassword().equals(password)), "密码不正确");
        return user;
    }
    @Override
    public User findForName(String username) {
        return userMapper.oneUser(username);
    }

    /**
     * 用户信息修改
     * @param user
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateUserInfo(User user) {
        AssertUtil.isTrue(StringUtil.isEmpty(user.getUsername()), "用户名为空");
        User temp = this.findForName(user.getUsername());
        //这条逻辑可以盘一盘，当前用户名已存在，如果id相同，代表是同一条数据，可以修改其属性，比如备注，备注名之类的
        //如果id不同，说明不是同一个用户，不可以修改其数据
        AssertUtil.isTrue(temp != null && (temp.getId() != user.getId()), "此用户名存在");
        AssertUtil.isTrue(!this.updateUser(user), "更新失败");
    }

    @Override
    public boolean updateUser(User user) {
        int i = userMapper.updateUserInfo(user);
        if (i >= 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User queryById(int id) {
        User user = this.userMapper.queryById(id);
        return user;
    }

    @Override
    public boolean updateUserPasswordById(int id, String newPassword) {
        int i = this.userMapper.updateUserPasswordById(id, newPassword);
        if (i >= 1)
            return true;
        else
            return false;
    }

    /**
     *  修改密码
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * @param userName
     */
    @Override
    public void updateUserPassword(String oldPassword, String newPassword, String confirmPassword, String userName) {
        AssertUtil.isTrue(StringUtil.isEmpty(oldPassword), "原密码为空");
        AssertUtil.isTrue(StringUtil.isEmpty(newPassword), "新密码为空");
        AssertUtil.isTrue(StringUtil.isEmpty(confirmPassword), "两次密码不一致");
        User user = this.findForName(userName);
        AssertUtil.isTrue(!(passwordEncoder.matches(oldPassword, user.getPassword())), "原密码不正确");
        AssertUtil.isTrue(passwordEncoder.matches(newPassword, user.getPassword()), "新密码与旧密码相同");
        AssertUtil.isTrue(!(newPassword.equals(confirmPassword)), "密码验证错误");
        AssertUtil.isTrue(!this.updateUserPasswordById(user.getId(), passwordEncoder.encode(newPassword)), "修改失败");
    }

    @Override
    public List<User> queryAllUser() {

        return this.userMapper.listuser();
    }

    @Override
    public Map<String, Object> queryExUser(int page, int limit, String userName) {
        PageHelper.startPage(page, limit);
        List<User> users = userMapper.queryExUser(userName);
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        long total = userPageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", total);
        map.put("data", users);
        return map;
    }

    @Override
    public boolean insert(User user) {
        int i = userMapper.insertUser(user);
        if (i >= 1)
            return true;
        else
            return false;

    }

    @Override
    @Transactional
    public boolean updateUserList(List<User> users) {
        try {
            for (User user : users) {
                this.userMapper.updateUserList(user);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 添加新用户       非空且不重复
     * 密码默认为123456
     * @param user
     * @param roleIds
     */
    @Override
    public void saveUser(User user, String roleIds) {
        AssertUtil.isTrue(StringUtil.isEmpty(user.getUsername()),"用户名为空");
        User temp = userMapper.oneUser(user.getUsername());
        AssertUtil.isTrue(temp!=null,"用户已存在");
        user.setIsDel(0);
        user.setPassword(passwordEncoder.encode("123456"));//加密存储
        boolean insert = this.insert(user);
        AssertUtil.isTrue(!insert,"插入失败");
        //
        User userid = this.findForName(user.getUsername());
        Integer id = userid.getId();
        this.relationUserRole(id,roleIds);
    }

    @Override
    public void updateUsermassage(User user,String roleIds) {
        AssertUtil.isTrue(StringUtil.isEmpty(user.getUsername()),"用户名为空");
        User temp = this.findForName(user.getUsername());
        //这条逻辑可以盘一盘，当前用户名已存在，如果id相同，代表是同一条数据，可以修改其属性，比如备注，备注名之类的
        //如果id不同，说明不是同一个用户，不可以修改其数据
        AssertUtil.isTrue(temp!=null&&(temp.getId()!=user.getId()),"此用户名存在");
        AssertUtil.isTrue(!this.updateUser(user),"更新失败");
        User userid = this.findForName(user.getUsername());
        Integer id = userid.getId();
        this.relationUserRole(id,roleIds);
    }

    @Override
    public boolean deleteUserList(List<User> users) {
        for (User user: users
             ) {
            
        }
        return false;
    }

    @Override
    public void deleteUser(Integer[] ids) {
        AssertUtil.isTrue(null==ids,"选择删除对象");
        List<User> users = new ArrayList<>();
        for (int i:ids
             ) {
            User user = queryById(i);
            user.setIsDel(1);
            System.out.println(user);
            users.add(user);
        }
       AssertUtil.isTrue(!this.updateUserList(users),"删除失败");
    }

    /**
     * 为用户分配角色
     * 核心表 t_user_role
     * @param userid
     * @param roleIds
     */
    @Override
    public void relationUserRole(Integer userid, String roleIds) {
        /**
         * 添加用户时：直接分配角色
         * 更新用户时：
         *      1.存在原始角色记录：删除原始角色记录再添加
         *      2.不存在原始角色记录：直接执行添加
         */
        int count = userRoleService.count(new QueryWrapper<UserRole>().eq("user_id",userid));
        if(count>0){
            AssertUtil.isTrue(!(userRoleService.remove(new QueryWrapper<UserRole>().eq("user_id",userid))),"用户角色分配失败!");
        }
        if(StringUtil.isNotEmpty(roleIds)){
            List<UserRole> userRoles = new ArrayList<>();

            for (String i:roleIds.split(",")) {
                UserRole userRole = new UserRole();
                userRole.setUserId(userid);
                userRole.setRoleId(Integer.parseInt(i));
                userRoles.add(userRole);
            }
            AssertUtil.isTrue(!this.userRoleService.saveBatch(userRoles),"添加角色失败");
        }

    }
}
