package com.educationalsystem;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.educationalsystem.common.Course;
import com.educationalsystem.common.netUtil;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

public class TeacherAddCourseActivity extends AppCompatActivity implements View.OnClickListener {

    private Course course=new Course();
    private TextView beginTime_tv,endTime_tv;
    private EditText courseName_et,introduction_et;

    private String account;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_add_course);

        Bundle bundle = getIntent().getExtras();
        account=bundle.get("teacherAccount").toString();

        //课程名称
        courseName_et = findViewById(R.id.etCourseName);
        //课程简介
        introduction_et = findViewById(R.id.introduction_et);

        //时间选择按钮
        findViewById(R.id.beginTime_btn).setOnClickListener(this);
        findViewById(R.id.endTime_btn).setOnClickListener(this);
        //时间文本框
        beginTime_tv=findViewById(R.id.beginTime_tv);
        endTime_tv=findViewById(R.id.endTime_tv);

        //保存按钮
        findViewById(R.id.btnSave).setOnClickListener(this);

        //返回按钮
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v->{
            Intent intent = new Intent(this, TeacherActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.beginTime_btn){
            DatePickerDialog datePickerDialog=new DatePickerDialog(this, (datePicker, year, month, dayOfMonth) -> {
                //将时间显示到tv，并把时间转换为LocalDateTime传给course
                beginTime_tv.setText(year+"年"+(month+1)+"月"+dayOfMonth+"日");
                course.setBeginTime(LocalDateTime.of(year,month+1,dayOfMonth,0,0));
            }, 2023, 1, 1);
            datePickerDialog.show();
        }else if (v.getId()==R.id.endTime_btn){
            DatePickerDialog datePickerDialog=new DatePickerDialog(this,(datePicker, year, month,dayOfMonth) -> {
                //将时间显示到tv，并把时间转换为LocalDateTime传给course
                endTime_tv.setText(year+"年"+(month+1)+"月"+dayOfMonth+"日");
                course.setEndTime(LocalDateTime.of(year,month+1,dayOfMonth,0,0));
            },2023,1,1);
            datePickerDialog.show();
        } else if (v.getId()==R.id.btnSave) {
            //将course发送给后端，等待后端返回结果
            course.setName(courseName_et.getText().toString());
            course.setIntroduction(introduction_et.getText().toString());
            String response= null;

            try {
                response = netUtil.doPost("/teacher/add?account="+account, JSON.toJSON(course).toString());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            System.out.println(response);

            if ("Success".equals(response)){
                Toast.makeText(getApplicationContext(),"填报成功",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(),"填报失败，请检查输入数据是否正确",Toast.LENGTH_SHORT).show();
            }
        }
    }
}