package com.educationalsystem.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.educationalsystem.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class StudentBaseAdapter extends BaseAdapter {
    private Context context;
    private List<Student> studentList;

    public StudentBaseAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int i) {
        return studentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder") View view = LayoutInflater.from(context).inflate(R.layout.studentitem_list, null);
        TextView studentName_tv = view.findViewById(R.id.studentName_tv);
        TextView studentId_tv =view.findViewById(R.id.studentId_tv);
        EditText score_et = view.findViewById(R.id.score_et);
        Button save_btn=view.findViewById(R.id.save_btn);

        Student student = studentList.get(i);
        studentName_tv.setText(student.getName());
        studentId_tv.setText("学生Id："+student.getId());
        //查询学生成绩回显
        Integer score=Student.getScoreByStudentId(student.getId());
        if (score!=-1) score_et.setText(score.toString());
        else score_et.setText("未录入成绩");

        save_btn.setOnClickListener(v -> {
            try {
                String response = netUtil.doGet("/teacher/student/score?id="+studentId_tv.getText().toString().split("：")[1]+
                        "&score=" + score_et.getText().toString());
                System.out.println(response);
                if ("Success".equals(response)){
                    Toast.makeText((context.getApplicationContext()),"修改成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context.getApplicationContext(),"修改失败，请检查输入数据是否正确",Toast.LENGTH_SHORT).show();
                }
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        return view;
    }
}
