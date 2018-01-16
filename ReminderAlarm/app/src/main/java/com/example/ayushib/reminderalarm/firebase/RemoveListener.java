package com.example.ayushib.reminderalarm.firebase;

/**
 * Created by Ayushi B on 15-Jan-18.
 */

public interface RemoveListener <Child extends Item> extends FireListener {
    public void onRemove(Child item);
}
