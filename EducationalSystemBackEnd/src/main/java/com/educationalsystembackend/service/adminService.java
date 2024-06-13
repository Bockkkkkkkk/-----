package com.educationalsystembackend.service;

import com.educationalsystembackend.pojo.Admin;
import com.educationalsystembackend.pojo.Course;
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
public interface adminService {


    Boolean login(String account, String password);

    List<Course> getAllCourse();

    boolean updateCourse(Course course);

    Boolean register(Admin admin);
}
