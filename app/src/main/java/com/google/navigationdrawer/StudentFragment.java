package com.google.navigationdrawer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.navigationdrawer.Activities.StudentDetailActivity;
import com.google.navigationdrawer.Adapters.StudentAdapter;
import com.google.navigationdrawer.Models.Student;
import com.google.navigationdrawer.SQLite.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class StudentFragment extends Fragment implements AdapterView.OnItemClickListener {

    ArrayAdapter<Student> adapter;
    ListView listView;
    List<Student> students;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        students = new ArrayList<>();


//        DBHelper.getInstance(getContext()).insertNewStudent(new Student("علی مقدسی","1243525490", "پشتیبان"));
//        DBHelper.getInstance(getContext()).insertNewStudent(new Student("حسین صفری","98937423525490", "پشتیبان"));
//        DBHelper.getInstance(getContext()).insertNewStudent(new Student("علی ممتاز","98931234525490", "پشتیبان"));
//        DBHelper.getInstance(getContext()).insertNewStudent(new Student("حسین صداقت","9812341525490", "پشتیبان"));
//        DBHelper.getInstance(getContext()).insertNewStudent(new Student("علی محمدی","989371432490", "پشتیبان"));
//        DBHelper.getInstance(getContext()).insertNewStudent(new Student("علی گلابی","989371524390", "پشتیبان"));
//        DBHelper.getInstance(getContext()).insertNewStudent(new Student("علی خربزه","98932325490", "پشتیبان"));
//        DBHelper.getInstance(getContext()).insertNewStudent(new Student("علی دنباله رو","989375225490", "پشتیبان"));
//        DBHelper.getInstance(getContext()).insertNewStudent(new Student("علی کچل","98935425490", "پشتیبان"));
//        DBHelper.getInstance(getContext()).insertNewStudent(new Student("علی میخوم","98945525490", "پشتیبان"));
//        DBHelper.getInstance(getContext()).insertNewStudent(new Student("علی شسی","989371235490", "پشتیبان"));
//        DBHelper.getInstance(getContext()).insertNewStudent(new Student("علی یسش","989371435490", "پشتیبان"));
//        DBHelper.getInstance(getContext()).insertNewStudent(new Student("علی سس","9893745525490", "پشتیبان"));
//        DBHelper.getInstance(getContext()).insertNewStudent(new Student("علی یی","98937123490", "پشتیبان"));
//        DBHelper.getInstance(getContext()).insertNewStudent(new Student("علی ضصث","98425490", "پشتیبان"));
//        DBHelper.getInstance(getContext()).insertNewStudent(new Student("علی ثص","9893723490", "پشتیبان"));


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_student, container , false);

        listView = rootView.findViewById(R.id.my_listview);

        listView.setOnItemClickListener(this);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        //to refresh student list after changes
        students = DBHelper.getInstance(getContext()).getAllStudents();
        adapter = new StudentAdapter(getActivity(), students);

        listView.setAdapter(adapter);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        Intent intent = new Intent(getContext(), StudentDetailActivity.class);

        Student student = (Student) parent.getItemAtPosition(position);

        intent.putExtra("student", student);


        startActivity(intent);
    }

}
