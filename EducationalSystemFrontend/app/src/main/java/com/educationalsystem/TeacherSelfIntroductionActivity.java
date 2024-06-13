package com.educationalsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.educationalsystem.common.Teacher;
import com.educationalsystem.common.netUtil;

import java.util.concurrent.ExecutionException;

public class TeacherSelfIntroductionActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private Teacher teacher=new Teacher();

    private TextView teacherName_et,teacherAge_et,teacherDegree_et;
    private EditText introduction_et;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_self_introduction);

        //获取账户以查询
        Bundle bundle = getIntent().getExtras();
        teacher.setAccount(bundle.get("teacherAccount").toString());

        //姓名
        teacherName_et = findViewById(R.id.teacherName_et);
        //年龄
        teacherAge_et = findViewById(R.id.teacherAge_et);
        //学历
        teacherDegree_et = findViewById(R.id.teacherDegree_et);
        //个人经历
        introduction_et = findViewById(R.id.introduction_et);

        //保存按钮
        findViewById(R.id.btnSave).setOnClickListener(this);

        //设置性别
        RadioGroup rg= findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(this);

        //教师信息查询回显
        teacher=Teacher.getTeacherByAccount(teacher.getAccount());
        teacherName_et.setText(teacher.getName());
        teacherAge_et.setText(teacher.getAge().toString());
        teacherDegree_et.setText(teacher.getDegree());
        introduction_et.setText(teacher.getIntroduction());

        Integer gender=teacher.getGender();
        if (gender==0) rg.check(R.id.male);
        else if (gender== 1) rg.check(R.id.female);

        //返回按钮
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v->{
            Intent intent = new Intent(this, TeacherActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }

    @Override
    public void onClick(View view) {
        //将course发送给后端，等待后端返回结果
        teacher.setName(teacherName_et.getText().toString());
        teacher.setAge(Integer.valueOf(teacherAge_et.getText().toString()));
        teacher.setDegree(teacherDegree_et.getText().toString());
        teacher.setIntroduction(introduction_et.getText().toString());
        String response;
        try {
            response = netUtil.doPost("/teacher/information", JSON.toJSON(teacher).toString());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);

        if ("Success".equals(response)){
            Toast.makeText(getApplicationContext(),"保存成功",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(),"保存失败，请检查输入数据是否正确",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        if (checkedId==R.id.male) teacher.setGender(0);
        if (checkedId==R.id.female) teacher.setGender(1);
    }
}