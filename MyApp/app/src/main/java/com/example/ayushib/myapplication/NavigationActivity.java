package com.example.ayushib.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

import static com.example.ayushib.myapplication.R.id.imageViewDiary;
import static com.example.ayushib.myapplication.R.id.imageViewFood;
import static com.example.ayushib.myapplication.R.id.imageViewGame;
import static com.example.ayushib.myapplication.R.id.imageViewWorkout;

public class NavigationActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;

    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //create a back button to return in previous state



        //actions on items of hamburger
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.home){
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_container, new HomeFragment());
                    fragmentTransaction.commit();
                    getSupportActionBar().setTitle("Main Menu");
                    item.setChecked(true);
                    drawerLayout.closeDrawers();


                }else if(id == R.id.profile){

                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_container, new AccountFragment());
                    fragmentTransaction.commit();
                    getSupportActionBar().setTitle("Profile Menu");
                    item.setChecked(true);
                    drawerLayout.closeDrawers();

                } else if (id == R.id.notif){
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_container, new NotificationFragment());
                    fragmentTransaction.commit();
                    getSupportActionBar().setTitle("Notification");
                    item.setChecked(true);
                    drawerLayout.closeDrawers();

                } else if (id == R.id.setting){
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_container, new SettingFragment());
                    fragmentTransaction.commit();
                    getSupportActionBar().setTitle("Settings");
                    item.setChecked(true);
                    drawerLayout.closeDrawers();


                } else if (id == R.id.help){
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_container, new HelpFragment());
                    fragmentTransaction.commit();
                    getSupportActionBar().setTitle("Help & Feedback");
                    item.setChecked(true);
                    drawerLayout.closeDrawers();

                }else if (id ==R.id.logout){
                    firebaseAuth.signOut();
                    finish();
                    Intent intent = new Intent(NavigationActivity.this, loginActivity.class);

                }

                return true;
            }
        });




        //actions on bottom navigation view

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.nav_bottom);


       /* bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.home:
                                setTitle("Main Menu");
                                HomeFragment fragmentHome = new HomeFragment();
                                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.main_container, fragmentHome);
                                fragmentTransaction.commit();
                                break;

                            case R.id.training:
                                setTitle("Training");
                                TrainingFragment fragmentTrain = new TrainingFragment();
                               fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.main_container, fragmentTrain);
                                fragmentTransaction.commit();
                                break;


                            case R.id.progress:
                                setTitle("Progress Log");
                                ProgressFragment fragmentProgress = new ProgressFragment();
                                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.main_container, fragmentProgress);
                                fragmentTransaction.commit();
                                break;


                            case R.id.chat:
                                setTitle("Chat Forum");
                                ChatFragment fragmentChat = new ChatFragment();
                                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.main_container, fragmentChat);
                                fragmentTransaction.commit();
                                break;

                        }

                        return true;
                    }
                });

        //Used to select an item programmatically
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
*/
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /*ImageView imageViewGame = (ImageView) findViewById(R.id.imageViewGame);
    ImageView imageViewDiary = (ImageView) findViewById(R.id.imageViewDiary);
    ImageView imageViewFood = (ImageView) findViewById(R.id.imageViewFood);
    ImageView imageViewWorkout = (ImageView) findViewById(R.id.imageViewWorkout);*/

    View.OnClickListener listener = new View.OnClickListener() {
        @Override

        public void onClick(View v) {
            Intent intent = new Intent();
            switch (v.getId()){
                case R.id.imageViewGame:
                    intent.setClass(NavigationActivity.this, GameActivity.class);
                    break;
                case R.id.imageViewDiary:
                    intent.setClass(NavigationActivity.this, DiaryActivity.class);
                    break;
                case R.id.imageViewFood:
                    intent.setClass(NavigationActivity.this, NutritionActivity.class);
                    break;
                case R.id.imageViewWorkout:
                    intent.setClass(NavigationActivity.this, WorkoutActivity.class);
                    break;
                default:
                    break;
            }
            startActivity(intent);

        }


    };


}
