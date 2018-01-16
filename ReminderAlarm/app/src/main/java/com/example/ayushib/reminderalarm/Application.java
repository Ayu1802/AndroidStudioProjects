package com.example.ayushib.reminderalarm;

import android.graphics.BitmapFactory;

import com.example.ayushib.reminderalarm.util.Helper;

/**
 * Created by Ayushi B on 15-Jan-18.
 */

public class Application extends ReminderActivity {

    @Override
    public void setHelper() {
        Helper.setApplication(this);
        setHelper(new Helper(getResources().getBoolean(R.bool.firebase_persistence_enabled)));
    }

    @Override
    public Helper getHelper() {
        return (Helper) super.getHelper();
    }

    @Override
    public void onFirebaseCreate() {

        super.onFirebaseCreate();
        Scheduler.setAndroidContext(getApplicationContext());
        ListItem.setContentActivity(ReminderActivity.class);
        ListItem.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
    }
}
