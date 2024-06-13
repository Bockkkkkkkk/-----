package com.educationalsystembackend.mapper;

import com.educationalsystembackend.pojo.Course;
import com.educationalsystembackend.pojo.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * ClassName:teacherMapper
 * Package:com.educationalsystembackend.mapper
 * Description:
 *
 * @Author: Bock
 * @Create:2023/12/2120:33
 * @Version 1.0
 */
@Mapper
public interface teacherMapper {

    @Select("select * from teacher where id=#{teacherId}")
    Teacher getById(Integer teacherId);

    @Select("select teacherId from course_teacher where courseId=#{courseId}")
    Integer getTeacherByCourseId(Integer courseId);

    @Select("select * from course where id in (" +
            "(select courseId from course_teacher where teacherId=" +
            "(select id from teacher where account=#{account})))")
    List<Course> selectCourseByTeacherAccount(String account);

    @Select("select * from teacher where account=#{account} and password=#{password}")
    Boolean login(String account, String password);

    @Update("update course_student set score=#{score} where studentId=#{id}")
    Boolean updateScoreByStudentId(Integer id, Integer score);

    void insertCourse(Course course);

    @Insert("insert into course_teacher(courseId, teacherId) " +
            "VALUES (#{courseId},(select id from teacher where account=#{account}))")
    boolean insertCourseTeacher(Integer courseId, String account);

    @Update("update teacher set name=#{name},age=#{age},gender=#{gender},degree=#{degree},introduction=#{introduction} " +
            "where account=#{account}")
    boolean updateInformation(Teacher teacher);

    @Insert("insert into teacher(name, age, gender, degree, introduction, account, password) " +
            "VALUES (#{name},#{age},#{gender},#{degree},#{introduction},#{account},#{password})")
    Boolean insertTeacher(Teacher teacher);

    @Select("select score from course_student where studentId=#{studentId};")
    Integer selectScoreByStudentId(Integer studentId);

    @Select("select * from teacher where account=#{account};")
    Teacher selectTeacherByAccount(String account);

}
