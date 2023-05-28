package com.practice.zhxy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.practice.zhxy.mapper.TeacherMapper;
import com.practice.zhxy.pojo.LoginForm;
import com.practice.zhxy.pojo.Teacher;
import com.practice.zhxy.service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("teacherService")
@Transactional
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Override
    public Teacher login(LoginForm loginForm) {
        return null;
    }
}
