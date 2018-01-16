package com.example.ayushib.reminderalarm;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by Ayushi B on 15-Jan-18.
 */

public class List extends com.example.ayushib.reminderalarm.firebase.List<ListItem>{

    public List(String childPath, String uId) {
        super(childPath, uId);
    }


}
