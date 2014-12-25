package com.kevinpelgrims.pictureplayground.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevinpelgrims.pictureplayground.R;
import com.kevinpelgrims.pictureplayground.model.Picture;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PicturesAdapter extends RecyclerView.Adapter<PicturesAdapter.PictureViewHolder> {
    private Context context;
    private List<Picture> pictures;

    public static class PictureViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name, description;

        public PictureViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.picture_image);
            name = (TextView) itemView.findViewById(R.id.picture_name);
            description = (TextView) itemView.findViewById(R.id.picture_description);
        }
    }

    public PicturesAdapter(Context context, List<Picture> pictures) {
        this.context = context;
        this.pictures = pictures != null ? pictures : new ArrayList<Picture>();
    }

    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.list_item_picture, parent, false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PictureViewHolder holder, int position) {
        Picture picture = pictures.get(position);
        Picasso.with(context).load(picture.url).into(holder.image);
        holder.name.setText(picture.name);
        holder.description.setText(picture.description);
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }
}
