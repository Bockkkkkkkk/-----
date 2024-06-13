package com.educationalsystembackend.service.impl;

import com.educationalsystembackend.mapper.adminMapper;
import com.educationalsystembackend.mapper.studentMapper;
import com.educationalsystembackend.mapper.teacherMapper;
import com.educationalsystembackend.pojo.Course;
import com.educationalsystembackend.pojo.Student;
import com.educationalsystembackend.pojo.Teacher;
import com.educationalsystembackend.service.studentService;
import com.educationalsystembackend.service.teacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:adminServiceImpl
 * Package:com.educationalsystembackend.service.impl
 * Description:
 *
 * @Author: Bock
 * @Create:2023/12/1922:42
 * @Version 1.0
 */
@Service
@Slf4j
public class studentServiceImpl implements studentService {
    @Autowired
    private studentMapper studentMapper;
    @Autowired
    private adminMapper adminMapper;

    @Override
    public List<Student> getStudentByCourseId(Integer courseId) {
        return studentMapper.selectStudentByCourseId(courseId);
    }

    @Override
    public Boolean login(String account, String password) {
        return studentMapper.login(account,password);
    }


    @Override
    public List<Course> getCourseByStudentAccount(String account) {
        return studentMapper.getCourseByStudentAccount(account);
    }

    @Override
    public List<Course> getCourseByTime() {
        //根据现在的时间返回课程、该课程已审核
        List<Course> courseList = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (Course course : adminMapper.selectCourse()) {
            if (course.getBeginTime().isBefore(now) && course.getEndTime().isAfter(now) && course.getState()==1)
                courseList.add(course);
        }
        return courseList;
    }

    @Override
    public boolean chooseCourse(Integer courseId, String account) {
        //如果已存在则返回false，即该学生已选择该课程
        Boolean isSelected =studentMapper.selectCourseStudent(courseId,account);
        if(isSelected!=null && isSelected) return false;
        return studentMapper.chooseCourse(courseId,account);
    }

    @Override
    public Integer getStudentScoreByAccount(Integer courseId,String account) {
        return studentMapper.selectStudentScoreByAccount(courseId,account);
    }

    @Override
    public List<Course> getWithdrawCourse(String account) {
        //查询可退选课程
        //1.查询该学生已选课程
        //2.该课程在可退选时间内
        List<Course> courseList = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (Course course : studentMapper.selectWithdrawCourse(account)) {
            if (course.getBeginTime().isBefore(now) && course.getEndTime().isAfter(now))
                courseList.add(course);
        }

        return courseList;
    }

    @Override
    public boolean withdrawCourse(Integer courseId, String account) {
        return studentMapper.deleteCourseStudent(courseId,account);
    }

    @Override
    public Boolean register(Student student) {
        //检查添加的用户数据是否有误
        if (student.getAccount().isEmpty()||student.getPassword().isEmpty()||student.getName().isEmpty())
            return false;
        return studentMapper.insertStudent(student);
    }

}
