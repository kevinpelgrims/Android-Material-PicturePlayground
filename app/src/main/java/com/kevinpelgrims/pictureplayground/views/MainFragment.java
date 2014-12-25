package com.kevinpelgrims.pictureplayground.views;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kevinpelgrims.pictureplayground.PictureRecyclerItemClickListener;
import com.kevinpelgrims.pictureplayground.R;
import com.kevinpelgrims.pictureplayground.adapters.PicturesAdapter;
import com.kevinpelgrims.pictureplayground.model.Picture;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    private OnMainFragmentInteractionListener mListener;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnMainFragmentInteractionListener) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnMainFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        final List<Picture> pictures = new ArrayList<Picture>();
        pictures.add(new Picture("http://aliceinreaderland.files.wordpress.com/2013/01/grumpy-cat.png", "Grumpy Cat", "Grumpy"));
        pictures.add(new Picture("https://lh6.googleusercontent.com/-qdwnNEk24TM/AAAAAAAAAAI/AAAAAAAAAAA/trT7WE7acR8/photo.jpg", "Colonel Meow", "Description"));
        pictures.add(new Picture("https://livinglist.beaglestreet.com/wp-content/uploads/BUB-yes-with-no-yes-copy-tighter-brighter-256x256.jpg", "Lil Bub", "..."));
        pictures.add(new Picture("http://aliceinreaderland.files.wordpress.com/2013/01/grumpy-cat.png", "name4", "description4"));
        pictures.add(new Picture("https://lh6.googleusercontent.com/-qdwnNEk24TM/AAAAAAAAAAI/AAAAAAAAAAA/trT7WE7acR8/photo.jpg", "name5", "description5"));

        final RecyclerView picturesRecyclerView = (RecyclerView) view.findViewById(R.id.pictures_recycler_view);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        picturesRecyclerView.setLayoutManager(layoutManager);

        final RecyclerView.Adapter adapter = new PicturesAdapter(getActivity(), pictures);
        picturesRecyclerView.setAdapter(adapter);

        picturesRecyclerView.addOnItemTouchListener(new PictureRecyclerItemClickListener(getActivity(), new PictureRecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                PicturesAdapter.PictureViewHolder viewHolder = (PicturesAdapter.PictureViewHolder) picturesRecyclerView.getChildViewHolder(view);
                Picture picture = viewHolder.picture;
                mListener.onPictureClick(picture, viewHolder.image);
            }
        }));
    }

    public interface OnMainFragmentInteractionListener {
        public void onPictureClick(Picture picture, ImageView imageView);
    }
}
