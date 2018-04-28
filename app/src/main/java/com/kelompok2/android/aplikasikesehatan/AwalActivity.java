package com.kelompok2.android.aplikasikesehatan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AwalActivity extends AppCompatActivity {
   // private Toolbar maintoolbar;
    private Button logout;
    private Button share;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal);

//        share = (Button) findViewById(R.id.buttonshare);
//        logout = (Button) findViewById(R.id.button3);
        //maintoolbar = (Toolbar) findViewById(R.id.toolbar2);
       // setSupportActionBar(maintoolbar);

        //getSupportActionBar().setTitle("Photo Blog");


    }

    @Override
    protected void onStart() {

        super.onStart();
        Constant.mAuth.getCurrentUser();
        if (Constant.mAuth == null){
            login();
        }

    }



    private void login() {
        Intent intent = new Intent(AwalActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void logout() {
        Constant.mAuth.signOut(); //logout firebase
        Constant.currentUser = null;
        login();
    }

    public void forum(View view) {
        Intent intent1 = new Intent(AwalActivity.this, ForumActivity.class);
        startActivity(intent1);
        finish();

    }

    private void profil() {
        Intent intent2 = new Intent(AwalActivity.this, ProfilActivity.class);
        startActivity(intent2);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu); // inflate atau memasukkan menu
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout_Btn:
                logout();
            case R.id.action_setting_Btn:
                profil();
        }
        return super.onOptionsItemSelected(item);
    }

    public void info(View view) {
        Intent intent3 = new Intent(AwalActivity.this, InfoActivity.class);
        startActivity(intent3);
        finish();
    }

    public void fragment(View view) {
        Intent intent4 = new Intent(AwalActivity.this, DietActivity.class);
        startActivity(intent4);
        finish();
    }
}
