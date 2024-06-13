package com.educationalsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.educationalsystem.common.Course;
import com.educationalsystem.common.StudentChooseCourseBaseAdapter;

import java.util.List;

public class StudentChooseCourseActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_choose_course);
        //学生账户
        String studentAccount = getIntent().getExtras().get("studentAccount").toString();

        //课程列表
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ListView lv_course = findViewById(R.id.lv_course);
        List<Course> courseList = Course.getCourseByTime();
        lv_course.setAdapter(new StudentChooseCourseBaseAdapter(this, courseList,studentAccount));
        lv_course.setOnItemClickListener(this);

        //返回按钮
        Button btnBack = findViewById(R.id.back_btn);
        btnBack.setOnClickListener(v->{
            Intent intent = new Intent(this, StudentActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("studentAccount",studentAccount);
            intent.putExtras(bundle);
            startActivity(intent);

        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}