package com.kevinpelgrims.pictureplayground;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kevinpelgrims.pictureplayground.adapters.PicturesAdapter;
import com.kevinpelgrims.pictureplayground.model.Picture;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupPictures(view);
    }

    private void setupPictures(View view) {
        List<Picture> pictures = new ArrayList<Picture>();
        pictures.add(new Picture("http://whatever.com", "name1", "description1"));
        pictures.add(new Picture("http://whatever.com", "name2", "description2"));
        pictures.add(new Picture("http://whatever.com", "name3", "description3"));
        pictures.add(new Picture("http://whatever.com", "name4", "description4"));
        pictures.add(new Picture("http://whatever.com", "name5", "description5"));

        RecyclerView picturesRecyclerView = (RecyclerView) view.findViewById(R.id.pictures_recycler_view);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        picturesRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new PicturesAdapter(getActivity(), pictures);
        picturesRecyclerView.setAdapter(adapter);
    }
}
