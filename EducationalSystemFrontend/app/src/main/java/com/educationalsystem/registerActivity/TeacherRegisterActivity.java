package com.educationalsystem.registerActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.educationalsystem.MainActivity;
import com.educationalsystem.R;
import com.educationalsystem.common.Teacher;
import com.educationalsystem.common.netUtil;

import java.util.concurrent.ExecutionException;

public class TeacherRegisterActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private final Teacher teacher=new Teacher();

    private EditText teacherName_et,teacherAge_et,teacherDegree_et,account_et,password_et,introduction_et;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);

        //账号
        account_et = findViewById(R.id.account_et);
        //密码
        password_et = findViewById(R.id.password_et);
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

        //返回按钮
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v->{
            startActivity(new Intent(this, MainActivity.class));
        });
    }


    @Override
    public void onClick(View view) {
        //将teacher发送给后端，等待后端返回结果
        teacher.setAccount(account_et.getText().toString());
        teacher.setPassword(password_et.getText().toString());
        teacher.setName(teacherName_et.getText().toString());
        String age=teacherAge_et.getText().toString();
        if (age.length()>0)teacher.setAge(Integer.valueOf(age));
        teacher.setDegree(teacherDegree_et.getText().toString());
        teacher.setIntroduction(introduction_et.getText().toString());
        String response;
        try {
            response = netUtil.doPost("/teacher/register", JSON.toJSON(teacher).toString());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);

        if ("Success".equals(response)){
            Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
        }else {
            Toast.makeText(getApplicationContext(),"注册失败，请检查输入数据是否正确",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        if (checkedId==R.id.male) teacher.setGender(0);
        if (checkedId==R.id.female) teacher.setGender(1);
    }
}
