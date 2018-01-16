package com.example.ayushib.reminderalarm.firebase;

import com.google.firebase.database.DatabaseError;

/**
 * Created by Ayushi B on 15-Jan-18.
 */

public interface FireListener {
    public void onError(DatabaseError error);
}
