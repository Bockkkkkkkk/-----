package com.educationalsystembackend.controller.admin;

import ch.qos.logback.core.model.INamedModel;
import com.educationalsystembackend.pojo.Admin;
import com.educationalsystembackend.pojo.Course;
import com.educationalsystembackend.pojo.Student;
import com.educationalsystembackend.pojo.Teacher;
import com.educationalsystembackend.service.adminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName:aminController
 * Package:com.educationalsystembackend.controller.admin
 * Description:
 *
 * @Author: Bock
 * @Create:2023/12/1922:33
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class adminController {

    @Autowired
    private adminService adminService;

    @GetMapping("/login")
    public String login(String account,String password){
        log.info("管理员登录：{}",account);
        Boolean res=adminService.login(account,password);
        return res!=null && res? "Success":"Failure";
    }

    @PostMapping("/register")
    public String register(@RequestBody Admin admin){
        log.info("管理员注册：{}",admin);
        Boolean res=adminService.register(admin);
        return res!=null && res? "Success":"Failure";
    }
    @GetMapping("/courseList")
    public List<Course> getAllCourse(){
        log.info("管理员查询课程");
        return adminService.getAllCourse();
    }


    @PostMapping("/update")
    public String updateCourse(@RequestBody Course course){
        log.info("修改课程：{}",course);
        return adminService.updateCourse(course)? "Success":"Failure";
    }

}
