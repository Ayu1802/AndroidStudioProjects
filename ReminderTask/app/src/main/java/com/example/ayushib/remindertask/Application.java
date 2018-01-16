package com.example.ayushib.remindertask;

import android.graphics.BitmapFactory;

import com.example.ayushib.notifier.Scheduler;
import com.example.ayushib.remindertask.Helper;

import com.example.ayushib.remindertask.ListItem;

/**
 * Created by Ayushi B on 19-Dec-17.
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
