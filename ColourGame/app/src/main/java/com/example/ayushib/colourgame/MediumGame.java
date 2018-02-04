package com.example.ayushib.colourgame;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ayushib.colourgame.util.BetterColour;

import java.util.ArrayList;
import java.util.Random;

public class MediumGame extends ColourGameActivity {

    private Button topBtn, bottomBtn, middleBtn;
    private ArrayList<Button> buttonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_game);


        setupProgressView();

        POINT_INCREMENT = 4;
        TIMER_BUMP = 2;

        gameMode = GameMode.MEDIUM;

        topBtn = (Button) findViewById(R.id.top_button);
        bottomBtn = (Button) findViewById(R.id.bottom_button);
        middleBtn = (Button) findViewById(R.id.middle_button);
        topBtn.setOnClickListener(this);
        bottomBtn.setOnClickListener(this);
        middleBtn.setOnClickListener(this);

        buttonList = new ArrayList<Button>();
        buttonList.add(topBtn);
        buttonList.add(bottomBtn);
        buttonList.add(middleBtn);

        resetGame();
        setupGameLoop();
        startGame();
    }

    @Override
    protected void setColorsOnButtons() {
        int color  = Color.parseColor(BetterColour.getColor());
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int[] alphas = shuffledColors();

        for (int i = 0; i < alphas.length; i++) {
            Button button = buttonList.get(i);
            button.setBackgroundColor(Color.argb(alphas[i], red, green, blue));
        }
    }

    @Override
    protected void calculatePoints(View clickedView) {
        ColorDrawable clickedColor = (ColorDrawable) clickedView.getBackground();
        int clickedAlpha = Color.alpha(clickedColor.getColor());

        int lightestColor = clickedAlpha;
        for (Button button : buttonList) {
            ColorDrawable color = (ColorDrawable) button.getBackground();
            int alpha = Color.alpha(color.getColor());
            if (alpha < lightestColor) {
                lightestColor = alpha;
            }
        }

        // correct guess
        if (lightestColor == clickedAlpha) {
            updatePoints();
        } else {
            // false - hard mode
            endGame();
        }

    }

    @Override
    public void onClick(View view) {
        if (!gameStart) return;
        calculatePoints(view);
        setColorsOnButtons();
    }

    // Fisher Yates shuffling algorithm
    private int[] shuffledColors() {
        Random random = new Random();
        int[] arr = {255, 185, 155 };
        for (int i = arr.length - 1; i >= 1; i--) {
            int j = random.nextInt(i);
            // swap i and j
            int tmp;
            tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }
}
