package com.educationalsystem.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.ExecutionException;

/**
 * ClassName:Student
 * Package:com.educationalsystembackend.pojo
 * Description:
 *
 * @Author: Bock
 * @Create:2023/12/2113:08
 * @Version 1.0
 */
public class Student {
    private Integer id;
    private String name;
    private Integer grade;
    private String account;
    private String password;

    public static Integer getStudentScoreByAccount(Integer courseId,String account){
        String jsonString;
        Integer score;
        final ObjectMapper mapper = new ObjectMapper();
        try {
            jsonString = netUtil.doGet("/student?courseId="+courseId+"&account="+account);
            System.out.println(jsonString);
            score = mapper.readValue(jsonString, new TypeReference<Integer>() {});

        } catch (ExecutionException | InterruptedException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(score);

        return score;
    }

    public static Integer getScoreByStudentId(Integer id) {
        String jsonString;
        Integer score;
        final ObjectMapper mapper = new ObjectMapper();
        try {
            jsonString = netUtil.doGet("/teacher/student/getScore?id="+id);
            System.out.println(jsonString);
            score = mapper.readValue(jsonString, new TypeReference<Integer>() {});

        } catch (ExecutionException | InterruptedException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(score);

        return score;
    }

    public Student() {
    }

    public Student(int id, String name, int grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getId() {return id;}

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String toString() {
        return "Student{id = " + id + ", name = " + name + ", grade = " + grade + "}";
    }
}
