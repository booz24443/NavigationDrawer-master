package com.google.navigationdrawer;

import android.content.Context;
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

import com.google.navigationdrawer.Adapters.StudentAdapter;
import com.google.navigationdrawer.Models.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentFragment extends Fragment{

    ArrayAdapter<Student> adapter;
    ListView listView;
    List<Student> students;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        students = new ArrayList<>();
        students.add(new Student("علی مقدسی","989371525490", "پشتیبان")) ;
        students.add(new Student("حسین کاسپور","989371525490", "پشتیبان")) ;
        students.add(new Student("تقی محمدی","989371525490", "پشتیبان")) ;
        students.add(new Student("حسین خوش کردار","989371525490", "پشتیبان")) ;
        students.add(new Student("محمد نجم آبادی","989371525490", "پشتیبان")) ;
        students.add(new Student("جواد محمدی","989371525490", "پشتیبان")) ;
        students.add(new Student("حسین کاسپور","989371525490", "پشتیبان")) ;
        students.add(new Student("حسین خوش کردار","989371525490", "پشتیبان")) ;
        students.add(new Student("علی مقدسی","989371525490", "پشتیبان")) ;
        students.add(new Student("تقی محمدی","989371525490", "پشتیبان")) ;
        students.add(new Student("حسین خوش کردار","989371525490", "پشتیبان")) ;
        students.add(new Student("حسین کاسپور","989371525490", "پشتیبان")) ;
        students.add(new Student("علی مقدسی","989371525490", "پشتیبان")) ;
        students.add(new Student("جواد محمدی","989371525490", "پشتیبان")) ;
        students.add(new Student("علی مقدسی","989371525490", "پشتیبان")) ;
        students.add(new Student("محمد نجم آبادی","989371525490", "پشتیبان")) ;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_student, container , false);

        listView = rootView.findViewById(R.id.my_listview);

        adapter = new StudentAdapter(getActivity(), students);
        listView.setAdapter(adapter);

        return rootView;
    }

}
