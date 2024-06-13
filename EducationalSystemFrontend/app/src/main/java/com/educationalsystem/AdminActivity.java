package com.educationalsystem;

import android.annotation.SuppressLint;
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

public class AdminActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        ListView lv_course = findViewById(R.id.lv_course);
        List<Course> courseList = Course.getAllCourse();
        lv_course.setAdapter(new CourseBaseAdapter(this, courseList));
        //点击课程列表条目
        lv_course.setOnItemClickListener(this);
        //退出按钮
//        findViewById(R.id.btnExit).setOnClickListener(v->{this.finish();});
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
        TextView courseId = view.findViewById(R.id.courseId);

        Intent intent = new Intent(this, AdminUpdateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("courseId",courseId.getText().toString().split("：")[1]);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}