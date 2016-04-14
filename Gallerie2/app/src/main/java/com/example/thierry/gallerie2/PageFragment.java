package com.example.thierry.gallerie2;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by thierry on 12/04/16.
 */

public class PageFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_main, container, false);
        ImageView iv = (ImageView) rootView.findViewById(R.id.imageView);
        Bundle b = getArguments();
        //iv.setImageDrawable(getImage(b.getInt("position",R.drawable.home)));
        int resId = getImage(b.getInt("position"));
        iv.setImageBitmap(PrepareImageGallery.GetVignette(getResources(), resId, 100, 100));
        return rootView;
    }

    private int getImage(int position) {
        switch (position) {
            case 0 : return R.drawable.igloo;
            case 1 : return R.drawable.apartment;
            case 2 : return R.drawable.home;
            default: return R.drawable.castle;
        }
    }


}
