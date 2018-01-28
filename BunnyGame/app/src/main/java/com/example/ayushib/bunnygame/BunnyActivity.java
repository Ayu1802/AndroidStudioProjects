package com.example.ayushib.bunnygame;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class BunnyActivity extends AppCompatActivity {

    ImageView bunny1, bunny2, bunny3, bunny4;
    TextView left, score;
    Button btnStart;

    Random r;

    int currentscore = 0, finalpt = 1000, chance = 5, tempifleft = 0;

    int which = 0;
    int whichsave = 0;

    AnimationDrawable ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bunny);

        r = new Random();

        btnStart = (Button) findViewById(R.id.btnStart);

        left = (TextView) findViewById(R.id.left);
        score = (TextView) findViewById(R.id.score);

        bunny1 = (ImageView) findViewById(R.id.bunny1);
        bunny2 = (ImageView) findViewById(R.id.bunny2);
        bunny3 = (ImageView) findViewById(R.id.bunny3);
        bunny4 = (ImageView) findViewById(R.id.bunny4);

        bunny1.setVisibility(View.INVISIBLE);
        bunny2.setVisibility(View.INVISIBLE);
        bunny3.setVisibility(View.INVISIBLE);
        bunny4.setVisibility(View.INVISIBLE);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chance = 5;
                left.setText("LEFT: " + chance);
                currentscore = 0;
                score.setText("SCORE: " + currentscore);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        theGameActions();
                    }
                }, 1000);

                btnStart.setEnabled(false);
                btnStart.setVisibility(View.INVISIBLE);
            }
        });


        bunny1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempifleft = 1;
                bunny1.setImageResource(R.drawable.bearshot);
                currentscore = currentscore + 1;
                score.setText("SCORE: " + currentscore);
                bunny1.setEnabled(false);
            }
        });


        bunny2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempifleft = 1;
                bunny2.setImageResource(R.drawable.bearshot);
                currentscore = currentscore + 1;
                score.setText("SCORE: " + currentscore);
                bunny2.setEnabled(false);
            }
        });


        bunny3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempifleft = 1;
                bunny3.setImageResource(R.drawable.bearshot);
                currentscore = currentscore + 1;
                score.setText("SCORE: " + currentscore);
                bunny3.setEnabled(false);
            }
        });


        bunny4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempifleft = 1;
                bunny4.setImageResource(R.drawable.bearshot);
                currentscore = currentscore + 1;
                score.setText("SCORE:  " + currentscore);
                bunny4.setEnabled(false);
            }
        });

    }


    //actions to take while the game is still on
    private void theGameActions() {
        if (currentscore < 10) {
            finalpt = 1000;
        } else if (currentscore >= 10 && currentscore < 15) {
            finalpt = 950;
        } else if (currentscore >= 15 && currentscore < 20) {
            finalpt = 900;
        } else if (currentscore >= 20 && currentscore < 25) {
            finalpt = 850;
        } else if (currentscore >= 25 && currentscore < 30) {
            finalpt = 800;
        } else if (currentscore >= 30 && currentscore < 35) {
            finalpt = 750;
        } else if (currentscore >= 35 && currentscore < 40) {
            finalpt = 700;
        } else if (currentscore >= 40 && currentscore < 45) {
            finalpt = 650;
        } else if (currentscore >= 45 && currentscore < 50) {
            finalpt = 600;
        } else if (currentscore >= 50 && currentscore < 55) {
            finalpt = 550;
        } else if (currentscore >= 55 && currentscore < 60) {
            finalpt = 500;
        } else if (currentscore >= 60 && currentscore < 65) {
            finalpt = 450;
        } else if (currentscore >= 65 && currentscore < 70) {
            finalpt = 400;
        } else if (currentscore >= 70 && currentscore < 75) {
            finalpt = 350;
        }

        ad = (AnimationDrawable) ContextCompat.getDrawable(this, R.drawable.anim);

        do{
            which = r.nextInt(4) + 1;

        } while (whichsave == which);
            whichsave = which;

        if(which ==1){
            bunny1.setImageDrawable(ad);
            bunny1.setVisibility(View.VISIBLE);
            bunny1.setEnabled(true);
        }

        else if (which == 2){
            bunny2.setImageDrawable(ad);
            bunny2.setVisibility(View.VISIBLE);
            bunny2.setEnabled(true);
        }

        else if (which == 3){
            bunny3.setImageDrawable(ad);
            bunny3.setVisibility(View.VISIBLE);
            bunny3.setEnabled(true);
        }

        else if (which == 4){
            bunny4.setImageDrawable(ad);
            bunny4.setVisibility(View.VISIBLE);
            bunny4.setEnabled(true);
        }

        ad.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bunny1.setVisibility(View.INVISIBLE);
                bunny2.setVisibility(View.INVISIBLE);
                bunny3.setVisibility(View.INVISIBLE);
                bunny4.setVisibility(View.INVISIBLE);

                bunny1.setEnabled(false);
                bunny2.setEnabled(false);
                bunny3.setEnabled(false);
                bunny4.setEnabled(false);


                if (tempifleft == 0) {
                    chance = chance - 1;
                    left.setText("LEFT: " + chance);
                } else if (tempifleft == 1) {
                    tempifleft = 0;
                }

                if (chance == 0) {

                    Intent myIntent = new Intent(BunnyActivity.this, BunnyGameOver.class);
                    startActivity(myIntent);
                    /*Toast.makeText(BunnyActivity.this, "GAME OVER", Toast.LENGTH_SHORT).show();*/
                    /*btnStart.setEnabled(true);*/

                }else if (chance >0){
                    theGameActions();
                }


            }
        } , finalpt);
    }
}

