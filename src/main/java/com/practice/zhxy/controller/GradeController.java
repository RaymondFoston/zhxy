package com.practice.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.practice.zhxy.pojo.Grade;
import com.practice.zhxy.service.GradeService;
import com.practice.zhxy.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "年级控制器")
@RestController
@RequestMapping("/sms/gradeController")
public class GradeController {

    @Autowired
    private GradeService gradeService;


    @Operation(summary ="查询年级信息,分页带条件")
    @GetMapping("/getGrades/{pageNo}/{pageSize}")
    public Result getGradeByOpr(
            @Parameter(name="分页查询页码数") @PathVariable(value = "pageNo") Integer pageNo, // 页码数
            @Parameter(name="分页查询页大小") @PathVariable(value = "pageSize")Integer pageSize, // 页大小
            @Parameter(name="分页查询模糊匹配班级名") String gradeName)// 模糊查询条件
    {
        // 设置分页信息
        Page<Grade> page =new Page<>(pageNo,pageSize);
        // 调用服务层方法,传入分页信息,和查询的条件
        IPage<Grade> pageRs = gradeService.getGradeByOpr(page, gradeName);
        return Result.ok(pageRs);
    }

    @Operation(summary ="添加或者修改年级信息")
    @PostMapping("/saveOrUpdateGrade")
    public Result saveOrUpdateGrade(
            @Parameter(name="JSON的grade对象转换后台数据模型")@RequestBody Grade grade
    ){
        // 调用服务层方法,实现添加或者修改年级信息
        gradeService.saveOrUpdate(grade);
        return Result.ok();
    }

    @Operation(summary ="删除一个或者多个grade信息")
    @DeleteMapping("/deleteGrade")
    public Result deleteGradeById(
            @Parameter(name="JSON的年级id集合,映射为后台List<Integer>")@RequestBody List<Integer> ids
    )
    {
        gradeService.removeByIds(ids);
        return Result.ok();
    }

    @Operation(summary ="获取所有Grade信息")
    @GetMapping("/getGrades")
    public Result getGrades(){
        List<Grade> grades = gradeService.getGrades();
        return Result.ok(grades);
    }

}
