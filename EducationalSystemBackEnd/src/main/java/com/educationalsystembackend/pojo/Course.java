package com.educationalsystembackend.pojo;

import java.time.LocalDateTime;

/**
 * ClassName:Course
 * Package:com.educationalsystembackend.pojo
 * Description:
 *
 * @Author: Bock
 * @Create:2023/12/2114:09
 * @Version 1.0
 */
public class Course {
    private Integer id;
    private String name;
    private String introduction;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;
    private Integer state;

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

    /**
     * 获取
     * @return beginTime
     */
    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    /**
     * 设置
     * @param beginTime
     */
    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 获取
     * @return endTime
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * 设置
     * @param endTime
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取
     * @return state
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置
     * @param state
     */
    public void setState(int state) {
        this.state = state;
    }

    public String toString() {
        return "Course{id = " + id + ", name = " + name + ", introduction = " + introduction + ", beginTime = " + beginTime + ", endTime = " + endTime + ", state = " + state + "}";
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
}
