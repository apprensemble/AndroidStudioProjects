package com.example.thierry.gallerie2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.InputStream;
import java.lang.ref.WeakReference;
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
        //iv.setImageBitmap(PrepareImageGallery.GetVignette(getResources(), resId, 100, 100));
        chargeImage(resId, iv);
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

    public void chargeImage(int resId, ImageView imageView) {
        if (annuleTacheEnCours(resId, imageView)) {
            ChargeImageThread tacheDeCharge = new ChargeImageThread(imageView);
            //partie pour la gestion de la concurence
            final AsyncDrawable asyncDrawable = new AsyncDrawable(getResources(), BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher), tacheDeCharge);
            imageView.setImageDrawable(asyncDrawable);
            tacheDeCharge.execute(resId);
        }
    }

    public static boolean annuleTacheEnCours(int resId, ImageView imageView) {
        final ChargeImageThread chargeImageThread = getChargeImageThread(imageView);
        if (chargeImageThread != null) {
            final int bitmapData =  chargeImageThread.data;
            if (bitmapData == 0 || bitmapData != resId) {
                chargeImageThread.cancel(true);
            }
            else {
                return false;
            }
        }
        return true;
    }

    private static ChargeImageThread getChargeImageThread(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getChargeImageThread();
            }

        }
        return null;
    }

    /*
     je ne sais pas où mettre cette classe, mon pb c'est l'acces aux ressources
     je trouve ça etrange de mettre ça dans le fragment qui est volatile.
     mais c'est lui qui gere l'imageView et je n'ai pas de meilleure idée
    */
    class ChargeImageThread extends AsyncTask<Integer, Void, Bitmap> {
        private final WeakReference<ImageView> refImageView;
        private int data = 0;

        public ChargeImageThread(ImageView imageView) {
            refImageView = new WeakReference<ImageView>(imageView);
        }

        @Override
        protected Bitmap doInBackground(Integer... params) {
            data = params[0];
            return PrepareImageGallery.GetVignette(getResources(), data, 1024, 768 );
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (isCancelled()) {
                bitmap = null;
            }

            if (refImageView != null && bitmap != null ) {
                final ImageView imageView = refImageView.get();
                final ChargeImageThread tacheChargeImage = getChargeImageThread(imageView);

                if (this == tacheChargeImage && imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
            else {
                Log.i("refImage", "vide");
            }
        }
    }

    /*
    a present gestion de la concurence. Encore une fois dans un fragment qui est créé
    pour chaque page...Est-ce le bon endroit?
    */

    static class AsyncDrawable extends BitmapDrawable {
        private final WeakReference<ChargeImageThread> chargeImageThreadRef;

        public AsyncDrawable(Resources res, Bitmap bitmap, ChargeImageThread chargeImageThread) {
            super(res, bitmap);
            chargeImageThreadRef = new WeakReference<ChargeImageThread>(chargeImageThread);
        }

        public ChargeImageThread getChargeImageThread() {
            return chargeImageThreadRef.get();
        }

    }


}
