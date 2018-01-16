package com.example.ayushib.tictactoe;

/**
 * Created by Ayushi B on 10-Jan-18.
 */

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.example.ayushib.tictactoe.databinding.ActivityStartBinding;
import com.example.ayushib.tictactoe.model.User;
import com.example.ayushib.tictactoe.wifi.UserListActivity;

import static android.text.TextUtils.isEmpty;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static android.widget.Toast.LENGTH_SHORT;
import static com.example.ayushib.tictactoe.Util.getCurrentUserId;
import static com.example.ayushib.tictactoe.Util.savePushToken;

public class StartActivity  extends AppCompatActivity {
    private static final String LOG_TAG = "StartActivity";
    private ActivityStartBinding binding;
    private boolean logginIn;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_start);

    }

    public void startSingleMode(View view) {
        startActivity(new Intent(this, TicTacToeActivity.class)
                .putExtra("type", "single"));
    }

    public void startMultilayer(View view) {
        if (isAnonymous()) {
            binding.inputEmail.setVisibility(VISIBLE);
            binding.inputName.setVisibility(VISIBLE);
            binding.login.setVisibility(VISIBLE);
            binding.inputPassword.setVisibility(VISIBLE);
        } else {
//            startActivity(new Intent(this, MainActivity.class)
//                    .putExtra("type", "wifi"));

            startActivity(new Intent(this, UserListActivity.class));
        }
    }

    public void loginWithEmail(View view) {
        final String email = binding.inputEmail.getText().toString();
        final String name = binding.inputName.getText().toString();
        final String password = binding.inputPassword.getText().toString();
        if (logginIn
                || isEmpty(email)
                || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
                || isEmpty(password)
                || isEmpty(name)) {
            Toast.makeText(this, "Enter all fields", LENGTH_SHORT).show();
            return;
        }

        logginIn = true;
        showProgressDialog();

      firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(LOG_TAG, "loginWithEmail: ");
                            String uid = firebaseAuth.getCurrentUser().getUid();

                            User user = new User(name);
                            FirebaseDatabase.getInstance().getReference().child("users").child(uid)
                                    .setValue(user);

                            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
                            savePushToken(refreshedToken, uid);

//                        startActivity(new Intent(this, TicTacToeActivity.class)
//                                .putExtra("type", "wifi"));

                            startActivity(new Intent(getApplicationContext(), UserListActivity.class));

                        } else {
                            Log.d(LOG_TAG, "loginWithEmail: unsuccessful");
                            firebaseAuth.signInWithEmailAndPassword(email, password)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task1) {
                                            if (!StartActivity.this.isAnonymous()) {
                                                String refreshedToken = FirebaseInstanceId.getInstance().getToken();

                                                savePushToken(refreshedToken, getCurrentUserId());

//                                        startActivity(new Intent(this, TicTacToeActivity.class)
//                                                .putExtra("type", "wifi"));

                                                StartActivity.this.startActivity(new Intent(StartActivity.this, UserListActivity.class));

                                            }
                                        }
                                    });
                        }
                    }

                    ;



                });

    }

        public void showProgressDialog() {
            binding.progress.setVisibility(VISIBLE);
        }

        private void hideProgressDialog() {
            binding.progress.setVisibility(GONE);
        }

        private boolean isAnonymous() {
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            return currentUser == null || currentUser.isAnonymous();
        }
    }

