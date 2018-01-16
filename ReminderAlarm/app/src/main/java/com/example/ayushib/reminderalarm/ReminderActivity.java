package com.example.ayushib.reminderalarm;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.ayushib.reminderalarm.fragment.ItemFragment;
import com.example.ayushib.reminderalarm.fragment.ListFragment;

public class ReminderActivity extends AppCompatActivity implements ItemFragment.Listener, ListFragment.Listener, ViewPager.OnPageChangeListener {

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                itemFragment.prepareNew();
                fab.setVisibility(View.VISIBLE);
                getSupportActionBar().setDisplayShowTitleEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                break;
            case 1:
                fab.setVisibility(View.GONE);
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onItemClick(ListItem listItem) {
        itemFragment.prepareEdit(listItem);
        mViewPager.setCurrentItem(1);
    }



    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return ListFragment.newInstance(1);
            else if (editKey == null)
                itemFragment = ItemFragment.newInstance();
            else {
                itemFragment = ItemFragment.newInstance(editKey);
                mViewPager.setCurrentItem(1);
                editKey = null;
            }
            return itemFragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Reminders";
                case 1:
                    return "Reminder";
            }
            return null;
        }
    }

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public void onSave(int mode, ListItem listItem) {
        mViewPager.setCurrentItem(0);
    }

   /* @Override
    public Application getApp() {

        return (Application) super.getApp;
    }*/

    private final int REQUEST_CODE_SPLASH = 1000;

    ItemFragment itemFragment;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPLASH)
            initialize();
    }

    public void initialize() {
        fab = (FloatingActionButton) findViewById(R.id.add_task_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemFragment.prepareNew();
                mViewPager.setCurrentItem(1);
            }
        });
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fragment_reminder_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mViewPager.setCurrentItem(0);
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    private String editKey;

    @Override
    public void onBackPressed() {
        if (mViewPager.getCurrentItem() == 0)
            super.onBackPressed();
        else {
            mViewPager.setCurrentItem(0);
            return;
        }
    }
}
