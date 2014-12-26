package com.kevinpelgrims.pictureplayground.views;

import android.app.Activity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionSet;

import com.kevinpelgrims.pictureplayground.R;
import com.kevinpelgrims.pictureplayground.adapters.PicturesAdapter;
import com.kevinpelgrims.pictureplayground.model.Picture;

public class MainActivity extends Activity implements MainFragment.OnMainFragmentInteractionListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, MainFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onPictureClick(Picture picture, PicturesAdapter.PictureViewHolder pictureViewHolder) {
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition(new ChangeImageTransform());
        transitionSet.addTransition(new ChangeBounds());
        transitionSet.addTransition(new ChangeTransform());
        transitionSet.setDuration(300);

        getFragmentManager().findFragmentById(R.id.container).setSharedElementReturnTransition(transitionSet);
        getFragmentManager().findFragmentById(R.id.container).setExitTransition(new Fade());

        DetailsFragment detailsFragment = DetailsFragment.newInstance(picture);
        detailsFragment.setSharedElementEnterTransition(transitionSet);
        detailsFragment.setEnterTransition(new Fade());
        getFragmentManager().beginTransaction()
                .replace(R.id.container, detailsFragment)
                .addSharedElement(pictureViewHolder.image, "picture")
                .addToBackStack("detail")
                .commit();
    }
}
