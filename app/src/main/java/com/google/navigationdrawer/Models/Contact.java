package com.google.navigationdrawer.Models;

import android.content.ContentValues;

import com.google.navigationdrawer.SQLite.DBHelper;

public class Contact {

    private int id;
    private String name;
    private String phone;

    public Contact(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ContentValues getContentValues(){
        ContentValues values = new ContentValues();

        String[] name = getName().split(" ");

        //dividing first & last name
        if (name.length > 1) {

            String lastName = "";

            values.put(DBHelper.CLM_FIRST_NAME, name[0]);

            for (int i = 1; i < name.length; i++) {

                lastName += name[i];
            }
            values.put(DBHelper.CLM_LAST_NAME, lastName);

        } else {
            values.put(DBHelper.CLM_LAST_NAME, getName());
        }

        String[] phoneNumber = getPhone().split("z");

        values.put(DBHelper.CLM_PHONE1, phoneNumber[0]);

        //adding second phone number if exists
        if (phoneNumber.length > 1) {
            values.put(DBHelper.CLM_PHONE2, phoneNumber[1]);
        }

        return values;
    }
}
