package com.google.navigationdrawer.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.navigationdrawer.ChatFragment;
import com.google.navigationdrawer.StudentFragment;
import com.google.navigationdrawer.ProfileFragment;
import com.google.navigationdrawer.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    boolean hideActionItems;

    StudentFragment  studentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this , drawer ,
                toolbar , R.string.navigation_drawer_open , R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        studentFragment = new StudentFragment();

        //showing message fragment on application start and also prevent showing it again when
        //user rotates device cause rotating destroys activity and creates it again
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    studentFragment).commit();

            navigationView.setCheckedItem(R.id.nav_students);
        }
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        hideActionItems = true;

        switch (item.getItemId()) {

            case R.id.nav_students:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                            studentFragment).commit();

                //showing ActionMenuItems only in StudentsFragment
                hideActionItems = false;

                break;

            case R.id.nav_chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                        new ChatFragment()).commit();
                break;

            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                        new ProfileFragment()).commit();
                break;

            case R.id.nav_share:
                Toast.makeText(this , "Share" , Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_send:
                Toast.makeText(this , "Send" , Toast.LENGTH_SHORT).show();
                break;

        }
        drawer.closeDrawer(GravityCompat.START);

        invalidateOptionsMenu(); //calls onCreateOptionsMenu()

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        if (hideActionItems) {
            menu.findItem(R.id.item_add_person).setVisible(false);
            menu.findItem(R.id.item_search).setVisible(false);

            return false;
        }

        SearchView  searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.item_search));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        menu.findItem(R.id.item_add_person).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent = new Intent(MainActivity.this, NewStudentActivity.class);
                startActivity(intent);

                return false;
            }
        });

        menu.findItem(R.id.item_add_group).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Toast.makeText(MainActivity.this, "در حال بارگذاری مخاطبین...", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, ImportContactsActivity.class);
                startActivity(intent);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}
