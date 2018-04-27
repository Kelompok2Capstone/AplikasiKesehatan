package com.kelompok2.android.aplikasikesehatan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArrayList<InfoModel> mPenyakitData;
    private InfoAdapter mAdapter;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("penyakit");
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

//       int gridColumn = getResources().getInteger(R.integer.grid_column_count);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mPenyakitData = new ArrayList<>();
        mAdapter = new InfoAdapter(this, mPenyakitData);
        mRecyclerView.setAdapter(mAdapter);

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot post : dataSnapshot.getChildren()){
                    InfoModel model = post.getValue(InfoModel.class);
                    mPenyakitData.add(model);
                }
                mAdapter = new InfoAdapter(InfoActivity.this, mPenyakitData);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
