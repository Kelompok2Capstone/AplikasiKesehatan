package com.kelompok2.android.aplikasikesehatan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 26/04/2018.
 */

public class KomenAdapter extends RecyclerView.Adapter<KomenAdapter.ViewHolder> {
    String useremail; //inisiasi variable
    String url; //inisiasi variable
    Context context; //inisiasi variable
    List<KomenModel> MainImageUploadInfoList; //inisiasi variable

    public KomenAdapter(Context context, List<KomenModel> TempList) {

        this.MainImageUploadInfoList = TempList; //refrensi variable

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.komen_item, parent, false);  //refrensi variable

        ViewHolder viewHolder = new ViewHolder(view); //refrensi variable ke holder

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(KomenAdapter.ViewHolder holder, int position) {
        KomenModel UploadInfo = MainImageUploadInfoList.get(position);
        holder.komentarUSer.setText(UploadInfo.getKomen()); //inisiasi ke holder
        holder.namaUser.setText(UploadInfo.getEmail()); //inisiasi ke holder
    }

    @Override
    public int getItemCount() {
        return MainImageUploadInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView namaUser;
        public TextView komentarUSer;

        public ViewHolder(View itemView) {
            super(itemView);

            //imageView = (ImageView) itemView.findViewById(R.id.imageView);
            namaUser = (TextView) itemView.findViewById(R.id.namaUSer);
            komentarUSer = (TextView) itemView.findViewById(R.id.komentarUSer);

        }
    }
}
