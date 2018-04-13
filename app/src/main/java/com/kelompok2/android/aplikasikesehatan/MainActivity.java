package com.kelompok2.android.aplikasikesehatan;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private EditText LUsername;
    private EditText LPassword;
    private Button LLogin;
    private FirebaseAuth mAuth;
    private ProgressBar loginprogress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        LUsername = (EditText) findViewById(R.id.etUsername);
        LPassword = (EditText) findViewById(R.id.etPassword);
        LLogin = (Button) findViewById(R.id.button_login);
        loginprogress= (ProgressBar) findViewById(R.id.progressBar3);

    }


    @Override
    protected void onStart() {

        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            sendtoMain();
        }

    }

    private void sendtoMain() {
        Intent mainintent = new Intent(MainActivity.this, AwalActivity.class);
        startActivity(mainintent);
        finish();
    }


    public void launchRegisterActivity(View view) {


        String LEmail = LUsername.getText().toString();
        String LPass = LPassword.getText().toString();

        if (!TextUtils.isEmpty(LEmail) && !TextUtils.isEmpty(LPass))
            loginprogress.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(LEmail, LPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        sendtoMain();

                    } else {
                        String error = task.getException().getMessage();
                        Toast.makeText(MainActivity.this, "Error : " + error, Toast.LENGTH_LONG ).show();
                    }

                    loginprogress.setVisibility(View.INVISIBLE);
                }
            });

    }

    public void buatakunactivity(View view) {
        Intent akun = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(akun);
    }
}
