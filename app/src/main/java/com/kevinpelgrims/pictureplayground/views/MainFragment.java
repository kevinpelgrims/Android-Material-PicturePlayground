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
        pictures.add(new Picture("http://www.hollywoodreporter.com/sites/default/files/imagecache/modal_800/2014/09/too_good_for_grumpy_cat.jpg", "Grumpy Cat", "No!"));
        pictures.add(new Picture("http://th08.deviantart.net/fs70/PRE/i/2012/336/c/e/colonel_meow___wallpaper_by_chronoperates-d5mw23j.jpg", "Colonel Meow", "Likes scotch"));
        pictures.add(new Picture("http://i.dailymail.co.uk/i/pix/2013/04/24/article-0-19752124000005DC-581_968x640.jpg", "Lil Bub", "..."));
        pictures.add(new Picture("http://cdn.cutestpaw.com/wp-content/uploads/2013/12/Most-Famous-Felines-034.jpg", "Hamilton", "The Hipster Cat"));
        pictures.add(new Picture("http://cdn.cutestpaw.com/wp-content/uploads/2013/12/Most-Famous-Felines-031.jpg", "Bob", "Street Cat"));

        final RecyclerView picturesRecyclerView = (RecyclerView) view.findViewById(R.id.pictures_recycler_view);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        picturesRecyclerView.setLayoutManager(layoutManager);

        final RecyclerView.Adapter adapter = new PicturesAdapter(getActivity(), pictures);
        picturesRecyclerView.setAdapter(adapter);

        picturesRecyclerView.addOnItemTouchListener(new PictureRecyclerItemClickListener(getActivity(), new PictureRecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                PicturesAdapter.PictureViewHolder viewHolder = (PicturesAdapter.PictureViewHolder) picturesRecyclerView.getChildViewHolder(view);
                mListener.onPictureClick(viewHolder);
            }
        }));
    }

    public interface OnMainFragmentInteractionListener {
        public void onPictureClick(PicturesAdapter.PictureViewHolder pictureViewHolder);
    }
}
