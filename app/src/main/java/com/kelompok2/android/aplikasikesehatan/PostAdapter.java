package com.kelompok2.android.aplikasikesehatan;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 21/04/2018.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    //deklarasi variable
    private List<PostModel> photoList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvDesc, tvTitle, tvComment;
        public ImageView imgPhoto;
        public CardView cvPhoto;

        public MyViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tvNama);
            tvDesc = (TextView) view.findViewById(R.id.tvDeskripsi);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            tvComment = (TextView) view.findViewById(R.id.tvComment);
            cvPhoto = (CardView) view.findViewById(R.id.cvPhoto);
        }
    }

    public PostAdapter(List<PostModel> photoList, Context context) {
        this.photoList = photoList;
        this.context = context;
    }

    @Override
    public PostAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_post, parent, false);

        return new MyViewHolder(itemView);
    }
    int comment = 0;

    @Override
    public void onBindViewHolder(final PostAdapter.MyViewHolder holder, int position) {
        final PostModel photo = photoList.get(position);
        holder.tvDesc.setText(photo.getDesc());
        holder.tvName.setText(photo.getEmail());
        holder.tvTitle.setText(photo.getTitle());

        //mengambil data jumlah komentar setiap photo
//        Constant.mypost.child(photo.getKey()).child("commentList")
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        comment = 0;
//                        // This method is called once with the initial value and again
//                        // whenever data at this location is updated.
//
//                        for (final DataSnapshot ds : dataSnapshot.getChildren()) {
//                            CommentModel model = ds.getValue(CommentModel.class);
//                            comment++;
//                        }
//                        holder.tvComment.setText(comment + "");
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError error) {
//                        // Failed to read value
//                        Log.w("", "Failed to read value.", error.toException());
//                        //showProgress(false);
//                    }
//                });
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }
}
