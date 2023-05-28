package com.practice.zhxy.controller;

import com.practice.zhxy.pojo.Admin;
import com.practice.zhxy.pojo.LoginForm;
import com.practice.zhxy.pojo.Student;
import com.practice.zhxy.pojo.Teacher;
import com.practice.zhxy.service.AdminService;
import com.practice.zhxy.service.StudentService;
import com.practice.zhxy.service.TeacherService;
import com.practice.zhxy.util.CreateVerifiCodeImage;
import com.practice.zhxy.util.JwtHelper;
import com.practice.zhxy.util.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sms/system")
public class SystemController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    // @Api(tags = "系统控制器")
    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm, HttpServletRequest request) {
        /**1.验证码校验**/
        //获取用户提交的验证码和session域中验证码
        HttpSession session = request.getSession();
        String systemVerifiCode = (String) session.getAttribute("verifiCode");
        String loginVerifiCode = loginForm.getVerifiCode();
        if ("".equals(systemVerifiCode)) {
            //session过期，验证码超时
            return Result.fail().message("验证码失误，请刷新后重试");
        }
        if (loginVerifiCode.equalsIgnoreCase(systemVerifiCode)) {
            //验证码有误
            return Result.fail().
                    message("验证码有误，请刷新后重新输入");
        }

        /**2.用户登录信息校验**/
        Map<String, Object> map = new HashMap<>();
        //调用服务层登录方法，根据用户提交的LoginInfo信息，查询对应的Admin对象，找不到返回Null
        int userType = loginForm.getUserType();
        try {
            switch (userType) {
                case 1:
                    Admin login = adminService.login(loginForm);
                    if (login != null) {
                        //登录成功，将用户id和用户类型转换为token口令，作为消息响应给前端
                        long userId = login.getId().longValue();
                        map.put("token", JwtHelper.createToken(userId, userType));
                    } else {
                        throw new RuntimeException("用户名或者密码有误！");
                    }
                    login = null;
                    break;
                case 2:
                    Student login1 = studentService.login(loginForm);
                    break;
                case 3:
                    Teacher login2 = teacherService.login(loginForm);
                    break;
                default:

            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            //捕获异常，向用户响应错误信息
            return Result.fail().message(e.getMessage());
        }
        return Result.fail().message("查无此用户");
    }


    @ApiOperation("获取验证码图片")
    @GetMapping("/getVerifiCodeImage")
    public void getVerificodeImage(HttpServletRequest request, HttpServletResponse response) {
        //获取图片
        BufferedImage verifiCodeImage = CreateVerifiCodeImage.getVerifiCodeImage();
        //获取图片上的验证码
        String verifiCode = String.valueOf(CreateVerifiCodeImage.getVerifiCode());
        //蒋验证码文本放入session域，为下一次验证做准备
        HttpSession session = request.getSession();
        session.setAttribute("verifiCode", verifiCode);
        //将验证码图片响应给浏览器
        try {
            ImageIO.write(verifiCodeImage, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
