package com.example.ayushib.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        private Button btnRegister;
        private EditText editTextEmail;
        private EditText editTextPassword;
        private TextView textViewSignIn;
        private ProgressDialog progressDialog;

        private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){

                finish();
                startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
        }

        progressDialog = new ProgressDialog(this);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignIn = (TextView) findViewById(R.id.textViewSignIn);

        btnRegister.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);
    }

        private void registerUser(){
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();


            if(TextUtils.isEmpty(email)){
                //email empty
                Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                return; //stop function from executing further
            }
            if(TextUtils.isEmpty(password)){
                //password empty
                Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                return;
            }

                //if validated
            progressDialog.setMessage("Registering User...");
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
                    } else{
                        Toast.makeText(MainActivity.this, "Fail to Register. Please Try again!", Toast.LENGTH_SHORT).show();

                    }
                    progressDialog.dismiss();
                }
            });

        }
    @Override
    public void onClick(View view) {
        if (view == btnRegister){
            registerUser();
        }

        if(view == textViewSignIn){
            startActivity(new Intent(this, loginActivity.class));
        }
    }
}
