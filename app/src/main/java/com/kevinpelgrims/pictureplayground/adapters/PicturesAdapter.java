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

import java.util.ArrayList;
import java.util.List;

public class PicturesAdapter extends RecyclerView.Adapter<PicturesAdapter.ViewHolder> {
    private Context context;
    private List<Picture> pictures;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name, description;

        public ViewHolder(View itemView) {
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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.list_item_picture, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picture picture = pictures.get(position);
        holder.name.setText(picture.name);
        holder.description.setText(picture.description);
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }
}
