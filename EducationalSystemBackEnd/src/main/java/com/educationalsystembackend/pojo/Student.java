package com.educationalsystembackend.pojo;

import org.springframework.objenesis.instantiator.perc.PercInstantiator;

import java.util.PriorityQueue;

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

    public Student() {
    }

    public Student(Integer id, String name, Integer grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public Student(Integer id, String name, Integer grade, String account, String password) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.account = account;
        this.password = password;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Student(Integer id, String name, Integer grade, String account) {
        this.id = id;
        this.name = name;
        this.grade = grade;
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
     * @return grade
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * 设置
     * @param grade
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String toString() {
        return "Student{id = " + id + ", name = " + name + ", grade = " + grade + "}";
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
}
