package com.px.admin.interceptors;

///**
// * 拦截器1.0（已被Security取代）
// * @author TianTian
// * @date 2022/1/7 15:29
// */
//public class NoLoginInterceptor  implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        User user= (User) request.getSession().getAttribute("user");
//
//        if(null == user){
//            /**
//             * 用户未登录 或者 session 过期
//             */
//            response.sendRedirect("index");
//            return false;
//        }
//        return true;
//    }
//}
