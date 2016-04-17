package com.example.thierry.gallerie;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

public class GallerieMain extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private GestureDetectorCompat gestureDetector;
    private BitmapFactory.Options options;
    private static int position = 0;
    final private int MAX = 5;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PrepareImageGallery.initCache();
        setContentView(R.layout.activity_gallerie_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        chargeImage(getImage(position), imageView);
        options = PrepareImageGallery.getTailleImage(getResources(),getImage(position));
        gestureDetector = new GestureDetectorCompat(this,this);
        //gestureDetector.setOnDoubleTapListener(this);
    }

    private void infoImage() {
        options = PrepareImageGallery.getTailleImage(getResources(),getImage(position));
        Toast.makeText(this,options.outMimeType + " " + String.valueOf(options.outHeight) + " x " + String.valueOf(options.outWidth) + "\n density : " + String.valueOf(options.inDensity) ,Toast.LENGTH_SHORT).show();
    }

    /*
    Retourne la position actuelle
     */
    private void suivant() {
        if (position < MAX -1) {
            chargeImage(getImage(++position),imageView);
        }
    }

    private void precedent() {
        if (position > 0) {
            chargeImage(getImage(--position),imageView);
        }
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
            final AsyncDrawable asyncDrawable = new AsyncDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), tacheDeCharge);
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

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Toast.makeText(getApplicationContext(),"tap",Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        infoImage();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if ((e1.getX() - e2.getX()) > 0) {
            Toast.makeText(getApplicationContext(),"suivant",Toast.LENGTH_SHORT).show();
            suivant();
        }
        else {
            Toast.makeText(getApplicationContext(),"precedent",Toast.LENGTH_SHORT).show();
            precedent();


        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

}
