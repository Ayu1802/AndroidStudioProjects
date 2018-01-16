package com.example.ayushib.reminderalarm.firebase;

/**
 * Created by Ayushi B on 15-Jan-18.
 */

public interface CreateListener <Child extends Item> extends FireListener {
    public void onCreate(Child item);
}
