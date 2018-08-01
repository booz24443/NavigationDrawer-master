package com.google.navigationdrawer.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.navigationdrawer.SQLite.DBHelper;

public class Student implements Parcelable{

    private int studentId;
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


    public Student(Parcel parcel) {

        this.studentId = parcel.readInt();
        this.firstName = parcel.readString();
        this.lastName = parcel.readString();
        this.level = parcel.readString();
        this.major = parcel.readString();
        this.birthDate = parcel.readString();
        this.supporter = parcel.readString();
        this.phone1 = parcel.readString();
        this.phone2 = parcel.readString();
        this.parentPhone = parcel.readString();
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

        values.put(DBHelper.CLM_ID, studentId);
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

        student.setStudentId(cursor.getInt(cursor.getColumnIndex(DBHelper.CLM_ID)));

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(studentId);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(level);
        dest.writeString(major);
        dest.writeString(birthDate);
        dest.writeString(supporter);
        dest.writeString(phone1);
        dest.writeString(phone2);
        dest.writeString(parentPhone);

    }

    public static Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
