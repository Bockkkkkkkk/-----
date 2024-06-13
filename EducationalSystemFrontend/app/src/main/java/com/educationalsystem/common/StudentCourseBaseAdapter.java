package com.educationalsystem.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.educationalsystem.R;

import java.util.List;

public class StudentCourseBaseAdapter extends BaseAdapter {
    private Context context;
    private List<Course> courseList;
    private String studentAccount;

    public StudentCourseBaseAdapter(Context context, List<Course> courseList,String studentAccount) {
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

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder") View view = LayoutInflater.from(context).inflate(R.layout.studentcourse_item_list, null);
        TextView courseName = view.findViewById(R.id.courseName_tv);
        TextView courseTeacher =view.findViewById(R.id.courseTeacher_tv);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView courseId_tv =view.findViewById(R.id.courseId_tv);
        TextView score =view.findViewById(R.id.score_tv);

        Course course = courseList.get(i);


        courseName.setText(course.getName());
        courseTeacher.setText("任课老师:"+Course.getTeacherByCourseId(course.getId()).getName());
        courseId_tv.setText("课程Id："+course.getId());
        //根据学生账户和课程id查询学生成绩
        Integer studentScore = Student.getStudentScoreByAccount(course.getId(),studentAccount);
        if (studentScore!=-1) score.setText("我的成绩："+studentScore);
        else score.setText("未录入成绩");


        return view;
    }
}
