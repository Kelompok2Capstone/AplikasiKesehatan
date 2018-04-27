package com.kelompok2.android.aplikasikesehatan;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 24/04/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    String useremail;
    String url;
    Context context;
    List<PostModel> MainImageUploadInfoList;

    public RecyclerViewAdapter(Context context, List<PostModel> TempList) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        PostModel UploadInfo = MainImageUploadInfoList.get(position);
        holder.tUser.setText(UploadInfo.getEmail());
        holder.imageNameTextView.setText(UploadInfo.getDesc());
        holder.imageView.setText(UploadInfo.getTitle());

    }

    @Override
    public int getItemCount() {
        return MainImageUploadInfoList.size();
    }

    final class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tUser;
        public TextView imageView;
        public TextView imageNameTextView;
        public TextView Title;
//        String UploadInfo;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (TextView) itemView.findViewById(R.id.Title);
            tUser = (TextView) itemView.findViewById(R.id.user1);
            imageNameTextView = (TextView) itemView.findViewById(R.id.ImageNameTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
// Use that to access the affected item in mWordList.
            String element = MainImageUploadInfoList.get(mPosition).toString();
            String uri = MainImageUploadInfoList.get(mPosition).getEmail();
            String judul = MainImageUploadInfoList.get(mPosition).getTitle();
            String desk = MainImageUploadInfoList.get(mPosition).getDesc();
            Intent intent1 = new Intent(view.getContext(), PostDetailActivity.class);
//            intent1.putExtra("photoData", UploadInfo);
            intent1.putExtra("uri",uri);
            intent1.putExtra("judul",judul);
            intent1.putExtra("desk",desk);
            view.getContext().startActivity(intent1);
        }
    }
}
