package com.educationalsystembackend.mapper;

import com.educationalsystembackend.pojo.Course;
import com.educationalsystembackend.pojo.Student;
import com.educationalsystembackend.pojo.Teacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
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
public interface studentMapper {


    @Select("select * from student where id in (select studentId from course_student where courseId=#{courseId})")
    List<Student> selectStudentByCourseId(Integer courseId);

    @Select("select * from student where account=#{account}")
    Boolean login(String account, String password);

    @Select("select score from course_student where courseId=#{courseId} " +
            "and studentId=(select id from student where account=#{account})")
    Integer selectStudentScoreByAccount(Integer courseId,String account);

    @Select("select * from course where id in " +
            "(select courseId from course_student where studentId=" +
            "(select id from student where account=#{account}));")
    List<Course> getCourseByStudentAccount(String account);

    @Insert("insert into course_student (courseId, studentId) " +
            "values (#{courseId},(select id from student where account=#{account}))")
    boolean chooseCourse(Integer courseId, String account);

    @Select("select * from course_student where courseId=#{courseId} " +
            "and studentId=(select id from student where account=#{account})")
    Boolean selectCourseStudent(Integer courseId, String account);

    @Select("select * from course where id in (" +
            "select courseId from course_student where studentId=(" +
            "select  id from student where account=#{account}))")
    List<Course> selectWithdrawCourse(String account);

    @Delete("delete from course_student  where courseId=#{courseId} and studentId=" +
            "(select id from student where account=#{account})")
    boolean deleteCourseStudent(Integer courseId, String account);

    @Insert("insert into student(name,account, password) VALUES (#{name},#{account},#{password})")
    Boolean insertStudent(Student student);
}
