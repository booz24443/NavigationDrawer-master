package com.google.navigationdrawer;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.google.navigationdrawer.models.Contact;

import java.util.ArrayList;

public class GetContacts {

    public static ArrayList<Contact> getThemAll (Context context) {


        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        ArrayList<Contact> contacts = new ArrayList<>();

        int contactId;
        String contactName ;
        String contactPhone = "";
        String lastPhoneNum = "";

        while (cursor.moveToNext()) {

            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

            Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? ", new String[]{id}, null);

            contactId = Integer.valueOf(id);
            contactName = name;

            while (phoneCursor.moveToNext()) {
                String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


                //prevent adding repeated phoneNumber
                if ( !phoneNumber.isEmpty() ) {

                    //trimming phone numbers
                    if (phoneNumber.contains("+98")) {
                        phoneNumber = phoneNumber.replace("+98", "0");
                    }
                    if (phoneNumber.contains(" ")) {
                        phoneNumber = phoneNumber.replace(" ", "");
                    }
                    if (phoneNumber.contains("-")) {
                        phoneNumber = phoneNumber.replace("-", "");
                    }

                    if ( !phoneNumber.equals(lastPhoneNum) ) {

                        contactPhone += phoneNumber + "z";
                        lastPhoneNum = phoneNumber;
                    }

                }

            }

            //just adding contacts having phone number
            if (!contactPhone.isEmpty()) {

                Contact contact = new Contact(contactId, contactName, contactPhone);
                contacts.add(contact);
            }

            //clearing last contact phone
            contactPhone = "";
        }

        return contacts;
    }

}
