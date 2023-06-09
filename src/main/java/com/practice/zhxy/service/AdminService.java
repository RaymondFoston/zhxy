package com.practice.zhxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.practice.zhxy.pojo.Admin;
import com.practice.zhxy.pojo.LoginForm;

public interface AdminService extends IService<Admin> {
    /**
     * 登录
     */
    Admin login(LoginForm loginForm);
}
