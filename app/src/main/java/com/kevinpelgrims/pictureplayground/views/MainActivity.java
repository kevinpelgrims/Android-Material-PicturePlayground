package com.kevinpelgrims.pictureplayground.views;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.kevinpelgrims.pictureplayground.R;
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
    public void onPictureClick(Picture picture, ImageView imageView) {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, DetailsFragment.newInstance(picture))
                .addToBackStack(null)
                .commit();
    }
}
