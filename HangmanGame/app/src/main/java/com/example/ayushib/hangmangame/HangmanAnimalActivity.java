package com.example.ayushib.hangmangame;

/**
 * Created by Ayushi B on 28-Jan-18.
 */
import java.util.Random;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.app.ActionBar;

public class HangmanAnimalActivity extends AppCompatActivity {

    private String[] animals;
    private Random rand;
    private String currWord;
    private LinearLayout wordLayout;
    private TextView[] charViews;
    private GridView letters;
    private LetterAdapter ltrAdapt;

    private ImageView[] bodyParts;   //body part images
    private int numParts=6;         //number of body parts
    private int currPart;           //current part - will increment when wrong answers are chosen
    private int numChars;           //number of characters in current word
    private int numCorr;            //number correctly guessed


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hangman_game);

        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        Resources res = getResources();

        // read the collection of words, and store them into the instance variable
        animals = res.getStringArray(R.array.animals);


        rand = new Random(); //application uses the rand object to select a word from the array each time
        currWord = ""; //a reference to the current word

        wordLayout = (LinearLayout)findViewById(R.id.word); //to hold the answer area
        letters = (GridView)findViewById(R.id.letters); //an array of text views for the letters

        bodyParts = new ImageView[numParts];
        bodyParts[0] = (ImageView)findViewById(R.id.head);
        bodyParts[1] = (ImageView)findViewById(R.id.body);
        bodyParts[2] = (ImageView)findViewById(R.id.arm1);
        bodyParts[3] = (ImageView)findViewById(R.id.arm2);
        bodyParts[4] = (ImageView)findViewById(R.id.leg1);
        bodyParts[5] = (ImageView)findViewById(R.id.leg2);

        playGame();
    }

    private void playGame() {


        String newWord = animals[rand.nextInt(animals.length)];

        //we make sure we don't pick the same word two times in a row.
        while (newWord.equals(currWord))  {
            newWord = animals[rand.nextInt(animals.length)];
        }
        currWord = newWord;

        //create one text view for each letter of the target word. Still inside our helper method,
        // create a new array to store the text views for the letters of the target word.
        charViews = new TextView[currWord.length()];

        wordLayout.removeAllViews();

        //The string is stored as an array of characters. The charAt method allows us to access the characters at a specific index.
        // Still inside the for loop, set the display properties on the text view and add it to the layout.
        for (int c = 0; c < currWord.length(); c++) {
            charViews[c] = new TextView(this);
            charViews[c].setText(""+currWord.charAt(c));

            charViews[c].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            charViews[c].setGravity(Gravity.CENTER);
            charViews[c].setTextColor(Color.TRANSPARENT);
            charViews[c].setBackgroundResource(R.drawable.letter_bg);

            //add to layout
            wordLayout.addView(charViews[c]);

        }
        ltrAdapt=new LetterAdapter(this);
        letters.setAdapter(ltrAdapt);

        currPart=0;
        numChars=currWord.length();
        numCorr=0;

        //the body parts need to be hidden
        for(int p = 0; p < numParts; p++) {
            bodyParts[p].setVisibility(View.INVISIBLE);
        }
    }

    public void letterPressed(View view) {
        //user has pressed a letter to guess
        String ltr=((TextView)view).getText().toString();
        char letterChar = ltr.charAt(0);


        view.setEnabled(false);
        view.setBackgroundResource(R.drawable.letter_down);


        boolean correct = false;
        for(int k = 0; k < currWord.length(); k++) {
            if (currWord.charAt(k)==letterChar){
                correct = true;
                numCorr++;
                charViews[k].setTextColor(Color.BLACK);
            }
        }

        if (correct) {
            //correct guess
            if (numCorr == numChars) {
                //user has won
                // Disable Buttons
                disableBtns();

                // Display Alert Dialog
                AlertDialog.Builder winBuild = new AlertDialog.Builder(this);
                winBuild.setTitle("Well done!");
                winBuild.setMessage("You won!\n\nThe answer was:\n\n"+currWord);
                winBuild.setPositiveButton("Play Again",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                HangmanAnimalActivity.this.playGame();
                            }});

                winBuild.setNegativeButton("Exit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                HangmanAnimalActivity.this.finish();
                            }});

                winBuild.show();
            }
        } else if (currPart < numParts) {
            //some guesses left
            bodyParts[currPart].setVisibility(View.VISIBLE);
            currPart++;
        } else {
            //user has lost
            disableBtns();

            // Display Alert Dialog
            AlertDialog.Builder loseBuild = new AlertDialog.Builder(this);
            loseBuild.setTitle("Oops!");
            loseBuild.setMessage("You lost!\n\nThe answer was:\n\n"+currWord);
            loseBuild.setPositiveButton("Play Again",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            HangmanAnimalActivity.this.playGame();
                        }});

            loseBuild.setNegativeButton("Exit",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            HangmanAnimalActivity.this.finish();
                        }});

            loseBuild.show();

        }
    }

    public void disableBtns() {
        int numLetters = letters.getChildCount();
        for (int l = 0; l < numLetters; l++) {
            letters.getChildAt(l).setEnabled(false);
        }
    }



}
