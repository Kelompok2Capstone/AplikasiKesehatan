package com.kelompok2.android.aplikasikesehatan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;

import butterknife.BindView;

public class AddPostActivity extends AppCompatActivity implements View.OnClickListener {

    //Deklarasi View
    @BindView(R.id.btnPost) //@BindView declare sekaligus inisialisasi view dengan menggunakan library ButterKnife
    FloatingActionButton btnPost;
    @BindView(R.id.tvTitle) //@BindView declare sekaligus inisialisasi view dengan menggunakan library ButterKnife
    TextInputEditText tvTitle;
    @BindView(R.id.tvPost) //@BindView declare sekaligus inisialisasi view dengan menggunakan library ButterKnife
    TextInputEditText tvPost;
    private StorageReference refPhotoProfile;
    private Uri photoUrl;
    private ProgressDialog pbDialog;
    public final static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public static FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        btnPost.setOnClickListener(this);
        pbDialog = new ProgressDialog(this);
    }

    @Override
    protected void onStart () {

        super.onStart();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null){
            login();
        }
    }

    private void login() {
        Intent intent = new Intent(AddPostActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPost:
                //validasi kosong
                if(tvTitle.getText().toString().isEmpty()) {
                    tvTitle.setError("Required");
                    return;
                }
                //validasi kosong
                if(tvPost.getText().toString().isEmpty()) {
                    tvPost.setError("Required");
                    return;
                }

                pbDialog.setMessage("Uploading..");
                pbDialog.setIndeterminate(true);
                pbDialog.show();

                String key = Constant.mypost.push().getKey(); //ambil key dari node firebase

                //push atau insert data ke firebase database
                Constant.mypost.child(key).setValue(new PostModel(
                        key,
                        photoUrl.toString(),
                        tvTitle.getText().toString(),
                        tvPost.getText().toString(),
                        currentUser.getEmail().split("@")[0],
                        currentUser.getEmail()));
                pbDialog.dismiss();
                Toast.makeText(AddPostActivity.this, "Uploaded!", Toast.LENGTH_SHORT).show();
        }
    }
}
