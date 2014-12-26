package com.kevinpelgrims.pictureplayground.views;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.TransitionSet;
import android.widget.Toolbar;

import com.kevinpelgrims.pictureplayground.R;
import com.kevinpelgrims.pictureplayground.adapters.PicturesAdapter;

public class MainActivity extends Activity implements FragmentManager.OnBackStackChangedListener, MainFragment.OnMainFragmentInteractionListener {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        if (savedInstanceState == null) {
            getFragmentManager().addOnBackStackChangedListener(this);
            getFragmentManager().beginTransaction()
                    .add(R.id.container, MainFragment.newInstance())
                    .commit();
        }
    }

    private void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
    }

    @Override
    public void onBackStackChanged() {
        if (getFragmentManager().findFragmentById(R.id.container) instanceof MainFragment) {
            toolbar.setTitle(R.string.app_name);
        }
    }

    @Override
    public void onPictureClick(PicturesAdapter.PictureViewHolder pictureViewHolder) {
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition(new ChangeImageTransform());
        transitionSet.addTransition(new ChangeBounds());
        transitionSet.addTransition(new ChangeTransform());
        transitionSet.setDuration(300);

        getFragmentManager().findFragmentById(R.id.container).setSharedElementReturnTransition(transitionSet);
        getFragmentManager().findFragmentById(R.id.container).setExitTransition(new Fade());

        DetailsFragment detailsFragment = DetailsFragment.newInstance(pictureViewHolder.picture);
        detailsFragment.setSharedElementEnterTransition(transitionSet);
        detailsFragment.setEnterTransition(new Fade());
        getFragmentManager().beginTransaction()
                .replace(R.id.container, detailsFragment)
                .addSharedElement(pictureViewHolder.image, "picture")
                .addToBackStack("detail")
                .commit();

        toolbar.setTitle(pictureViewHolder.picture.name);
    }
}
