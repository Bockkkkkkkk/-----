package com.educationalsystembackend.controller.student;

import com.educationalsystembackend.pojo.Course;
import com.educationalsystembackend.pojo.Student;
import com.educationalsystembackend.service.adminService;
import com.educationalsystembackend.service.studentService;
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
@RequestMapping("/student")
@Slf4j
public class studentController {

    @Autowired
    private studentService studentService;

    @GetMapping("/login")
    public String login(String account,String password){
        log.info("学生登录：{}",account);
        Boolean res=studentService.login(account,password);
        return res!=null && res? "Success":"Failure";
    }
    @PostMapping("/register")
    public String register(@RequestBody Student student){
        log.info("学生注册：{}",student);
        Boolean res=studentService.register(student);
        return res!=null && res? "Success":"Failure";
    }

    @GetMapping("/course")
    public List<Student> getStudentByCourseId(Integer courseId){
        log.info("根据课程id查询学生：{}",courseId);
        return studentService.getStudentByCourseId(courseId);
    }

    @GetMapping("/courses")
    public List<Course> getCourseByStudentAccount(String account){
        log.info("根据学生账户查询课程：{}",account);
        return studentService.getCourseByStudentAccount(account);
    }
    @GetMapping("/course/time")
    public List<Course> getCourseByTime(){
        log.info("学生查询可选课程");
        return studentService.getCourseByTime();
    }

    @GetMapping("/chooseCourse")
    public String chooseCourse(Integer courseId,String account){
        log.info("{}学生选择课程 课程id：{} ",account,courseId);
        return studentService.chooseCourse(courseId,account)? "Success":"Failure";
    }

    @GetMapping("/getWithdrawCourse")
    public List<Course> getWithdrawCourse(String account){
        log.info("{}学生查询可退选课程",account);
        return studentService.getWithdrawCourse(account);
    }

    @GetMapping("/withdrawCourse")
    public String withdrawCourse(Integer courseId,String account){
        log.info("{}学生退选课程 课程id：{} ",account,courseId);
        return studentService.withdrawCourse(courseId,account)? "Success":"Failure";
    }

    @GetMapping
    public Integer getStudentScoreByAccount(Integer courseId,String account){
        log.info("查询学生成绩通过课程id和账户：课程id:{} 账户：{}",courseId,account);
        Integer score = studentService.getStudentScoreByAccount(courseId, account);
        return score==null? -1:score;
    }




}
