package com.example.ayushib.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class NavigationActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //create a back button to return in previous state

        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.home){
                    Intent intent = new Intent(NavigationActivity.this, NavigationActivity.class);
                    startActivity(intent);


                }else if(id == R.id.profile){

                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.drawer, new AccountFragment());
                    fragmentTransaction.commit();
                    getSupportActionBar().setTitle("Profile Menu");
                    item.setChecked(true);

                } else if (id == R.id.setting){
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.drawer, new SettingFragment());
                    fragmentTransaction.commit();
                    getSupportActionBar().setTitle("Settings");
                    item.setChecked(true);

                }else if (id ==R.id.logout){
                    firebaseAuth.signOut();
                    finish();
                    Intent intent = new Intent(NavigationActivity.this, loginActivity.class);

                }

                return true;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }




}
