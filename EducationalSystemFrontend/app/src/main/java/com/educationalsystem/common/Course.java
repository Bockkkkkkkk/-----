package com.educationalsystem.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Course {
    private int id;
    private String name;
    private String introduction;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime beginTime;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime endTime;
    private int state;

    public static HashMap<Integer,String> StateMap=new HashMap<>();

    static {
        StateMap.put(0,"未审核");
        StateMap.put(1,"已审核");
        StateMap.put(2,"开课中");
        StateMap.put(3,"已结课");
    }

    //该方法会返回所有课程，仅限管理员使用
    public static List<Course> getAllCourse(){
        String jsonString;
        List<Course> courseList;
        final ObjectMapper mapper = new ObjectMapper();
        try {
            jsonString = netUtil.doGet("/admin/courseList");
            courseList = mapper.readValue(jsonString, new TypeReference<List<Course>>() {});

        } catch (ExecutionException | InterruptedException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(courseList);

        return courseList;

    }

    //该方法会根据id返回课程
    public static List<Course> getCourseByAccount(String account){
        String jsonString;
        List<Course> courseList;
        final ObjectMapper mapper = new ObjectMapper();
        try {
            jsonString = netUtil.doGet("/teacher/course?account="+account);
            courseList = mapper.readValue(jsonString, new TypeReference<List<Course>>() {});

        } catch (ExecutionException | InterruptedException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(courseList);

        return courseList;
    }

    public static List<Course> getCourseByStudentAccount(String account){
        String jsonString;
        List<Course> courseList;
        final ObjectMapper mapper = new ObjectMapper();
        try {
            jsonString = netUtil.doGet("/student/courses?account="+account);
            courseList = mapper.readValue(jsonString, new TypeReference<List<Course>>() {});

        } catch (ExecutionException | InterruptedException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(courseList);

        return courseList;
    }

    public static Teacher getTeacherByCourseId(Integer id){
        String jsonString;
        Teacher teacher;
        final ObjectMapper mapper = new ObjectMapper();
        try {
            jsonString = netUtil.doGet("/teacher?id="+id);
            System.out.println(jsonString);
            teacher = mapper.readValue(jsonString, new TypeReference<Teacher>() {});

        } catch (ExecutionException | InterruptedException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(teacher);

        return teacher;
    }

    public static List<Student> getStudentByCourseId(Integer courseId) {
        String jsonString;
        List<Student> studentList;
        final ObjectMapper mapper = new ObjectMapper();
        try {
            jsonString = netUtil.doGet("/student/course?courseId="+courseId);
            studentList = mapper.readValue(jsonString, new TypeReference<List<Student>>() {});

        } catch (ExecutionException | InterruptedException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(studentList);

        return studentList;

    }

    public static List<Course> getCourseByTime() {
        String jsonString;
        List<Course> courseList;
        final ObjectMapper mapper = new ObjectMapper();
        try {
            jsonString = netUtil.doGet("/student/course/time");
            courseList = mapper.readValue(jsonString, new TypeReference<List<Course>>() {});

        } catch (ExecutionException | InterruptedException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(courseList);

        return courseList;
    }

    public static List<Course> getCourseByTimeAndAccount(String account) {
        String jsonString;
        List<Course> courseList;
        final ObjectMapper mapper = new ObjectMapper();
        try {
            jsonString = netUtil.doGet("/student/getWithdrawCourse?account="+account);
            courseList = mapper.readValue(jsonString, new TypeReference<List<Course>>() {});

        } catch (ExecutionException | InterruptedException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(courseList);

        return courseList;
    }












    public Course() {
    }

    public Course(int id, String name, String introduction, LocalDateTime beginTime, LocalDateTime endTime, int state) {
        this.id = id;
        this.name = name;
        this.introduction = introduction;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.state = state;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", state=" + state +
                '}';
    }
}
