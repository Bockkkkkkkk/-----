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
public interface teacherService {


    Teacher getTeacherByCourseId(Integer id);

    List<Course> getCourseByTeacherAccount(String  account);

    Boolean login(String account, String password);


    Boolean setScoreByStudentId(Integer id, Integer score);

    boolean addCourse(String account,Course course);

    boolean updateInformation(Teacher teacher);

    Boolean register(Teacher teacher);

    Integer getScoreByStudentId(Integer id);

    Teacher getTeacherByAccount(String account);
}
