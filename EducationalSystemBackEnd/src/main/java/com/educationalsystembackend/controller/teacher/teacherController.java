package com.educationalsystembackend.controller.teacher;

import com.educationalsystembackend.pojo.Course;
import com.educationalsystembackend.pojo.Student;
import com.educationalsystembackend.pojo.Teacher;
import com.educationalsystembackend.service.adminService;
import com.educationalsystembackend.service.impl.teacherServiceImpl;
import com.educationalsystembackend.service.studentService;
import com.educationalsystembackend.service.teacherService;
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
@RequestMapping("/teacher")
@Slf4j
public class teacherController {
    @Autowired
    private teacherService teacherService;
    @Autowired
    private studentService studentService;

    @GetMapping
    public Teacher getTeacherByCourseId(Integer id){
        log.info("根据课程id查询任教老师，id：{}",id);
        return teacherService.getTeacherByCourseId(id);
    }
    @GetMapping("/account")
    public Teacher getTeacherByAccount(String account){
        log.info("根据账户查询任教老师，账户：{}",account);
        return teacherService.getTeacherByAccount(account);
    }

    @GetMapping("/login")
    public String login(String account,String password){
        log.info("教师登录：{}",account);
        Boolean res=teacherService.login(account,password);
        return res!=null && res? "Success":"Failure";
    }

    @PostMapping("/register")
    public String register(@RequestBody Teacher teacher){
        log.info("教师注册：{}",teacher);
        Boolean res=teacherService.register(teacher);
        return res!=null && res? "Success":"Failure";
    }

    @GetMapping("/course")
    public List<Course> getCourseByTeacherAccount(String account){
        log.info("任教老师查询课程的，account：{}",account);
        return teacherService.getCourseByTeacherAccount(account);
    }

    @GetMapping("/student")
    public List<Student> getStudentByCourseId(Integer id){
        log.info("任教老师查询选课学生，课程id：{}",id);
        return studentService.getStudentByCourseId(id);
    }

    @GetMapping("/student/score")
    public String setScoreByStudentId(Integer id,Integer score){
        log.info("任教老师修改学生成绩，学生id：{}，成绩：{}",id,score);
        Boolean res=teacherService.setScoreByStudentId(id,score);
        log.info("修改结果：{}",res);
        return res!=null && res? "Success":"Failure";
    }
    @GetMapping("/student/getScore")
    public Integer getScoreByStudentId(Integer id){
        log.info("任教老师查询学生成绩，学生id：{}",id);
        Integer score=teacherService.getScoreByStudentId(id);
        return score==null? -1:score;
    }

    @PostMapping("/add")
    public String addCourse(String account,@RequestBody Course course){
        log.info("教师填报课程：账户：{}，课程：{}",account,course);
        return teacherService.addCourse(account,course)? "Success":"Failure";
    }

    @PostMapping("/information")
    public String updateInformation(@RequestBody Teacher teacher){
        log.info("教师修改个人简介：{}",teacher);
        return teacherService.updateInformation(teacher)? "Success":"Failure";
    }
}
