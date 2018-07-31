package com.google.navigationdrawer;

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

import com.google.navigationdrawer.activities.StudentDetailActivity;
import com.google.navigationdrawer.adapters.StudentAdapter;
import com.google.navigationdrawer.models.Student;
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

        Student student = (Student) parent.getItemAtPosition(position);

        Intent intent = new Intent(getContext(), StudentDetailActivity.class).putExtra("student", student);

        startActivity(intent);
    }

}
