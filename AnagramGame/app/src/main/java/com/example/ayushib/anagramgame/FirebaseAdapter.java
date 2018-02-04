package com.example.ayushib.anagramgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ayushi B on 02-Feb-18.
 */

public class FirebaseAdapter extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        List<String> anawords = new ArrayList<>();
        anawords.add("ACCOUNT");
        anawords.add("ADDITION");
        anawords.add("BEHAVIOUR");
        anawords.add("WEATHER");
        anawords.add("HOSPITAL");


        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        for(String anaword : anawords) {
            rootRef.child("anawords").child(anaword).setValue(true);
        }

    }


}
