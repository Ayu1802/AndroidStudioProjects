package com.example.ayushib.anagramgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class AnagramActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView wordTv;
    private EditText wordEnteredTv;
    private Button validate, newword;
    private String wordToFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anagram);

        wordTv = (TextView) findViewById(R.id.wordTv);
        wordEnteredTv = (EditText) findViewById(R.id.wordEnteredEt);
        validate = (Button) findViewById(R.id.validate);
        validate.setOnClickListener(this);
        newword = (Button) findViewById(R.id.newword);
        newword.setOnClickListener(this);

        newword();
    }

    @Override
    public void onClick(View view) {
        if (view == validate) {
            validate();
        } else if (view == newword) {
            newword();
        }
    }

    private void validate() {
        String w = wordEnteredTv.getText().toString();

        if (wordToFind.equals(w)) {
            Toast.makeText(this, "Congratulations ! You found the word " + wordToFind, Toast.LENGTH_SHORT).show();
            newword();
        } else {
            Toast.makeText(this, "Wrong ! The word was " + wordToFind, Toast.LENGTH_SHORT).show();
            newword();
        }
    }

    private void newword() {
        wordToFind = Anagram.randomWord();
        String wordShuffled = Anagram.shuffleWord(wordToFind);
        wordTv.setText(wordShuffled);
        wordEnteredTv.setText("");
    }
}
