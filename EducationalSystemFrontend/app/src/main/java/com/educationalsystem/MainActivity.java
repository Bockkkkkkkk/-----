package com.educationalsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.educationalsystem.common.netUtil;
import com.educationalsystem.registerActivity.AdminRegisterActivity;
import com.educationalsystem.registerActivity.StudentRegisterActivity;
import com.educationalsystem.registerActivity.TeacherRegisterActivity;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    EditText etUsername, etPassword;  //存输入的用户名、密码
    String strUsername,strPassword,strIdentity;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText)findViewById(R.id.etUsername);   //用户名
        etPassword = (EditText)findViewById(R.id.etPassword);   //密码
        RadioGroup rg=(RadioGroup) findViewById(R.id.rg); // 身份单选按钮组
        Button loginBtn=findViewById(R.id.btnLogin);//登录按钮
        Button registerBtn=findViewById(R.id.btnRegister);

        rg.setOnCheckedChangeListener(this);

        //点击登录按钮，发送get请求，若数据库中存在此用户则进入对应界面
        loginBtn.setOnClickListener(v -> {
            strUsername = etUsername.getText().toString();
            strPassword = etPassword.getText().toString();

            //response==Success 登陆成功 跳转至对应界面；0 登陆失败 提示用户名或密码错误
            String response;
            try {
                response = netUtil.doGet(strIdentity +
                        "/login?" + "account=" + strUsername + "&"+"password=" + strPassword);
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            if("Success".equals(response)){
                switch(strIdentity){
                    case "/admin":
                        //跳转至管理员界面
                        Intent intent1 = new Intent(this, AdminActivity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent1);
                        break;
                    case "/teacher":
                        //跳转至老师界面
                        Intent intent2 = new Intent(this, TeacherActivity.class);
                        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        Bundle bundle = new Bundle();
                        bundle.putString("teacherAccount",etUsername.getText().toString());
                        intent2.putExtras(bundle);
                        startActivity(intent2);
                        break;
                    case "/student":
                        //跳转至学生界面
                        Intent intent3 = new Intent(this, StudentActivity.class);
                        intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("studentAccount",etUsername.getText().toString());
                        intent3.putExtras(bundle1);
                        startActivity(intent3);
                        break;
                }
            }else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("登录失败");
                builder.setMessage("请确认账号密码输入是否正确！！！\n 或用户不存在，请注册新账户");
                builder.create().show();
            }



        });

        //注册按钮
        registerBtn.setOnClickListener(
                v -> {
                    if(strIdentity==null) Toast.makeText(getApplicationContext(),"请选择您的身份",Toast.LENGTH_SHORT).show();
                    else {
                        switch (strIdentity){
                            case "/admin":
                                Intent intent1 = new Intent(this, AdminRegisterActivity.class);
                                startActivity(intent1);
                                break;
                            case "/teacher":
                                Intent intent2 = new Intent(this, TeacherRegisterActivity.class);
                                startActivity(intent2);
                                break;
                            case "/student":
                                Intent intent3 = new Intent(this, StudentRegisterActivity.class);
                                startActivity(intent3);
                                break;
                        }
                    }
                }
        );

    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        if (checkedId==R.id.adminRB) strIdentity="/admin";
        if (checkedId==R.id.teacherRB) strIdentity="/teacher";
        if (checkedId==R.id.studentRB) strIdentity="/student";

//        switch (checkedId){
//            case R.id.adminRB :
//                strIdentity="admin";
//                break;
//            case R.id.teacherRB:
//                strIdentity="teacher";
//                break;
//            case R.id.studentRB:
//                strIdentity="student";
//                break;
//        }
    }
}