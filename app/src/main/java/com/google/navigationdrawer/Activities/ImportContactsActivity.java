package com.google.navigationdrawer.Activities;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.navigationdrawer.Adapters.ContactAdapter;
import com.google.navigationdrawer.GetContacts;
import com.google.navigationdrawer.Models.Contact;
import com.google.navigationdrawer.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.ThreadPoolExecutor;

public class ImportContactsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private ContactAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_contacts);

        progressBar = findViewById(R.id.progress_bar);
        listView = findViewById(R.id.listview_contact);

        //setting home button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //to change actionBar direction
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

        MyTask myTask = new MyTask();
        //starting AsyncTask without sending any params
        myTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        listView.setOnItemClickListener(this);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//        view.setSelected(true);
    }

    private class MyTask extends AsyncTask<String, String , String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {

            ArrayList<Contact> contacts = GetContacts.getThemAll(getApplicationContext());

            adapter = new ContactAdapter(ImportContactsActivity.this, contacts);

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            listView.setAdapter(adapter);

            progressBar.setVisibility(View.GONE);
        }
    }

}
