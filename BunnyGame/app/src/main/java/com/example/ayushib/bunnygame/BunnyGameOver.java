package com.example.ayushib.bunnygame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Ayushi B on 17-Jan-18.
 */

public class BunnyGameOver extends AppCompatActivity {

    ImageView imgover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bunnyover);

        imgover = (ImageView) findViewById(R.id.restart);

        imgover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(BunnyGameOver.this, BunnyActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
