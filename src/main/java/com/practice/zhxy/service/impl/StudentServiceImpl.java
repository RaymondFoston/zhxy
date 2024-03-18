package com.practice.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.practice.zhxy.mapper.StudentMapper;
import com.practice.zhxy.pojo.Admin;
import com.practice.zhxy.pojo.LoginForm;
import com.practice.zhxy.pojo.Student;
import com.practice.zhxy.pojo.Teacher;
import com.practice.zhxy.service.StudentService;
import com.practice.zhxy.util.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("studentService")
@Transactional
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    /**
     * 学生登录方法
     * @return
     */
    public Student login(LoginForm loginForm){
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",loginForm.getUsername());
        queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));
        Student student = baseMapper.selectOne(queryWrapper);
        return student;
    }

    @Override
    public Student getStudentById(int userId) {
        QueryWrapper<Student> queryWrapper =  new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * 按条件查询学生信息【带分页】
     */
    public IPage<Student> getStudentByOpr(Page<Student> pageParam,Student student){
        QueryWrapper<Student> queryWrapper = null;
        if(student != null) {
            queryWrapper = new QueryWrapper<>();
            if (student.getClazzName() != null) {
                queryWrapper.eq("clazz_name", student.getClazzName());
            }
            if (student.getName() != null) {
                queryWrapper.like("name", student.getName());
            }
            queryWrapper.orderByDesc("id");
            queryWrapper.orderByAsc("name");
        }
        //创建分页对象
        IPage<Student> pages = baseMapper.selectPage(pageParam, queryWrapper);

        return pages;
    }
}
