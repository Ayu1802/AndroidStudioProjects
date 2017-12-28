package com.example.ayushib.remindertask;

/**
 * Created by Ayushi B on 19-Dec-17.
 */
import com.example.ayushib.remindertask.List;
import com.example.ayushib.remindertask.Application;


public class Helper {

    public Helper(Boolean firebase_persistence_enabled) {

        super(firebase_persistence_enabled);
    }



    public static Helper getInstance() {
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
