package com.educationalsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.educationalsystem.common.Course;
import com.educationalsystem.common.CourseBaseAdapter;

import java.util.List;

public class TeacherActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private String teacherAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        teacherAccount = getIntent().getExtras().get("teacherAccount").toString();

        //课程列表
        ListView lv_course = findViewById(R.id.lv_course);
        List<Course> courseList = Course.getCourseByAccount(teacherAccount);
        lv_course.setAdapter(new CourseBaseAdapter(this, courseList));
        lv_course.setOnItemClickListener(this);

        //填报课程按钮
        findViewById(R.id.addCourse_btn).setOnClickListener(v -> {
            Intent intent = new Intent(this, TeacherAddCourseActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("teacherAccount",teacherAccount);
            intent.putExtras(bundle);
            startActivity(intent);
        });

        //个人简介按钮
        findViewById(R.id.selfIntroduction_btn).setOnClickListener(v -> {
            Intent intent = new Intent(this, TeacherSelfIntroductionActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("teacherAccount",teacherAccount);
            intent.putExtras(bundle);
            startActivity(intent);
        });

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView courseId = view.findViewById(R.id.courseId);

        Intent intent = new Intent(this, studentManagementActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("courseId",courseId.getText().toString().split("：")[1]);
        bundle.putString("teacherAccount",teacherAccount);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}