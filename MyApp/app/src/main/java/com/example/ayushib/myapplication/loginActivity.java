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

public class loginActivity extends AppCompatActivity implements View.OnClickListener {

        private Button btnLogin;
        private EditText editTextEmail;
        private EditText editTextPassword;
        private TextView textViewregister;
        private ProgressDialog progressDialog;

        private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
        }


        btnLogin = (Button) findViewById(R.id.btnLogin);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewregister = (TextView) findViewById(R.id.textViewregister);

        progressDialog = new ProgressDialog(this);

        btnLogin.setOnClickListener(this);
        textViewregister.setOnClickListener(this);

    }


    private void userLogin(){
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
        progressDialog.setMessage("Login User...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Toast.makeText(loginActivity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(), NavigationActivity.class));//start profile activity
                } else{
                    Toast.makeText(loginActivity.this, "Fail to Login. Please Try again!", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });

    }

    @Override
    public void onClick(View view) {
        if(view == btnLogin){
            userLogin();
        }

        if(view == textViewregister){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
