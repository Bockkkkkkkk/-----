package com.educationalsystem.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.ExecutionException;

/**
 * ClassName:Teacher
 * Package:com.educationalsystembackend.pojo
 * Description:
 *
 * @Author: Bock
 * @Create:2023/12/2113:05
 * @Version 1.0
 */
public class Teacher {
    private Integer id;
    private String name;
    private Integer age;
    private Integer gender;
    private String degree;
    private String introduction;

    private String account;

    private String password;


    public Teacher() {
    }

    public Teacher(Integer id, String name, Integer age, Integer gender, String degree, String introduction) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.degree = degree;
        this.introduction = introduction;
    }

    public static Teacher getTeacherByAccount(String account) {
        String jsonString;
        Teacher teacher;
        final ObjectMapper mapper = new ObjectMapper();
        try {
            jsonString = netUtil.doGet("/teacher/account?account="+account);
            System.out.println(jsonString);
            teacher = mapper.readValue(jsonString, new TypeReference<Teacher>() {});

        } catch (ExecutionException | InterruptedException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(teacher);

        return teacher;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return account
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置
     * @param account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取
     * @return gender
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 设置
     * @param gender
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 获取
     * @return degree
     */
    public String getDegree() {
        return degree;
    }

    /**
     * 设置
     * @param degree
     */
    public void setDegree(String degree) {
        this.degree = degree;
    }

    /**
     * 获取
     * @return introduction
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置
     * @param introduction
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String toString() {
        return "Teacher{id = " + id + ", name = " + name + ", age = " + age + ", gender = " + gender + ", degree = " + degree + ", introduction = " + introduction + "}";
    }

}
