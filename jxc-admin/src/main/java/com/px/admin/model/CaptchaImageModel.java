package com.px.admin.model;

import java.time.LocalDateTime;

/**
 * 返回公共对象,就是封装了验证码对象，设置了一个存活时间，用于判断是否超时
 */
public class CaptchaImageModel {

    private String code;

    private LocalDateTime expireTime;


    public CaptchaImageModel(String code, int expireAfterSeconds){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireAfterSeconds);
    }

    public String getCode() {
        return code;
    }

    /**
     * 验证码是否失效
     * @return
     */
    public boolean isExpired(){
        return  LocalDateTime.now().isAfter(expireTime);
    }
}
