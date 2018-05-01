package com.kelompok2.android.aplikasikesehatan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ForumActivity extends AppCompatActivity {
    // Creating button.
    // Creating DatabaseReference.
    DatabaseReference databaseReference;

    // Creating RecyclerView.
    RecyclerView recyclerView;

    // Creating RecyclerView.Adapter.
    RecyclerView.Adapter adapter ;

    // Creating Progress dialog
    ProgressDialog progressDialog;

    // Creating List of ImageUploadInfo class.
    List<PostModel> list = new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // Setting RecyclerView size true.
        recyclerView.setHasFixedSize(true);

        // Setting RecyclerView layout as LinearLayout.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        // Assign activity this to progress dialog.
        progressDialog = new ProgressDialog(this);

        // Setting up message in Progress dialog.
        progressDialog.setMessage("Loading Images From Firebase.");

        // Showing progress dialog.
        progressDialog.show();
        Constant.currentUser = FirebaseAuth.getInstance().getCurrentUser();

        // Setting up Firebase image upload folder path in databaseReference.
        // The path is already defined in MainActivity.
        databaseReference = FirebaseDatabase.getInstance().getReference("postingan");

        // Adding Add Value Event Listener to databaseReference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //list.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    PostModel imageUploadInfo = postSnapshot.getValue(PostModel.class);
                    list.add(imageUploadInfo);
                }

                adapter = new RecyclerViewAdapter(ForumActivity.this, list);

                recyclerView.setAdapter(adapter);

                // Hiding the progress dialog.
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Hiding the progress dialog.
                progressDialog.dismiss();

            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForumActivity.this, AddPostActivity.class);
                startActivity(intent);
            }
        });

//       Constant.mAuth.getCurrentUser(); //referensi variable

       //Constant.currentUser.getEmail();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout_Btn:
                logout();
            case R.id.action_setting_Btn:
                profil();
        }

        return super.onOptionsItemSelected(item);
    }

    private void login() {
        Intent intent = new Intent(ForumActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void logout() {
        Constant.mAuth.signOut(); //logout firebase
        Constant.currentUser = null;
        login();
    }

    private void profil() {
        Intent intent2 = new Intent(ForumActivity.this, ProfilActivity.class);
        startActivity(intent2);
        finish();
    }

    @Override
    protected void onStart() {

        super.onStart();
        Constant.mAuth.getCurrentUser();
        if (Constant.mAuth == null){
            login();
        }

    }
}