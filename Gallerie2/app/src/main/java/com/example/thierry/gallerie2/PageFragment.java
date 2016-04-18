package com.example.thierry.gallerie2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.LruCache;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by thierry on 12/04/16.
 */

public class PageFragment extends Fragment{


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ImageView rootView = (ImageView) inflater.inflate(R.layout.activity_main, container, false);
        ImageView iv = (ImageView) rootView.findViewById(R.id.imageView);

        Bundle b = getArguments();
        int resId = getImage(b.getInt("position"));

        chargeImage(resId, iv);

        return rootView;
    }

    private int getImage(int position) {
        switch (position) {
            case 0 : return R.drawable.fait_maison_1;
            case 1 : return R.drawable.fait_maison_2;
            case 2 : return R.drawable.fait_maison_3;
            case 3 : return R.drawable.fait_maison_4;
            case 4 : return R.drawable.fait_maison_5;
            default: return R.drawable.fait_maison_4;
        }
    }

    public void chargeImage(int resId, ImageView imageView) {
        final String imageKey = String.valueOf(resId);

        final Bitmap bitmap = PrepareImageGallery.getBitmapDepuisMemCache(imageKey);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
        else if (annuleTacheEnCours(resId, imageView)) {
            ChargeImageThread tacheDeCharge = new ChargeImageThread(getResources() ,imageView);
            //partie pour la gestion de la concurence
            final AsyncDrawable asyncDrawable = new AsyncDrawable(getResources(), BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher), tacheDeCharge);
            imageView.setImageDrawable(asyncDrawable);
            tacheDeCharge.execute(resId);
        }
    }

    public static boolean annuleTacheEnCours(int resId, ImageView imageView) {
        final ChargeImageThread chargeImageThread = ChargeImageThread.getChargeImageThread(imageView);
        if (chargeImageThread != null) {
            final int bitmapData =  chargeImageThread.getData();
            if (bitmapData == 0 || bitmapData != resId) {
                chargeImageThread.cancel(true);
            }
            else {
                return false;
            }
        }
        return true;
    }


}
