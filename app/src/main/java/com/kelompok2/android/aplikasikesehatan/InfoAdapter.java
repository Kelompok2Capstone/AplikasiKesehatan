package com.kelompok2.android.aplikasikesehatan;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by User on 28/04/2018.
 */

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {
    private ArrayList<InfoModel> mPenyakitData;
    private Context mContext;

    InfoAdapter(Context context, ArrayList<InfoModel> penyakitData){
        this.mPenyakitData = penyakitData;
        this.mContext = context;

//        mGradient = new GradientDrawable();
//        mGradient.setColor(Color.GRAY);

//        Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.Batuk);
//        if (drawable!=null){
//            mGradient.setSize(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//        }
    }

    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InfoViewHolder(mContext, LayoutInflater.from(mContext).inflate(R.layout.info_item, parent, false));
    }

    @Override
    public void onBindViewHolder(InfoAdapter.InfoViewHolder holder, int position) {
        InfoModel currentPenyakit = mPenyakitData.get(position);

//        holder.bindTo(currentPenyakit);
        holder.mTitle.setText(currentPenyakit.getNama());
        String url = currentPenyakit.getImage();
        holder.deskripsi.setText(currentPenyakit.getDeskripsi());
        holder.penyebab.setText(currentPenyakit.getPenyebab());
        holder.pengobatan.setText(currentPenyakit.getPengobatan());
        holder.jenis.setText(currentPenyakit.getPengobatan());

        Glide.with(mContext).load(url).into(holder.mPenyakitImage);

    }

    @Override
    public int getItemCount() {
        return mPenyakitData.size();
    }
    class InfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView mPenyakitImage;
        private TextView mTitle;
        private TextView deskripsi, penyebab, pengobatan, jenis;
        private Context mContext;
        private InfoModel mCurrentPenyakit;

        private String txtTitle;

        InfoViewHolder(Context context, View itemView){
            super(itemView);

            mPenyakitImage = (ImageView) itemView.findViewById(R.id.penyakitImage);
            mTitle = (TextView) itemView.findViewById(R.id.labelTitle);
            deskripsi = (TextView)itemView.findViewById(R.id.desk);
            penyebab = (TextView)itemView.findViewById(R.id.penyebab);
            pengobatan = (TextView)itemView.findViewById(R.id.pengobatan);
            jenis = (TextView)itemView.findViewById(R.id.jenis);
//            mInfo = (TextView) itemView.findViewById(R.id.infoTitle);

            mContext = context;


            itemView.setOnClickListener(this);

        }

/*        void bindTo(Penyakit currentPenyakit){
            mTitle.setText(currentPenyakit.getNama());


            mCurrentPenyakit = currentPenyakit;
            txtTitle = mTitle.getText().toString();
            Glide.with(mContext).load(currentPenyakit.getImage()).into(mPenyakitImage);
        }*/

        @Override
        public void onClick(View view) {
            Intent intent1 = new Intent(view.getContext(), InfoDetailActivity.class);
            int mPosition = getLayoutPosition();
// Use that to access the affected item in mWordList.
            String element = mPenyakitData.get(mPosition).toString();
            String nama = mPenyakitData.get(mPosition).getNama();
            String pengobatan = mPenyakitData.get(mPosition).getPengobatan();
            String desk = mPenyakitData.get(mPosition).getDeskripsi();
            String penyebab = mPenyakitData.get(mPosition).getPenyebab();
            String jenis = mPenyakitData.get(mPosition).getJenis();
            String gambar = mPenyakitData.get(mPosition).getImage();
//            intent1.putExtra("photoData", UploadInfo);
            intent1.putExtra("judul",nama);
            intent1.putExtra("desk",desk);
            intent1.putExtra("pengobatan",pengobatan);
            intent1.putExtra("penyebab" ,penyebab);
            intent1.putExtra("jenis" ,jenis);
            intent1.putExtra("image", gambar);
            view.getContext().startActivity(intent1);
        }
    }
}
