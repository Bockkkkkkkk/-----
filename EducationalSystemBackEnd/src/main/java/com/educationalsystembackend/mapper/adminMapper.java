package com.educationalsystembackend.mapper;

import com.educationalsystembackend.pojo.Admin;
import com.educationalsystembackend.pojo.Course;
import com.educationalsystembackend.pojo.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * ClassName:adminMapper
 * Package:com.educationalsystembackend.mapper
 * Description:
 *
 * @Author: Bock
 * @Create:2023/12/1922:43
 * @Version 1.0
 */
@Mapper
public interface adminMapper {

    @Select("select * from admin where account=#{account} and password=#{password}")
    Boolean login(String account, String password);

    @Select("select * from course")
    List<Course> selectCourse();

    @Update("update course set name=#{name},introduction=#{introduction},beginTime=#{beginTime},endTime=#{endTime}," +
            "state=#{state} where id=#{id}")
    boolean updateCourse(Course course);

    @Insert("insert into admin(account, password) VALUES (#{account},#{password})")
    Boolean insertAdmin(Admin admin);
}
