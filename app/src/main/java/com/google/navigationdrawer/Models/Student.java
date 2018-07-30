package com.google.navigationdrawer.Models;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.navigationdrawer.SQLite.DBHelper;

public class Student {

    private String firstName;
    private String lastName;
    private String level;
    private String major;
    private String supporter;
    private String phone1;
    private String phone2;
    private String parentPhone;
    private String birthDate;

    public Student() {

    }

    public Student(String lastName, String phone1, String supporter) {
        this.lastName = lastName;
        this.phone1 = phone1;
        this.supporter = supporter;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSupporter() {
        return supporter;
    }

    public void setSupporter(String supporter) {
        this.supporter = supporter;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthday) {
        this.birthDate = birthday;
    }

    public ContentValues getContentValues(){
        ContentValues values = new ContentValues();

        values.put(DBHelper.CLM_FIRST_NAME, firstName);
        values.put(DBHelper.CLM_LAST_NAME, lastName);
        values.put(DBHelper.CLM_LEVEL, level);
        values.put(DBHelper.CLM_MAJOR, major);
        values.put(DBHelper.CLM_BIRTH, birthDate);
        values.put(DBHelper.CLM_SUPPORTER, supporter);
        values.put(DBHelper.CLM_PHONE1, phone1);
        values.put(DBHelper.CLM_PHONE2, phone2);
        values.put(DBHelper.CLM_PARENT_PHONE, parentPhone);

        return values;
    }

    public static Student cursorToStudent(Cursor cursor){

        Student student = new Student();

        student.setFirstName(cursor.getString(cursor.getColumnIndex(DBHelper.CLM_FIRST_NAME)));
        student.setLastName(cursor.getString(cursor.getColumnIndex(DBHelper.CLM_LAST_NAME)));

        student.setLevel(cursor.getString(cursor.getColumnIndex(DBHelper.CLM_LEVEL)));
        student.setMajor(cursor.getString(cursor.getColumnIndex(DBHelper.CLM_MAJOR)));

        student.setSupporter(cursor.getString(cursor.getColumnIndex(DBHelper.CLM_SUPPORTER)));
        student.setBirthDate(cursor.getString(cursor.getColumnIndex(DBHelper.CLM_BIRTH)));

        student.setPhone1(cursor.getString(cursor.getColumnIndex(DBHelper.CLM_PHONE1)));
        student.setPhone2(cursor.getString(cursor.getColumnIndex(DBHelper.CLM_PHONE2)));
        student.setParentPhone(cursor.getString(cursor.getColumnIndex(DBHelper.CLM_PARENT_PHONE)));

        return student;
    }
}
