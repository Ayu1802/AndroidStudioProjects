package com.example.ayushib.memorygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MemoryActivity extends AppCompatActivity {

    ImageView btnplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        btnplay = (ImageView) findViewById(R.id.btnplay);

        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MemoryActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
