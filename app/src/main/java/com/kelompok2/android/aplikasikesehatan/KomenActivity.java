package com.kelompok2.android.aplikasikesehatan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class KomenActivity extends AppCompatActivity {
    private EditText kom;
    private TextView ttitle;
    private TextView tpost;
    String title;
    String post;
    private Button posting;
    DatabaseReference databaseReference;
    String email;
    String judul;
    FirebaseUser mAuth;
//    PostModel komentar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komen);
        kom = (EditText) findViewById(R.id.postcomment);
        posting = (Button) findViewById(R.id.postbutton);
        Constant.currentUser = FirebaseAuth.getInstance().getCurrentUser();
        email = Constant.currentUser.getEmail();
//        email = mAuth.getEmail();
        Intent intent1 = getIntent(); //referensi variable
        judul = intent1.getStringExtra("judul1");
//        email = intent1.getStringExtra("email");
//        Constant.mAuth.getCurrentUser();
//        komentar = (PostModel) getIntent().getSerializableExtra("photoData1");//referensi variable dari intent
        databaseReference = FirebaseDatabase.getInstance().getReference("komentar/"+judul);

        posting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.push().setValue(new KomenModel(kom.getText().toString(),email.toString()));
//                KomenModel note = new KomenModel(kom.getText().toString(),email);
//                databaseReference.child(key).setValue(note);
                finish();
            }
        });
    }

//    @Override
//    protected void onStart() {
//
//        super.onStart();
//
//        if (mAuth == null) {
//            login();
//        }
//    }
//
//    private void login() {
//        Intent intent = new Intent(KomenActivity.this, MainActivity.class);
//        startActivity(intent);
//        finish();
//    }
}
