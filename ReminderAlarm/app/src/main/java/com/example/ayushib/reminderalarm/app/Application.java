package com.example.ayushib.reminderalarm.app;

import com.example.ayushib.reminderalarm.util.Helper;
import com.google.firebase.FirebaseApp;

/**
 * Created by Ayushi B on 15-Jan-18.
 */

public abstract class Application extends android.app.Application {

    public Boolean isReady() {
        return getHelper().isReady();
    }

    private Helper helper;

    public Helper getHelper() {
        return helper;
    }

    protected void setHelper(Helper value) {
        helper = value;
    }

    public abstract void setHelper();

    @Override
    public void onCreate() {
        super.onCreate();
        if(!FirebaseApp.getApps(this).isEmpty())
            onFirebaseCreate();

    }

    public void onFirebaseCreate() {
        setHelper();
    }
}
