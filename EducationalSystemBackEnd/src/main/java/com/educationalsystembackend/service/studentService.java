package com.educationalsystembackend.service;

import com.educationalsystembackend.pojo.Course;
import com.educationalsystembackend.pojo.Student;
import com.educationalsystembackend.pojo.Teacher;

import java.util.List;

/**
 * ClassName:adminService
 * Package:com.educationalsystembackend.service
 * Description:
 *
 * @Author: Bock
 * @Create:2023/12/1922:42
 * @Version 1.0
 */
public interface studentService {

    List<Student> getStudentByCourseId(Integer courseId);

    Boolean login(String account, String password);

    List<Course> getCourseByStudentAccount(String account);

    List<Course> getCourseByTime();

    boolean chooseCourse(Integer courseId, String account);

    Integer getStudentScoreByAccount(Integer courseId,String account);

    List<Course> getWithdrawCourse(String account);

    boolean withdrawCourse(Integer courseId, String account);

    Boolean register(Student student);
}
