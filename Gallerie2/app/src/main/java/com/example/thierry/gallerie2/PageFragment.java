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

public class PageFragment extends Fragment implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    private GestureDetectorCompat gestureDetector;
    private BitmapFactory.Options options;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_main, container, false);
        ImageView iv = (ImageView) rootView.findViewById(R.id.imageView);
        */
        ImageView rootView = (ImageView) inflater.inflate(R.layout.activity_main, container, false);
        ImageView iv = (ImageView) rootView.findViewById(R.id.imageView);
        /*
        //initialiser gesture detector
        //gestureDetector = new GestureDetectorCompat(getContext(),this);
        //gestureDetector.setOnDoubleTapListener(this);
        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return getActivity().onTouchEvent(event);
                //return gestureDetector.onTouchEvent(event);
            }
        });
        */

        Bundle b = getArguments();
        //iv.setImageDrawable(getImage(b.getInt("position",R.drawable.home)));
        int resId = getImage(b.getInt("position"));
        //iv.setImageBitmap(PrepareImageGallery.GetVignette(getResources(), resId, 100, 100));

        chargeImage(resId, iv);
        options = PrepareImageGallery.getTailleImage(getResources(),resId);

        return rootView;
    }




    private void infoImage() {
        Toast.makeText(getContext(),options.outMimeType + " " + String.valueOf(options.outHeight) + " x " + String.valueOf(options.outWidth) + "\n density : " + String.valueOf(options.inDensity) ,Toast.LENGTH_SHORT).show();
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


    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        infoImage();

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Toast.makeText(getContext(),"long press",Toast.LENGTH_SHORT);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
//        if (gestureDetector)
        Toast.makeText(getContext(),"double tap" , Toast.LENGTH_SHORT);
        return true;
    }
}
