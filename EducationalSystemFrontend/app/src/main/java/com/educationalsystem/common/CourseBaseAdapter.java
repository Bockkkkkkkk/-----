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

public class CourseBaseAdapter extends BaseAdapter {
    private Context context;
    private List<Course> courseList;

    public CourseBaseAdapter(Context context, List<Course> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Object getItem(int i) {
        return courseList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder") View view = LayoutInflater.from(context).inflate(R.layout.item_list, null);
        TextView courseName = view.findViewById(R.id.courseName);
        TextView courseTeacher =view.findViewById(R.id.courseTeacher);
        TextView courseState = view.findViewById(R.id.courseState);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView courseId =view.findViewById(R.id.courseId);
        TextView courseBeginTime =view.findViewById(R.id.courseBeginTime);
        TextView courseEndTime =view.findViewById(R.id.courseEndTime);

        Course course = courseList.get(i);
        courseName.setText(course.getName());
        courseTeacher.setText("任教老师:"+Course.getTeacherByCourseId(course.getId()).getName());
        courseState.setText("课程状态："+Course.StateMap.get(course.getState()));
        courseId.setText("课程Id："+course.getId());
        courseBeginTime.setText("选课开始时间:"+course.getBeginTime());
        courseEndTime.setText("选课结束时间:"+course.getEndTime());


        return view;
    }
}
