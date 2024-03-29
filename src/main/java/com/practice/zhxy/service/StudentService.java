package com.practice.zhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.practice.zhxy.pojo.LoginForm;
import com.practice.zhxy.pojo.Student;

public interface StudentService extends IService<Student> {
    /**
     * 登录
     */
    Student login(LoginForm loginForm);

    Student getStudentById(int intValue);

    IPage<Student> getStudentByOpr(Page<Student> page, Student student);
}
