package com.educationalsystembackend.service.impl;

import com.educationalsystembackend.mapper.adminMapper;
import com.educationalsystembackend.mapper.teacherMapper;
import com.educationalsystembackend.pojo.Course;
import com.educationalsystembackend.pojo.Student;
import com.educationalsystembackend.pojo.Teacher;
import com.educationalsystembackend.service.adminService;
import com.educationalsystembackend.service.teacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class teacherServiceImpl implements teacherService {
    @Autowired
    private teacherMapper teacherMapper;


    @Override
    public Teacher getTeacherByCourseId(Integer id) {
        Integer teacherId = teacherMapper.getTeacherByCourseId(id);
        return teacherMapper.getById(teacherId);
    }

    @Override
    public List<Course> getCourseByTeacherAccount(String account) {
        log.info("查询课程通过教师账户：{}",account);
        return teacherMapper.selectCourseByTeacherAccount(account);
    }

    @Override
    public Boolean login(String account, String password) {
        return teacherMapper.login(account,password);
    }


    @Override
    public Boolean setScoreByStudentId(Integer id, Integer score) {
        if(score==null || score>100 || score<0) return false;
        return teacherMapper.updateScoreByStudentId(id,score);
    }

    @Override
    public boolean addCourse(String account,Course course) {
        //规范数据，将课程状态设置未审核
        course.setState(0);
        //检查添加的课程数据是否有误
        if(course.getName()==null || course.getBeginTime()==null || course.getEndTime()==null ||
                course.getState()==null || course.getEndTime().isBefore(course.getBeginTime()))
            return false;

        teacherMapper.insertCourse(course);
        return teacherMapper.insertCourseTeacher(course.getId(),account);
    }

    @Override
    public boolean updateInformation(Teacher teacher) {
        //检查添加的课程数据是否有误
        if (teacher.getAge()==null || teacher.getName()==null || teacher.getDegree()==null ||
                teacher.getGender()==null|| teacher.getAccount()==null)
            return false;

        return teacherMapper.updateInformation(teacher);
    }

    @Override
    public Boolean register(Teacher teacher) {
        //检查添加的用户数据是否有误
        if (teacher.getAccount().isEmpty()||teacher.getPassword().isEmpty()||teacher.getAge()==null ||
                teacher.getName().isEmpty() || teacher.getDegree().isEmpty() || teacher.getGender()==null)
            return false;

        return teacherMapper.insertTeacher(teacher);
    }

    @Override
    public Integer getScoreByStudentId(Integer id) {
        return teacherMapper.selectScoreByStudentId(id);
    }

    @Override
    public Teacher getTeacherByAccount(String account) {
        return teacherMapper.selectTeacherByAccount(account);
    }

}
