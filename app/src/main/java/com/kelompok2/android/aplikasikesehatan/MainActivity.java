package com.kelompok2.android.aplikasikesehatan;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private EditText LUsername;
    private EditText LPassword;
    private Button LLogin;
    private ProgressBar loginprogress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LUsername = (EditText) findViewById(R.id.etUsername);
        LPassword = (EditText) findViewById(R.id.etPassword);
        LLogin = (Button) findViewById(R.id.button_login);
//        loginprogress= (ProgressBar) findViewById(R.id.progressBar3);

        if(Constant.mAuth.getCurrentUser() != null){

            // Finishing current Login Activity.
            finish();

            // Opening UserProfileActivity .
            Intent intent = new Intent(this, AwalActivity.class);
            startActivity(intent);
        }

    }


    @Override
    protected void onStart() {

        super.onStart();
        FirebaseUser curUser = Constant.mAuth.getCurrentUser();
        if (curUser != null){
            sendtoMain();
        }

    }

    private void sendtoMain() {
        Intent mainintent = new Intent(MainActivity.this, AwalActivity.class);
        startActivity(mainintent);
        finish();
    }


    public void launchRegisterActivity(View view) {



        signIn();



    }
    public void signIn(){

        String LEmail = LUsername.getText().toString();
        String LPass = LPassword.getText().toString();
        Constant.mAuth.signInWithEmailAndPassword(LEmail, LPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            loginprogress.setVisibility(View.VISIBLE);
                            Log.d("", "signInWithEmail:success");
                            FirebaseUser curUser = Constant.mAuth.getCurrentUser(); //ambil informasi user yang login
                            Constant.currentUser = curUser; //set di variabel global
                            startActivity(new Intent(MainActivity.this, AwalActivity.class)); //panggil activity main
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Akun belum terdaftar",
                                    Toast.LENGTH_SHORT).show();
//                            loginprogress.setVisibility(View.INVISIBLE);
                            //showProgress(false);
                        }
                    }
                });

    }

    public void buatakunactivity(View view) {
        Intent akun = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(akun);
    }
}
