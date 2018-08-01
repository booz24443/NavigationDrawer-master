package com.google.navigationdrawer.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.navigationdrawer.models.Student;
import com.google.navigationdrawer.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class StudentDetailActivity extends AppCompatActivity {

    LinearLayout contentLayout;
    private TextView tvLName, tvFName, tvMajor, tvLevel, tvSupporter, tvBirth, tvPhone1, tvPhone2, tvPPhone;
    private Student student;

    //changing activity font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        student = getIntent().getParcelableExtra("student");

        setViews();

        startAnimation();


    }

    private void setViews() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //to change actionBar direction
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

        tvFName = findViewById(R.id.tv_student_fname);
        tvLName = findViewById(R.id.tv_student_lname);
        tvMajor = findViewById(R.id.tv_student_major);
        tvLevel = findViewById(R.id.tv_student_level);
        tvSupporter = findViewById(R.id.tv_student_supporter);
        tvBirth = findViewById(R.id.tv_student_birth);
        tvPhone1 = findViewById(R.id.tv_student_phone1);
        tvPhone2 = findViewById(R.id.tv_student_phone2);
        tvPPhone = findViewById(R.id.tv_student_pphone);

        tvFName.setVisibility(View.INVISIBLE);
        tvLName.setVisibility(View.INVISIBLE);
        tvMajor.setVisibility(View.INVISIBLE);
        tvLevel.setVisibility(View.INVISIBLE);
        tvSupporter.setVisibility(View.INVISIBLE);
        tvBirth.setVisibility(View.INVISIBLE);
        tvPhone1.setVisibility(View.INVISIBLE);
        tvPhone2.setVisibility(View.INVISIBLE);
        tvPPhone.setVisibility(View.INVISIBLE);

        contentLayout = findViewById(R.id.layout_content);

        if (student.getPhone2() == null || student.getPhone2().isEmpty()) {

            findViewById(R.id.line_phone2).setVisibility(View.GONE);
            findViewById(R.id.tv_title_phone2).setVisibility(View.GONE);

            tvPhone2.setVisibility(View.GONE);
            findViewById(R.id.line_student_phone2).setVisibility(View.GONE);
        }

        tvFName.setText(student.getFirstName());
        tvLName.setText(student.getLastName());
        tvMajor.setText(student.getMajor());
        tvLevel.setText(student.getLevel());
        tvSupporter.setText(student.getSupporter());
        tvBirth.setText(student.getBirthDate());
        tvPhone1.setText(student.getPhone1());
        tvPhone2.setText(student.getPhone2());
        tvPPhone.setText(student.getParentPhone());
    }

    private void startAnimation() {

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_open);

        contentLayout.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Animation animation1 = AnimationUtils.loadAnimation(StudentDetailActivity.this, R.anim.tv_fade_in);

                tvFName.startAnimation(animation1);
                tvLName.startAnimation(animation1);
                tvMajor.startAnimation(animation1);
                tvLevel.startAnimation(animation1);
                tvSupporter.startAnimation(animation1);
                tvBirth.startAnimation(animation1);
                tvPhone1.startAnimation(animation1);
                tvPPhone.startAnimation(animation1);

                if (tvPhone2.getVisibility() == View.INVISIBLE) {
                    tvPhone2.startAnimation(animation1);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_student_detail, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();

        } else if (id == R.id.item_delete) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("آیا از حذف این دانش آموز اطمینان دارید؟");

            builder.setNegativeButton("بله", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setPositiveButton("خیر", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.show();

        } else if (id == R.id.item_edit) {

            Intent intent = new Intent(this, EditStudentActivity.class).putExtra("student", student);

            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
