package com.practice.zhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.practice.zhxy.pojo.LoginForm;
import com.practice.zhxy.pojo.Teacher;

public interface TeacherService extends IService<Teacher> {
    /**
     * 登录
     */
    Teacher login(LoginForm loginForm);

    Teacher getTeacherById(int intValue);

    IPage<Teacher> getTeachersByOpr(Page<Teacher> pageParam, Teacher teacher);
}
