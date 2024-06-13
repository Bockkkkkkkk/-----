package com.educationalsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.educationalsystem.common.Course;
import com.educationalsystem.common.StudentCourseBaseAdapter;
import com.educationalsystem.common.Teacher;

import java.util.List;

public class StudentActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //学生账户
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        String studentAccount;
        if (getIntent().getExtras()!=null)
            studentAccount = getIntent().getExtras().get("studentAccount").toString();
        else {
            studentAccount = null;
        }

        //课程列表
        ListView lv_course = findViewById(R.id.lv_course);
        List<Course> courseList = Course.getCourseByStudentAccount(studentAccount);
        lv_course.setAdapter(new StudentCourseBaseAdapter(this, courseList,studentAccount));
        lv_course.setOnItemClickListener(this);

        //选课按钮
        findViewById(R.id.chooseCourse_btn).setOnClickListener(v -> {
            Intent intent = new Intent(this, StudentChooseCourseActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("studentAccount",studentAccount);
            intent.putExtras(bundle);
            startActivity(intent);
        });

        //退课按钮
        findViewById(R.id.withdrawCourse_btn).setOnClickListener(v -> {
            Intent intent = new Intent(this, StudentWithdrawCourseActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("studentAccount",studentAccount);
            intent.putExtras(bundle);
            startActivity(intent);
        });

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //查看授课教师简介

        //获取课程Id
        TextView courseId_tv = view.findViewById(R.id.courseId_tv);
        String courseId = courseId_tv.getText().toString().split("：")[1];

        //根据课程Id查询教师信息，课程与老师是一对一的关系
        Teacher teacher=Course.getTeacherByCourseId(Integer.valueOf(courseId));

        //通过提醒对话框的形式展示教师信息
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(teacher.getName());
        builder.setMessage("年龄："+teacher.getAge()+"\n"+
                            "性别："+getGender(teacher.getGender())+"\n"+
                            "学历："+teacher.getDegree()+"\n"+
                            "个人经历："+"\n"+teacher.getIntroduction());
        builder.create().show();

    }

    //处理性别问题
    public String getGender(Integer num){
        switch (num){
            case 0:
                return "男";
            case 1:
                return "女";
            default:
                return "未知";
        }
    }
}