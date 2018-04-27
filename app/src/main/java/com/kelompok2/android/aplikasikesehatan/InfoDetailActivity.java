package com.kelompok2.android.aplikasikesehatan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoDetailActivity extends AppCompatActivity {
    private ImageView level;
    private TextView levelIndicator, levelIndicator1, levelIndicator2, levelIndicator3;
    private String nama,desk,pengobatan,penyebab,gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_detail);
        levelIndicator = (TextView)findViewById(R.id.titleDetail);
        levelIndicator1 = (TextView)findViewById(R.id.deskripsi1);
        levelIndicator2 = (TextView)findViewById(R.id.penyebab);
        levelIndicator3 = (TextView)findViewById(R.id.pengobatan);

        Intent intent = getIntent();
        nama = intent.getStringExtra("judul");
        desk = intent.getStringExtra("desk");
        penyebab = intent.getStringExtra("penyebab");
        pengobatan = intent.getStringExtra("pengobatan");
        gambar = intent.getStringExtra("image");

        levelIndicator.setText(nama);
        levelIndicator1.setText(desk);
        levelIndicator2.setText(penyebab);
        levelIndicator3.setText(pengobatan);

        startActivity(intent);
    }
}
