package com.px.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Main控制器
 */
@Controller
public class MainController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/main")
    public String main(){
        return "main";
    }
    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }




//    /**
//     * @version 退出1.0（被Security取代）
//     * @param session
//     * @return
//     */
//    @RequestMapping("signout")
//    public String signout(HttpSession session) {
//        session.removeAttribute("user");
//        return "redirect:/index";
//    }
}
