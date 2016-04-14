package com.example.thierry.gallerie2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by thierry on 14/04/16.
 */
public class ChargeImageThread extends AsyncTask<Integer, Void, Bitmap> {
    private final WeakReference<ImageView> refImageView;
    private int data = 0;

    public ChargeImageThread(ImageView imageView) {
        refImageView = new WeakReference<ImageView>(imageView);
    }

    @Override
    protected Bitmap doInBackground(Integer... params) {
        data = params[0];
        return PrepareImageGallery.GetVignette(Resources.getSystem(), data, 100, 100 );
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (refImageView != null && bitmap != null ) {
            final ImageView imageView = refImageView.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
