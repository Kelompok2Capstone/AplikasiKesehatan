package com.kelompok2.android.aplikasikesehatan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostDetailActivity extends AppCompatActivity {
    @BindView(R.id.tvDescription) //@BindView declare sekaligus inisialisasi view dengan menggunakan library ButterKnife
            TextView tvDescription;
    //    @BindView(R.id.rvKomentar) //@BindView declare sekaligus inisialisasi view dengan menggunakan library ButterKnife
//            RecyclerView rvKomentar;
//    @BindView(R.id.etKomentar) //@BindView declare sekaligus inisialisasi view dengan menggunakan library ButterKnife
//            TextInputEditText etKomentar;
//    @BindView(R.id.btnKirim) //@BindView declare sekaligus inisialisasi view dengan menggunakan library ButterKnife
//            Button btnKirim;
//    @BindView(R.id.toolbar) //@BindView declare sekaligus inisialisasi view dengan menggunakan library ButterKnife
//            android.support.v7.widget.Toolbar toolbar;
    RecyclerView.Adapter mAdapter;
    public final static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public static FirebaseUser currentUser;
    DatabaseReference databaseReference;
    private String judul;
    private String useremail;
    private String userKomen;
    private String desk;
    private TextView tjudul;
    private TextView tdesk;
    private ImageView icomment;
    private ImageView ithump;
    private TextView tvuser;
    List<KomenModel> komenList = new ArrayList<>();
    RecyclerView recyclerView;
    FirebaseUser user;
//    PostModel komentar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        ButterKnife.bind(this); //Binding ButterKnife pada activity ini
        tjudul = (TextView) findViewById(R.id.tvjudul);
        tdesk = (TextView) findViewById(R.id.tvDescription);
        icomment = (ImageView) findViewById(R.id.comment);
        ithump = (ImageView) findViewById(R.id.thump);
        tvuser = (TextView) findViewById(R.id.tvuser);
        recyclerView = (RecyclerView) findViewById(R.id.rvKomentar); //referensi variable
        Constant.currentUser = FirebaseAuth.getInstance().getCurrentUser();
        // Setting RecyclerView size true.
        recyclerView.setHasFixedSize(true); //referensi variable

        // Setting RecyclerView layout as LinearLayout.
        recyclerView.setLayoutManager(new LinearLayoutManager(PostDetailActivity.this));
        Intent intent1 = getIntent(); //referensi variable
        desk = intent1.getStringExtra("desk"); //referensi variable dari intent
        judul = intent1.getStringExtra("judul");
//        komentar = (PostModel) getIntent().getSerializableExtra("photoData");//referensi variable dari intent
//        databaseReference = FirebaseDatabase.getInstance().getReference("komen/"+nama); //referensi variable dari intent
        useremail = intent1.getStringExtra("uri"); //referensi variable dari intent
//        userKomen = Constant.currentUser.getEmail();
        tdesk.setText(desk);
        tjudul.setText(judul);
        tvuser.setText(useremail);
        icomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostDetailActivity.this, KomenActivity.class);

                intent.putExtra("judul1", judul);
//                intent.putExtra("photoData1", komentar);
                startActivity(intent);
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("komentar/" + judul);
        databaseReference.addValueEventListener(new ValueEventListener() {//eksekusi data dari firebase
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                komenList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) { //foreach memanggil data

                    KomenModel imageUploadInfo = postSnapshot.getValue(KomenModel.class); //get data dari firebase
                    //Toast.makeText(DetailGambar.this, imageUploadInfo.getUserKomen(), Toast.LENGTH_LONG).show();
                    komenList.add(imageUploadInfo); //add data ke listviews
                }

                mAdapter = new KomenAdapter(PostDetailActivity.this, komenList);//set adapter

                recyclerView.setAdapter(mAdapter); //set adapter

                // Hiding the progress dialog.

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Hiding the progress dialog.
            }
        });
    }

    @Override
    protected void onStart() {

        super.onStart();
        Constant.mAuth.getCurrentUser();
        if (Constant.mAuth == null) {
            login();
        }
    }

    private void login() {
        Intent intent = new Intent(PostDetailActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}


