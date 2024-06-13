package com.educationalsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.educationalsystem.common.Course;
import com.educationalsystem.common.Student;
import com.educationalsystem.common.StudentBaseAdapter;

import java.util.List;

public class studentManagementActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_management);

        //获取课程Id
        Bundle bundle = getIntent().getExtras();
        Integer courseId=Integer.valueOf(bundle.get("courseId").toString());

        //学生列表
        ListView lv_course = findViewById(R.id.lv_student);
        //根据课程id查询学生
        List<Student> studentList = Course.getStudentByCourseId(courseId);
        lv_course.setAdapter(new StudentBaseAdapter(this, studentList));
        lv_course.setOnItemClickListener(this);

        //返回按钮
        Button back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(v->{
            Intent intent = new Intent(this, TeacherActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}