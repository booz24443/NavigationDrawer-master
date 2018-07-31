package com.google.navigationdrawer.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.navigationdrawer.models.Contact;
import com.google.navigationdrawer.R;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {

    private List<Contact> contacts;
    private Activity activity;


    public ContactAdapter(@NonNull Activity activity, List<Contact> contacts) {
        super(activity, R.layout.item_list_contact, contacts);

        this.activity = activity;
        this.contacts = contacts;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewholder;

        if (convertView == null) {

            convertView = LayoutInflater.from(activity).inflate(R.layout.item_list_contact, parent , false);
            viewholder = new ViewHolder(convertView);
            convertView.setTag(viewholder);

        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        viewholder.fill(position);
        return convertView;

    }

    public class ViewHolder{

        private TextView[] tvPhoneNum = new TextView[6]; //0 to 6-1
        private TextView name;

        public ViewHolder(View view) {

            tvPhoneNum[0] = view.findViewById(R.id.tv_contact_phone1);
            tvPhoneNum[1] = view.findViewById(R.id.tv_contact_phone2);
            tvPhoneNum[2] = view.findViewById(R.id.tv_contact_phone3);
            tvPhoneNum[3] = view.findViewById(R.id.tv_contact_phone4);
            tvPhoneNum[4] = view.findViewById(R.id.tv_contact_phone5);
            tvPhoneNum[5] = view.findViewById(R.id.tv_contact_phone6);
            name = view.findViewById(R.id.tv_contact_name);
        }

        public void fill (int position) {

            Contact contact = contacts.get(position);

            name.setText(contact.getName());

                //dividing phone numbers to TextViews
                String[] phoneNumber = contact.getPhone().split("z");

                for (int i = 0; i < phoneNumber.length; i++) {

                    tvPhoneNum[i].setVisibility(View.VISIBLE);
                    tvPhoneNum[i].setText(phoneNumber[i]);
                }

                for (int i = 0; i < tvPhoneNum.length - phoneNumber.length; i++) {

                    tvPhoneNum[(tvPhoneNum.length - 1) -i].setVisibility(View.GONE);
                }

        }
    }
}
