package com.px.admin.config;

//import com.lzj.admin.interceptors.NoLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
/**
 * @version 拦截器配置1.0（已被Security取代）
 */
//    @Bean
//    public NoLoginInterceptor noLoginInterceptor(){
//        return new NoLoginInterceptor();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(noLoginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/index","/user/login",
//                        "/css/**","/error/**","/images/**","/js/**","/lib/**");
//    }
}
