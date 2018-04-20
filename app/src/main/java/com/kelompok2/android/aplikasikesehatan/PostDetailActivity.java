package com.kelompok2.android.aplikasikesehatan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostDetailActivity extends AppCompatActivity {
    @BindView(R.id.tvDescription) //@BindView declare sekaligus inisialisasi view dengan menggunakan library ButterKnife
            TextView tvDescription;
    @BindView(R.id.rvKomentar) //@BindView declare sekaligus inisialisasi view dengan menggunakan library ButterKnife
            RecyclerView rvKomentar;
    @BindView(R.id.etKomentar) //@BindView declare sekaligus inisialisasi view dengan menggunakan library ButterKnife
            TextInputEditText etKomentar;
    @BindView(R.id.btnKirim) //@BindView declare sekaligus inisialisasi view dengan menggunakan library ButterKnife
            Button btnKirim;
    @BindView(R.id.toolbar) //@BindView declare sekaligus inisialisasi view dengan menggunakan library ButterKnife
            android.support.v7.widget.Toolbar toolbar;
    private ArrayList<CommentModel> commentList; //arraylist untuk menyimpan hasil load komentar
    private CommentAdapter mAdapter;
    public final static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public static FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        ButterKnife.bind(this); //Binding ButterKnife pada activity ini
        setSupportActionBar(toolbar);
        displayHomeAsUpEnabled();
        commentList = new ArrayList<>();

        mAdapter = new CommentAdapter(commentList);
        rvKomentar.setAdapter(mAdapter);

        loadIntent();

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
        Intent intent = new Intent(PostDetailActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    PostModel photo;
    private void loadIntent() {
        tvDescription.setText(photo.getDesc() + "\nby: " + photo.getName());
        setTitle(photo.getTitle()); //set judul toolbar
        loadComment(); //load comment
    }
    private void loadComment() {
        Constant.mypost.child(photo.getKey()).child("commentList")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        commentList.clear();
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.

                        for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                            CommentModel model = ds.getValue(CommentModel.class);
                            commentList.add(model); //dimasukkan kedalam list
                            mAdapter.notifyDataSetChanged(); //refresh data
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("", "Failed to read value.", error.toException());
                        //showProgress(false);
                    }
                });
    }

    //menampilkan tombol back button diatas kiri
    private void displayHomeAsUpEnabled() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    //method untuk handling btnKirim
    @OnClick(R.id.btnKirim)
    public void kirim() {
        //validasi kosong
        if (etKomentar.getText().toString().isEmpty()) {
            Toast.makeText(this, "Harap isi komentar", Toast.LENGTH_SHORT).show();
            return;
        }

        //Insert atau push data komentar ke firebase
        Constant.mypost.child(photo.getKey()).child("commentList").push().setValue(new CommentModel(
                currentUser.getEmail().split("@")[0],
                currentUser.getEmail(),
                etKomentar.getText().toString()
        ));

        etKomentar.setText("");
    }

    //handler jika back button di klik
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
