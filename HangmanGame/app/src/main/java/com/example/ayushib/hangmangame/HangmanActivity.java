package com.example.ayushib.hangmangame;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

import android.widget.ImageView;

public class HangmanActivity extends Activity {

    ImageView playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);

        playButton = (ImageView) findViewById(R.id.playBtn);

       playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(HangmanActivity.this, HangmanCategory.class);
                startActivity(myIntent);
            }
        });
    }

}
