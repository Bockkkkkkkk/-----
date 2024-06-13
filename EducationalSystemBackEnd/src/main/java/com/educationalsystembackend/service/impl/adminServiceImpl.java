package com.educationalsystembackend.service.impl;

import com.educationalsystembackend.mapper.adminMapper;
import com.educationalsystembackend.mapper.teacherMapper;
import com.educationalsystembackend.pojo.Admin;
import com.educationalsystembackend.pojo.Course;
import com.educationalsystembackend.pojo.Teacher;
import com.educationalsystembackend.service.adminService;
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
public class adminServiceImpl implements adminService {
    @Autowired
    private adminMapper adminMapper;


    @Override
    public Boolean login(String account, String password) {
        return adminMapper.login(account,password);
    }

    @Override
    public List<Course> getAllCourse() {
        return adminMapper.selectCourse();
    }

    @Override
    public boolean updateCourse(Course course) {
        //检查更新数据是否有误
        if(course.getName().isEmpty() || course.getBeginTime()==null || course.getEndTime()==null ||
                course.getState()==null || course.getEndTime().isBefore(course.getBeginTime()))
            return false;
        return adminMapper.updateCourse(course);
    }

    @Override
    public Boolean register(Admin admin) {
        //检查添加的用户数据是否有误
        if (admin.getAccount().isEmpty()||admin.getPassword().isEmpty())
            return false;
        return adminMapper.insertAdmin(admin);
    }

}
