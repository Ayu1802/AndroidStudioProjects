package com.example.ayushib.menuapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;



public class MenuActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    setTitle("Main Menu"); //setting title of each
                    HomeFragment fragment = new HomeFragment();
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.content, fragment);
                    fragmentTransaction1.commit();
                    return true;

                case R.id.training:
                    setTitle("Training Menu"); //setting title of each
                    TrainingFragment fragment3 = new TrainingFragment();
                    FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction3.replace(R.id.content, fragment3);
                    fragmentTransaction3.commit();
                    return true;

                case R.id.progress:
                    setTitle("Progress Menu"); //setting title of each
                    ProgressFragment fragment4 = new ProgressFragment();
                    FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction4.replace(R.id.content, fragment4);
                    fragmentTransaction4.commit();
                    return true;


                case R.id.chat:
                    setTitle("Chat Menu"); //setting title of each
                    ChatFragment fragment2 = new ChatFragment();
                    FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.content, fragment2);
                    fragmentTransaction2.commit();
                    return true;


            }

            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //app starts with home fragment
        setTitle("Main Menu");
        HomeFragment fragment = new HomeFragment();
        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction1.replace(R.id.content, fragment);
        fragmentTransaction1.commit();

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //create a back button to return in previous state
        setupDrawerContent(navigationView);
    }

    public void selectItemDrawer (MenuItem menuItem){
        android.support.v4.app.Fragment frag = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {

            case R.id.home:
                fragmentClass = HomeFragment.class;
                break;

            case R.id.profile:
                fragmentClass = ProfileFragment.class;
                break;

            case R.id.notif:
                fragmentClass = NotificationFragment.class;
                break;

            case R.id.setting:
                fragmentClass = SettingFragment.class;
                break;

            default:
                fragmentClass = HelpFragment.class;

        }

        try{
            frag = (android.support.v4.app.Fragment) fragmentClass.newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, frag).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }

    private  void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
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
