package com.kevinpelgrims.pictureplayground.views;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kevinpelgrims.pictureplayground.R;
import com.kevinpelgrims.pictureplayground.model.Picture;

public class DetailsFragment extends Fragment {
    private static final String ARG_PICTURE = "picture";

    private Picture mPicture;

    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(Picture picture) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PICTURE, picture);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPicture = (Picture) getArguments().getSerializable(ARG_PICTURE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }
}
