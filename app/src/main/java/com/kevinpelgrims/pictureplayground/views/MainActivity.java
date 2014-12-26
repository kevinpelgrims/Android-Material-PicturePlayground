package com.kevinpelgrims.pictureplayground.views;

import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
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
        setUpToolbarDefaultProperties();
    }

    private void setUpToolbarDefaultProperties() {
        toolbar.setTitle(R.string.app_name);
        toolbar.setBackgroundResource(R.color.primary);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        getWindow().setStatusBarColor(getResources().getColor(R.color.primary_dark));
    }

    @Override
    public void onBackStackChanged() {
        if (getFragmentManager().findFragmentById(R.id.container) instanceof MainFragment) {
            setUpToolbarDefaultProperties();
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

        Palette palette = Palette.generate(((BitmapDrawable) pictureViewHolder.image.getDrawable()).getBitmap());
        Palette.Swatch swatch = palette.getVibrantSwatch();
        Palette.Swatch darkSwatch = palette.getDarkVibrantSwatch();

        toolbar.setTitle(pictureViewHolder.picture.name);
        if (swatch != null) {
            toolbar.setBackgroundColor(swatch.getRgb());
            toolbar.setTitleTextColor(swatch.getBodyTextColor());
            if (darkSwatch != null) {
                getWindow().setStatusBarColor(darkSwatch.getRgb());
            }
        }
    }
}
