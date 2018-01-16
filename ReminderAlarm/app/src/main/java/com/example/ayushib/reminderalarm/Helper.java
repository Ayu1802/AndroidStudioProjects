package com.example.ayushib.reminderalarm;

/**
 * Created by Ayushi B on 15-Jan-18.
 */

public class Helper extends com.example.ayushib.reminderalarm.util.Helper{

    public Helper(Boolean firebase_persistence_enabled) {

        super(firebase_persistence_enabled);
    }



    public static com.example.ayushib.reminderalarm.util.Helper getInstance() {
        return getApplication().getHelper();
    }

    public static Application getApplication() {
        return (Application) application;

    }

    private List list;

    public List getList() {
        return list;
    }

    public void setList(List items) {
        list = items;
    }

    @Override
    public void onReady(String userId) {
        super.onReady(userId);
        setList(new List("reminders", userId));
    }
}

