package com.google.navigationdrawer.activities;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.navigationdrawer.models.Student;
import com.google.navigationdrawer.R;
import com.google.navigationdrawer.SQLite.DBHelper;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class NewStudentActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner lvlSpinner, majorSpinner, yearSpinner, daySpinner, monthSpinner;
    private EditText edtFirstName, edtLastName, edtSupporter, edtPhone1, edtPhone2, edtParentPhone;
    private ImageButton imgMore;
    private ArrayAdapter adapter;
    private String[] spinnerItems;

    //changing activity font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);

        initViews();

        setSpinners();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        imgMore.setOnClickListener(this);
    }

    private void initViews() {

        lvlSpinner = findViewById(R.id.spinner_lvl);
        majorSpinner = findViewById(R.id.spinner_major);
        yearSpinner = findViewById(R.id.spinner_year);
        monthSpinner = findViewById(R.id.spinner_month);
        daySpinner = findViewById(R.id.spinner_day);

        edtFirstName = findViewById(R.id.edt_first_name);
        edtLastName = findViewById(R.id.edt_last_name);
        edtSupporter = findViewById(R.id.edt_supporter);
        edtPhone1 = findViewById(R.id.edt_phone1);
        edtPhone2 = findViewById(R.id.edt_phone2);
        edtParentPhone = findViewById(R.id.edt_parent_phone);
        imgMore = findViewById(R.id.img_btn_more);

        edtPhone2.setVisibility(View.GONE);

        //to change actionBar direction
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }

    private void setSpinners() {
        //level spinner
        spinnerItems = getResources().getStringArray(R.array.levels);
        adapter = new ArrayAdapter<>(this, R.layout.item_spinner_student, spinnerItems);

        lvlSpinner.setAdapter(adapter);

        //major spinner
        spinnerItems = getResources().getStringArray(R.array.majors);
        adapter = new ArrayAdapter<>(this, R.layout.item_spinner_student, spinnerItems);

        majorSpinner.setAdapter(adapter);

        //day Spinner
        spinnerItems = new String[31];
        for (int i = 0; i < 31; i++) {
            spinnerItems[i] = String.valueOf(i + 1);
        }
        adapter = new ArrayAdapter<>(this, R.layout.item_spinner_student, spinnerItems);

        daySpinner.setAdapter(adapter);

        //month spinner
        spinnerItems = new String[12];
        for (int i = 0; i < 12; i++) {
            spinnerItems[i] = String.valueOf(i + 1);
        }
        adapter = new ArrayAdapter<>(this, R.layout.item_spinner_student, spinnerItems);

        monthSpinner.setAdapter(adapter);


        //year spinner
        spinnerItems = new String[20];
        for (int i = 0; i < 20; i++) {
            spinnerItems[i] = String.valueOf(1388 - i);
        }
        adapter = new ArrayAdapter<>(this, R.layout.item_spinner_student, spinnerItems);

        yearSpinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_commit, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home) {
            finish();
        }

        if (item.getItemId() == R.id.item_commit) {

            String phone1 = edtPhone1.getText().toString().trim();
            String lastName = edtLastName.getText().toString().trim();

            if (!phone1.isEmpty() && !lastName.isEmpty()) {

                Student student = new Student();

                student.setFirstName(edtFirstName.getText().toString().trim());
                student.setLastName(lastName);
                student.setGrade(lvlSpinner.getSelectedItem().toString());
                student.setMajor(majorSpinner.getSelectedItem().toString());

                student.setBirthDate(yearSpinner.getSelectedItem().toString() +
                                     "-" +  monthSpinner.getSelectedItem().toString() +
                                     "-" + daySpinner.getSelectedItem().toString());
                student.setSupporter(edtSupporter.getText().toString().trim());
                student.setPhone1(phone1);
                student.setPhone2(edtPhone2.getText().toString().trim());
                student.setParentPhone(edtParentPhone.getText().toString().trim());

                if (DBHelper.getInstance(this).insertNewStudent(student)) {

                    Toast toast = Toast.makeText(this,"دانش آموز با موفقیت اضافه شد", Toast.LENGTH_SHORT);

                    showToast(toast);
                    finish();
                } else {

                    Toast toast = Toast.makeText(this, "این شماره قبلا وارد شده است", Toast.LENGTH_SHORT);
                    showToast(toast);
                }

            } else {

                Toast toast = Toast.makeText(this,"فیلد های نام و شماره نباید خالی باشند", Toast.LENGTH_SHORT);
                showToast(toast);
            }

        }

        return super.onOptionsItemSelected(item);
    }

    private void showToast(Toast toast) {

        //changing toast font size
        ViewGroup group = (ViewGroup) toast.getView();
        TextView messageTextView = (TextView) group.getChildAt(0);
        messageTextView.setTextSize(18);

        toast.show();
    }

    @Override
    public void onClick(View v) {

            edtPhone2.setVisibility(View.VISIBLE);
    }
}
