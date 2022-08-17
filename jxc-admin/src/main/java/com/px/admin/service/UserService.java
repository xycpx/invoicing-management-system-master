package com.px.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.px.admin.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * 角色表服务类
 */
public interface UserService extends IService<User> {
    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password);

    /**
     * 通过用户名查询
     * @param username
     * @return
     */
    public  User findForName(String username);

    /**
     * 修改用户信息
     * @param user
     */
    public void updateUserInfo(User user);

    /**
     * 对Dao进行操作
     * @param user
     * @return
     */
    public boolean updateUser(User user);
    public User queryById(int id);
    public boolean updateUserPasswordById(int id,String newPassword);
    public void updateUserPassword(String oldPassword, String newPassword, String confirmPassword,String username);
    public List<User> queryAllUser();
    public Map<String,Object> queryExUser(int page,int limit,String userName);
    public boolean insert(User user);
    public boolean updateUser(int id);
    public void saveUser(User user, String roleIds);
    public void updateUsermassage(User user,String roleIds);
    public boolean deleteUserList(List<User> users);
    void deleteUser(Integer[] ids);
    public boolean updateUserList(List<User> users);
    public void relationUserRole(Integer userid,String roleIds);
}
