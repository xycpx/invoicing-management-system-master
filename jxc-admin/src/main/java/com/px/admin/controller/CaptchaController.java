package com.px.admin.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.px.admin.model.CaptchaImageModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * 验证码控制器
 */
@RestController
public class CaptchaController {

    @Resource
    public DefaultKaptcha captchaProducer;

    @RequestMapping(value="/image",method = RequestMethod.GET)
    @ResponseBody
    public void kaptcha(HttpSession session, HttpServletResponse response) throws IOException {
        //设置响应头格式
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //验证码文字
        String capText = captchaProducer.createText();
        //将验证码存到session 利用model 设置消亡时间
        session.setAttribute("captcha_key", new CaptchaImageModel(capText,2 * 60));
        //将图片返回给前端
        ServletOutputStream out = response.getOutputStream();
            BufferedImage bufferedImage = captchaProducer.createImage(capText);
            //生成验证码图片
            ImageIO.write(bufferedImage,"jpg",out);
            out.flush();

        //因为没有catch异常处理器，所有该方法只能将产生的异常向外抛(高手)
    }
}
