package com.kevinpelgrims.pictureplayground.views;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevinpelgrims.pictureplayground.R;
import com.kevinpelgrims.pictureplayground.model.Picture;
import com.squareup.picasso.Picasso;

public class DetailsFragment extends Fragment {
    private static final String ARG_PICTURE = "picture";

    private Picture mPicture;

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
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        Picasso.with(getActivity()).load(mPicture.url).into((ImageView) view.findViewById(R.id.picture_details_image));
        ((TextView) view.findViewById(R.id.picture_details_description)).setText(mPicture.description);
        return view;
    }
}
