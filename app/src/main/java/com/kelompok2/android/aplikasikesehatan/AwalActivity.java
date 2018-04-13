package com.kelompok2.android.aplikasikesehatan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AwalActivity extends AppCompatActivity {
   // private Toolbar maintoolbar;

    private FirebaseAuth mAuth;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal);
        mAuth = FirebaseAuth.getInstance();
        logout = (Button) findViewById(R.id.button3);
        //maintoolbar = (Toolbar) findViewById(R.id.toolbar2);
       // setSupportActionBar(maintoolbar);

        //getSupportActionBar().setTitle("Photo Blog");


    }



    @Override
    protected void onStart () {

        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null){
            login();
        }
    }

    private void login() {
        Intent intent = new Intent(AwalActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void keluar(View view) {

        logout();

    }

    private void logout() {
        mAuth.signOut();
        login();
    }
}
