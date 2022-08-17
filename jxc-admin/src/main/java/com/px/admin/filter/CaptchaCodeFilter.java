package com.px.admin.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.px.admin.model.CaptchaImageModel;
import com.px.admin.model.RespBean;
import com.px.admin.utils.StringUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * 验证码过滤器 用于验证验证码是否有误
 * Security的过滤器 基于AOP的原理 在登录验证前切入验证码验证
 */
@Component
public class CaptchaCodeFilter extends OncePerRequestFilter {
    //利用这个对象向前端传输对象
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 过器实现
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            //判断请求地址是否为login
        if (StringUtil.equals(request.getRequestURI(),"/login")){
            try {
                this.validata(new ServletWebRequest(request));
            } catch (AuthenticationException e) {
                response.setContentType("application/json;charset=UTF-8");
               response.getWriter().write((objectMapper.writeValueAsString(RespBean.error("验证码错误"))));
               return;//这里非常关键 如果不返回的话会继续执行登录验证 导致前端会收到两个Json对象
            }
        }

        filterChain.doFilter(request,response);
    }

    /**
     * 验证方法实现
     * @param request
     * @throws ServletRequestBindingException
     */
    private void validata(ServletWebRequest request) throws ServletRequestBindingException {
        HttpSession session = request.getRequest().getSession();
        //获取请求中参数值（用户输入的验证码）
        String captchaCode = ServletRequestUtils.getStringParameter(request.getRequest(), "captchaCode");
        //验证前端字符串是否为空
        if(StringUtil.isEmpty(captchaCode)){
            throw new SessionAuthenticationException("验证码为空");
        }
        //获取Session中存放的验证码包装类
        CaptchaImageModel captcha_key = (CaptchaImageModel) session.getAttribute("captcha_key");
        //获取验证码图片中字符串
        String code = captcha_key.getCode();
        if(Objects.isNull(captcha_key)){
            throw new SessionAuthenticationException("验证码不存在");
        }
        if (captcha_key.isExpired())
        {
            throw new SessionAuthenticationException("验证码超时");
        }

        if (!StringUtil.equals(captchaCode,code)){
            throw new SessionAuthenticationException("验证码不正确");
        }
    }
}
