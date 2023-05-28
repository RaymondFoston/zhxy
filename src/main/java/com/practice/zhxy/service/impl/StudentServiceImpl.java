package com.practice.zhxy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.practice.zhxy.mapper.StudentMapper;
import com.practice.zhxy.pojo.LoginForm;
import com.practice.zhxy.pojo.Student;
import com.practice.zhxy.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("studentService")
@Transactional
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Override
    public Student login(LoginForm loginForm) {
        return null;
    }
}
