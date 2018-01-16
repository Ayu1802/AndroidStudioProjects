package com.example.ayushib.tictactoe;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ayushib.tictactoe.databinding.ActivityTicTacToeBinding;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

public class TicTacToeActivity extends AppCompatActivity {

        private static final String LOG_TAG = "TicTacToeActivity";
        private ActivityTicTacToeBinding binding;
        private String withId;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = DataBindingUtil.setContentView(this, R.layout.activity_tic_tac_toe);
            Bundle extras = getIntent().getExtras();
            String type = extras.getString("type");
            if (type.equals("wifi")) {
                withId = extras.getString("withId");
                binding.canvas.setWifiWith(withId);
                String gameId = extras.getString("gameId");
                binding.canvas.setGameId(gameId);
                binding.canvas.setMe(extras.getString("me"));

                FirebaseMessaging.getInstance().subscribeToTopic("all");

                FirebaseDatabase.getInstance().getReference().child("games")
                        .child(gameId)
                        .setValue(null);
            }
        }
    }
