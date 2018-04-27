package com.kelompok2.android.aplikasikesehatan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import butterknife.BindView;

public class AddPostActivity extends AppCompatActivity {

    //Deklarasi View
    private Button btnPost;
    @BindView(R.id.tvTitle) //@BindView declare sekaligus inisialisasi view dengan menggunakan library ButterKnife
            EditText tvTitle;
    @BindView(R.id.tvPost) //@BindView declare sekaligus inisialisasi view dengan menggunakan library ButterKnife
            EditText tvPost;
    private StorageReference refPhotoProfile;
    private Uri photoUrl;
    private ProgressDialog pbDialog;
    DatabaseReference databaseReference;
    String email;
//    private FirebaseAuth mAuth;
//    public static FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        tvTitle = (EditText) findViewById(R.id.tvTitle);
        tvPost = (EditText) findViewById(R.id.tvPost);
        pbDialog = new ProgressDialog(this);
        btnPost = (Button) findViewById(R.id.btnPost);

        email = Constant.currentUser.getEmail().toString();
        databaseReference = FirebaseDatabase.getInstance().getReference("postingan");
//        mAuth = FirebaseAuth.getInstance();
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                switch (view.getId()) {
//                    case R.id.btnPost:
//                        //validasi kosong
//                        if (tvTitle.getText().toString().isEmpty()) {
//                            tvTitle.setError("Required");
//                            return;
//                        }
//                        //validasi kosong
//                        if (tvPost.getText().toString().isEmpty()) {
//                            tvPost.setError("Required");
//                            return;
//                        }
//                PostForum();
                
                        String key = databaseReference.push().getKey();
                        PostingModel note = new PostingModel(tvTitle.getText().toString(), tvPost.getText().toString(),email);
                        databaseReference.child(key).setValue(note);
                Intent intent = new Intent(AddPostActivity.this, ForumActivity.class);
                startActivity(intent);
                finish();

//                        tvTitle.setText("");
//                        tvPost.setText("");
                }

        });
        Constant.mAuth.getCurrentUser();
    }

//    private void PostForum() {
        //databaseReference = FirebaseDatabase.getInstance().getReference("postingan");
//        databaseReference.
//    }

//    @Override
//    protected void onStart() {
//
//        super.onStart();
//        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//        if (currentUser == null) {
//            login();
//        }
//    }
//
//    private void login() {
//        Intent intent = new Intent(AddPostActivity.this, MainActivity.class);
//        startActivity(intent);
//        finish();
//    }
}

