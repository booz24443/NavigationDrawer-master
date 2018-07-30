package com.google.navigationdrawer.Activities;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.navigationdrawer.Models.Student;
import com.google.navigationdrawer.R;
import com.google.navigationdrawer.SQLite.DBHelper;

import net.sqlcipher.database.SQLiteDatabase;

public class NewStudentActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner lvlSpinner, majorSpinner, yearSpinner, daySpinner, monthSpinner;
    private EditText edtFirstName, edtLastName, edtSupporter, edtPhone1, edtPhone2, edtParentPhone;
    private ImageButton imgMore;
    private ArrayAdapter adapter;
    private String[] spinnerItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);

        SQLiteDatabase.loadLibs(this);

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
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        lvlSpinner.setAdapter(adapter);

        //major spinner
        spinnerItems = getResources().getStringArray(R.array.majors);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        majorSpinner.setAdapter(adapter);

        //day Spinner
        spinnerItems = new String[31];
        for (int i = 0; i < 31; i++) {
            spinnerItems[i] = String.valueOf(i + 1);
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        daySpinner.setAdapter(adapter);

        //month spinner
        spinnerItems = new String[12];
        for (int i = 0; i < 12; i++) {
            spinnerItems[i] = String.valueOf(i + 1);
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        monthSpinner.setAdapter(adapter);


        //year spinner
        spinnerItems = new String[20];
        for (int i = 0; i < 20; i++) {
            spinnerItems[i] = String.valueOf(1388 - i);
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        yearSpinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_student, menu);

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
                student.setLevel(lvlSpinner.getSelectedItem().toString());
                student.setMajor(majorSpinner.getSelectedItem().toString());

                student.setBirthDate(yearSpinner.getSelectedItem().toString() +
                                     "-" +  monthSpinner.getSelectedItem().toString() +
                                     "-" + daySpinner.getSelectedItem().toString());
                student.setSupporter(edtSupporter.getText().toString().trim());
                student.setPhone1(phone1);
                student.setPhone2(edtPhone2.getText().toString().trim());
                student.setParentPhone(edtParentPhone.getText().toString().trim());

                DBHelper.getInstance(this).insertStudent(student);
            } else {

                Toast.makeText(this,"فیلد های نام و شماره نباید خالی باشند", Toast.LENGTH_SHORT).show();
            }

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

            edtPhone2.setVisibility(View.VISIBLE);
    }
}
