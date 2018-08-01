package com.google.navigationdrawer.activities;

import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.navigationdrawer.adapters.ContactAdapter;
import com.google.navigationdrawer.GetContacts;
import com.google.navigationdrawer.models.Contact;
import com.google.navigationdrawer.R;
import com.google.navigationdrawer.SQLite.DBHelper;

import java.util.ArrayList;

public class ImportContactsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private ContactAdapter adapter;
    private ProgressBar progressBar;
    private ArrayList<Contact> contacts;
    private ArrayList<Contact> selectedContacts = new ArrayList<>();
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_contacts);

        progressBar = findViewById(R.id.progress_bar);
        listView = findViewById(R.id.listview_contact);

        actionBar = getSupportActionBar();

        //setting home button
        actionBar.setDisplayHomeAsUpEnabled(true);


        //to change actionBar direction
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }



        new MyTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "Import");

        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        listView.setOnItemClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_commit, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == android.R.id.home) {
            finish();

        } else if (item.getItemId() == R.id.item_commit) {

            new MyTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "Save");

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (!selectedContacts.contains(contacts.get(position))) {

            selectedContacts.add(contacts.get(position));
        } else {

            selectedContacts.remove(contacts.get(position));
        }

        actionBar.setTitle(selectedContacts.size() + " مورد");

    }

    /**
     * This class loads and imports contacts while displaying progressBar
     */
    private class MyTask extends AsyncTask<String, String , Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Integer doInBackground(String... strings) {

            if (strings[0].equals("Import")) {

                contacts = GetContacts.getThemAll(getApplicationContext());
                adapter = new ContactAdapter(ImportContactsActivity.this, contacts);

                return -1;

            } else { //when :  (strings[0].equals("Save"))

                int addCount = DBHelper.getInstance(getApplicationContext()).insertContacts(selectedContacts);


                return addCount;
            }

        }

        @Override
        protected void onPostExecute(Integer addCount) {
            super.onPostExecute(addCount);

            if (addCount != -1) {
                Toast.makeText(getApplicationContext(), addCount + " مخاطب با موفقیت اضافه شدند", Toast.LENGTH_SHORT).show();

                finish();
            }

            listView.setAdapter(adapter);

            progressBar.setVisibility(View.GONE);
        }
    }

}
