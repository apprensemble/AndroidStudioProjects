package com.example.thierry.gallerie;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by thierry on 15/04/16.
 */
public class ChargeImageThread extends AsyncTask<Integer, Void, Bitmap> {
    private final WeakReference<ImageView> refImageView;
    private int data = 0;
    private Resources res;

    public ChargeImageThread(Resources res,ImageView imageView) {
        this.res = res;
        refImageView = new WeakReference<ImageView>(imageView);
    }

    public int getData() {
        return data;
    }

    @Override
    protected Bitmap doInBackground(Integer... params) {
        data = params[0];
        final Bitmap bitmap = PrepareImageGallery.GetVignette(res, data, 640, 480 );
        //gestion cache
        //ajoutBitmapDansMemCache(String.valueOf(data),bitmap);
        return bitmap;
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
    public static ChargeImageThread getChargeImageThread(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getChargeImageThread();
            }

        }
        return null;
    }
}
