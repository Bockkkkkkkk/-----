package com.educationalsystem.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.educationalsystem.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class StudentChooseCourseBaseAdapter extends BaseAdapter {
    private Context context;
    private List<Course> courseList;
    private String studentAccount;

    public StudentChooseCourseBaseAdapter(Context context, List<Course> courseList,String studentAccount) {
        this.context = context;
        this.courseList = courseList;
        this.studentAccount=studentAccount;
    }
    @Override
    public int getCount() {return courseList.size();}

    @Override
    public Object getItem(int i) {
        return courseList.get(i);
    }

    @Override
    public long getItemId(int i) {return i;}

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder") View view = LayoutInflater.from(context).inflate(R.layout.choose_course_item_list, null);
        TextView courseName = view.findViewById(R.id.courseName_tv);
        TextView courseTeacher =view.findViewById(R.id.courseTeacher_tv);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView courseId_tv =view.findViewById(R.id.courseId_tv);
        TextView courseIntroduction_tv=view.findViewById(R.id.courseIntroduction_tv);

        Course course = courseList.get(i);

        if (course!=null){
            courseName.setText(course.getName());
            courseTeacher.setText("任课老师:"+Course.getTeacherByCourseId(course.getId()).getName());
            courseId_tv.setText("课程Id："+course.getId());
            if (course.getIntroduction()!=null) courseIntroduction_tv.setText("  "+course.getIntroduction());
        }
        //提交按钮
        view.findViewById(R.id.save_btn).setOnClickListener(v->{
            try {
                String response = netUtil.doGet("/student/chooseCourse?courseId="+course.getId()+"&account="+studentAccount);
                if ("Success".equals(response)){
                    Toast.makeText((context.getApplicationContext()),"选课成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context.getApplicationContext(),"选课失败，您已选择该课程",Toast.LENGTH_SHORT).show();
                }
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });




        return view;
    }
}
