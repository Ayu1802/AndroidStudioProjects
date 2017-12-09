package com.example.ayushib.memorygame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    TextView p1, p2;
    ImageView question1, question2, question3, question4, question11, question12, question13, question14,
            question21,question22,question23,question24;

    //array for images
    Integer[] cardsArray = {101, 102, 103, 104, 105, 106, 201, 202, 203, 204, 205, 206};

    //actual images
    int image101, image102, image103, image104, image105, image106,
            image201, image202, image203, image204, image205, image206;


    int firstcard, secondcard;
    int clickedFirst, clickedSecond;
    int cardNumber = 1;

    int turn = 1;
    int playerPoints = 0, cpuPoints = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        p1 = (TextView) findViewById(R.id.p1);
        p2 = (TextView) findViewById(R.id.p2);


        question1 = (ImageView) findViewById(R.id.question1);
        question2 = (ImageView) findViewById(R.id.question2);
        question3 = (ImageView) findViewById(R.id.question3);
        question4 = (ImageView) findViewById(R.id.question4);

        question11 = (ImageView) findViewById(R.id.question11);
        question12 = (ImageView) findViewById(R.id.question12);
        question13 = (ImageView) findViewById(R.id.question13);
        question14 = (ImageView) findViewById(R.id.question14);

        question21 = (ImageView) findViewById(R.id.question21);
        question22 = (ImageView) findViewById(R.id.question22);
        question23 = (ImageView) findViewById(R.id.question23);
        question24 = (ImageView) findViewById(R.id.question24);


        question1.setTag("0");
        question2.setTag("1");
        question3.setTag("2");
        question4.setTag("3");

        question11.setTag("4");
        question12.setTag("5");
        question13.setTag("6");
        question14.setTag("7");

        question21.setTag("8");
        question22.setTag("9");
        question23.setTag("10");
        question24.setTag("11");

        //load card images
        frontOfCardsResources();

        //shuffle the images
        Collections.shuffle(Arrays.asList(cardsArray));

        p2.setTextColor(Color.GRAY);

        question1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(question1, theCard);
            }
        });

        question2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(question2, theCard);
            }
        });

        question3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(question3, theCard);
            }
        });


        question4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(question4, theCard);
            }
        });


        question11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(question11, theCard);
            }
        });



        question12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(question12, theCard);
            }
        });


        question13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(question13, theCard);
            }
        });


        question14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(question14, theCard);
            }
        });

        question21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(question21, theCard);
            }
        });


        question22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(question22, theCard);
            }
        });


        question23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(question23, theCard);
            }
        });



        question24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                doStuff(question24, theCard);
            }
        });

    }

    private void doStuff(ImageView iv, int card){

        //set correct image to image view
        if (cardsArray[card] == 101){
            iv.setImageResource(image101);
        }
        else if(cardsArray[card] == 102){
            iv.setImageResource(image102);
        }
        else if(cardsArray[card] == 103){
            iv.setImageResource(image103);
        }
        else if(cardsArray[card] == 104){
            iv.setImageResource(image104);
        }
        else if(cardsArray[card] == 105){
            iv.setImageResource(image105);
        }
        else if(cardsArray[card] == 106){
            iv.setImageResource(image106);
        }



        else if(cardsArray[card] == 201){
            iv.setImageResource(image201);
        }
        else if(cardsArray[card] == 202){
            iv.setImageResource(image202);
        }
        else if(cardsArray[card] == 203){
            iv.setImageResource(image203);
        }
        else if(cardsArray[card] == 204){
            iv.setImageResource(image204);
        }
        else if(cardsArray[card] == 205){
            iv.setImageResource(image205);
        }
        else if(cardsArray[card] == 206){
            iv.setImageResource(image206);
        }


        //check which image is selected and save it to temporary variable
        if(cardNumber == 1) {
            firstcard = cardsArray[card];

            if (firstcard > 200) {
                firstcard = firstcard - 100;
            }

            cardNumber = 2;
            clickedFirst = card;

            iv.setEnabled(false);
        }

        else if (cardNumber == 2){
            secondcard = cardsArray[card];
            if (secondcard > 200) {
                secondcard = secondcard - 100;
            }

            cardNumber = 1;
            clickedSecond = card;


            question1.setEnabled(false);
            question2.setEnabled(false);
            question3.setEnabled(false);
            question4.setEnabled(false);

            question11.setEnabled(false);
            question12.setEnabled(false);
            question13.setEnabled(false);
            question14.setEnabled(false);

            question21.setEnabled(false);
            question22.setEnabled(false);
            question23.setEnabled(false);
            question24.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //check if selected images are equal
                    calculate();
                }
            }, 1000);
        }

    }

    private void calculate(){

        //if images are equal, remove them
        if (firstcard == secondcard){
            if (clickedFirst == 0){
                question1.setVisibility(View.INVISIBLE);
            }

            else if(clickedFirst == 1){
                question2.setVisibility(View.INVISIBLE);
            }

            else if(clickedFirst == 2){
                question3.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 3) {
                question4.setVisibility(View.INVISIBLE);
            }



            else if(clickedFirst == 4){
                question11.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 5){
                question12.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 6){
                question13.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 7){
                question14.setVisibility(View.INVISIBLE);
            }



            else if(clickedFirst == 8){
                question21.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 9){
                question22.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 10){
                question23.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst == 11){
                question24.setVisibility(View.INVISIBLE);
            }


            if (clickedSecond == 0){
                question1.setVisibility(View.INVISIBLE);
            }

            else if(clickedSecond == 1){
                question2.setVisibility(View.INVISIBLE);
            }

            else if(clickedSecond == 2){
                question3.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 3) {
                question4.setVisibility(View.INVISIBLE);
            }



            else if(clickedSecond == 4){
                question11.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 5){
                question12.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 6){
                question13.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 7){
                question14.setVisibility(View.INVISIBLE);
            }



            else if(clickedSecond == 8){
                question21.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 9){
                question22.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 10){
                question23.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond == 11){
                question24.setVisibility(View.INVISIBLE);
            }


            //add points to correct player
            if (turn ==1){
                playerPoints++;
                p1.setText("P1: " + playerPoints);
            } else if (turn == 2) {
                cpuPoints++;
                p2.setText("P2: " + cpuPoints);
            }
        } else {
            question1.setImageResource(R.drawable.question);
            question2.setImageResource(R.drawable.question);
            question3.setImageResource(R.drawable.question);
            question4.setImageResource(R.drawable.question);

            question11.setImageResource(R.drawable.question);
            question12.setImageResource(R.drawable.question);
            question13.setImageResource(R.drawable.question);
            question14.setImageResource(R.drawable.question);

            question21.setImageResource(R.drawable.question);
            question22.setImageResource(R.drawable.question);
            question23.setImageResource(R.drawable.question);
            question24.setImageResource(R.drawable.question);


            //change the palyer turn
            if (turn == 1){
                turn = 2;
                p1.setTextColor(Color.GRAY);
                p2.setTextColor(Color.BLACK);
            } else if(turn ==2){
                turn = 1;
                p1.setTextColor(Color.GRAY);
                p2.setTextColor(Color.BLACK);
            }

        }

        question1.setEnabled(true);
        question2.setEnabled(true);
        question3.setEnabled(true);
        question4.setEnabled(true);

        question11.setEnabled(true);
        question12.setEnabled(true);
        question13.setEnabled(true);
        question14.setEnabled(true);

        question21.setEnabled(true);
        question22.setEnabled(true);
        question23.setEnabled(true);
        question24.setEnabled(true);


        //check if game is over
        checkEnd();

    }

    private void checkEnd() {
        if (question1.getVisibility() == View.INVISIBLE &&
                question2.getVisibility() == View.INVISIBLE &&
                question3.getVisibility() == View.INVISIBLE &&
                question4.getVisibility() == View.INVISIBLE &&

                question11.getVisibility() == View.INVISIBLE &&
                question12.getVisibility() == View.INVISIBLE &&
                question13.getVisibility() == View.INVISIBLE &&
                question14.getVisibility() == View.INVISIBLE &&

                question21.getVisibility() == View.INVISIBLE &&
                question22.getVisibility() == View.INVISIBLE &&
                question23.getVisibility() == View.INVISIBLE &&
                question24.getVisibility() == View.INVISIBLE) {


            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder
                    .setMessage("GAME OVER!\nP1: " + playerPoints + "\nP2: " + cpuPoints)
                    .setCancelable(false)
                    .setPositiveButton("NEW", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }

    }
    private void  frontOfCardsResources() {
        image101 = R.drawable.dog;
        image102 = R.drawable.koala;
        image103 = R.drawable.monkey;
        image104 = R.drawable.pig;
        image105 = R.drawable.rabbit;
        image106 = R.drawable.tiger;
        image201 = R.drawable.dogcopy;
        image202 = R.drawable.koalacopy;
        image203 = R.drawable.monkeycopy;
        image204 = R.drawable.pigcopy;
        image205 = R.drawable.rabbitcopy;
        image206 = R.drawable.tigercopy;

    }
}
