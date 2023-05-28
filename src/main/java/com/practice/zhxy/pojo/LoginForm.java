package com.practice.zhxy.pojo;

import lombok.Data;

/**
 * @project: ssm_sms
 * @description: 用户登录表单信息
 */
@Data
public class LoginForm {
    private Integer id;
    private String username;
    private String password;
    private String verifiCode;
    private Integer userType;

}
