package com.google.navigationdrawer.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.navigationdrawer.models.Student;
import com.google.navigationdrawer.R;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {

    private List<Student> students;
    private Activity activity;

    public StudentAdapter(@NonNull Activity activity, List<Student> students) {
        super(activity, R.layout.item_list_student, students);

        this.activity = activity;
        this.students = students;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewholder;

        if (convertView == null) {

            convertView = LayoutInflater.from(activity).inflate(R.layout.item_list_student, parent , false);
            viewholder = new ViewHolder(convertView);
            convertView.setTag(viewholder);

        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        viewholder.fill(position);
        return convertView;

    }

    private class ViewHolder {

        private TextView tvPhoneNumber, tvName, tvSupporter;

        public ViewHolder(View view) {

            tvPhoneNumber = view.findViewById(R.id.list_item_phonenum);
            tvName = view.findViewById(R.id.list_item_name);
            tvSupporter = view.findViewById(R.id.list_item_supporter);

        }

        public void fill (int position) {

            Student student = students.get(position);

            tvPhoneNumber.setText(student.getPhone1());
            tvSupporter.setText(student.getSupporter());

            //display student first name if exists
            if (student.getFirstName() != null) {

                tvName.setText(student.getFirstName() + " " + student.getLastName());
            } else {

                tvName.setText(student.getLastName());
            }



        }
    }
}
