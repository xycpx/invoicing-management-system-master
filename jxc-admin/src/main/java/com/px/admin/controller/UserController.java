package com.px.admin.controller;

import com.px.admin.model.RespBean;
import com.px.admin.pojo.User;
import com.px.admin.service.impl.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Map;

/**
 * User控制器
 */
@Controller
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserServiceimpl userServiceimpl;

    /**
     * 修改页面2.0
     * @param principal
     * @param model
     * @return
     */
    @RequestMapping("setting")
    public String setting(Principal principal, Model model) {
        String userName = principal.getName();
        User user = userServiceimpl.findForName(userName);
        model.addAttribute("user", user);
        return "user/setting";
    }

    /**
     * 修改信息
     *
     * @param user
     * @return
     */
    @RequestMapping("updateUserInfo")
    @ResponseBody
    public RespBean updateUserInfo(User user) {
        this.userServiceimpl.updateUserInfo(user);
        return RespBean.success("修改成功");
    }

    /**
     * 跳转页面
     * @return
     */
    @RequestMapping("password")
    public String password() {
        return "/user/password";
    }

    /**
     * 修改密码2.0
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param confirmPassword 新密码二次验证
     * @param principal 用户信息
     * @return
     */
    @RequestMapping("updateUserPassword")
    @ResponseBody
    public RespBean updateUserPassword(String oldPassword, String newPassword, String confirmPassword, Principal principal) {
        this.userServiceimpl.updateUserPassword(oldPassword, newPassword, confirmPassword, principal.getName());
        return RespBean.success("修改密码成功");
    }
    @RequestMapping("index")
    public String User(){
        return "/user/user";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> list(int page,int limit,String userName){
//        System.out.println(page);
//        System.out.println(limit);
        return userServiceimpl.queryExUser(page,limit,userName);
    }
    @RequestMapping("addOrUpdateUserPage")
    public String addOrUpdate(Integer id,Model model){
        System.out.println(id);
        if (id!=null){
            User user = userServiceimpl.queryById(id);
            model.addAttribute("user",user);
            System.out.println(user);
        }
        return "/user/add_update";
    }
    @RequestMapping("save")
    @ResponseBody
    public RespBean save(User user, String roleIds){
        userServiceimpl.saveUser(user, roleIds);
        return RespBean.success("保存成功");
    }
    @RequestMapping("update")
    @ResponseBody
    public RespBean update(User user,String roleIds){
        userServiceimpl.updateUsermassage(user,roleIds);
        return RespBean.success("修改成功");
    }
    @RequestMapping("delete")
    @ResponseBody
    public RespBean delete(Integer[] ids){
        userServiceimpl.deleteUser(ids);
        return RespBean.success("删除成功");

    }
//    /**
//     * 修改密码1.0
//     * @param oldPassword
//     * @param newPassword
//     * @param confirmPassword
//     * @param session
//     * @return
//     */
//    @RequestMapping("updateUserPassword")
//    @ResponseBody
//    public RespBean updateUserPassword(String oldPassword,String newPassword,String confirmPassword,HttpSession session){
//        this.userServiceimpl.updateUserPassword(oldPassword,newPassword,confirmPassword,session);
//        return RespBean.success("修改密码成功");
//    }
//
//    /**
//     * 登录页面1.0（已被安全框架替代）
//     * @param userName
//     * @param password
//     * @param session
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("login")
//    public RespBean login(String userName, String password, HttpSession session) {
//        System.out.println(password);
//        System.out.println(userName);
//        User user = userServiceimpl.login(userName, password);
//        session.setAttribute("user", user);
//        return RespBean.success("登录成功");
//    }
}
