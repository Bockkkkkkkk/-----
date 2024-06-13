package com.educationalsystem.registerActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.educationalsystem.MainActivity;
import com.educationalsystem.R;
import com.educationalsystem.common.Student;
import com.educationalsystem.common.netUtil;

import java.util.concurrent.ExecutionException;

public class StudentRegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private final Student student=new Student();

    private EditText account_et,password_et,studentName_et;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        //账号
        account_et = findViewById(R.id.account_et);
        //密码
        password_et = findViewById(R.id.password_et);
        //姓名
        studentName_et = findViewById(R.id.studentName_et);

        //保存按钮
        findViewById(R.id.btnSave).setOnClickListener(this);

        //返回按钮
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v->{
            startActivity(new Intent(this, MainActivity.class));
        });
    }

    @Override
    public void onClick(View view) {
        //将student发送给后端，等待后端返回结果
        student.setAccount(account_et.getText().toString());
        student.setPassword(password_et.getText().toString());
        student.setName(studentName_et.getText().toString());
        String response;
        try {
            response = netUtil.doPost("/student/register", JSON.toJSON(student).toString());
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
}