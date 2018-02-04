package com.example.ayushib.hangmangame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HangmanCategory extends AppCompatActivity implements View.OnClickListener{
    private AlertDialog helpAlert;
    Button btnbasic;
    Button btnanimals;
    Button btnfood;
    Button btnplaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman_category);

        btnbasic = (Button) findViewById(R.id.btnbasic);
        btnfood = (Button) findViewById(R.id.btnfood);
        btnplaces = (Button) findViewById(R.id.btnplaces);
        btnanimals = (Button) findViewById(R.id.btnplaces);


        btnbasic.setOnClickListener(this);
        btnfood.setOnClickListener(this);
        btnplaces.setOnClickListener(this);
        btnanimals.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnbasic:
                Intent i = new Intent(HangmanCategory.this, HangmanGameActivity.class);
                startActivity(i);
                break;
            case R.id.btnfood:
                Intent i2 = new Intent(HangmanCategory.this, HangmanFoodActivity.class);
                startActivity(i2);
                break;
            case R.id.btnplaces:
                Intent i3 = new Intent(HangmanCategory.this, HangmanPlaceActivity.class);
                startActivity(i3);
                break;
            default:
                Intent i4 = new Intent(HangmanCategory.this, HangmanAnimalActivity.class);
                startActivity(i4);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hangman, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_help:
                showHelp();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void showHelp() {
        AlertDialog.Builder helpBuild = new AlertDialog.Builder(this);

        helpBuild.setTitle("Help");
        helpBuild.setMessage("Guess the word by selecting the letters for each category.\n"
                + "You only have 6 wrong selections then it's game over! \n\n"
                + "Examples of words for basic things: Computer, Teddy, Pen \n\n"
                + "Examples of words for food: Apple, Noodles, Carrot \n\n"
                + "Examples of words for animals: Shark, Mosquito, Bees \n\n"
                + "Examples of words for places: Port-Louis, Moka, Vacoas");
        helpBuild.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        helpAlert.dismiss();
                    }});
        helpAlert = helpBuild.create();

        helpBuild.show();
    }
}
