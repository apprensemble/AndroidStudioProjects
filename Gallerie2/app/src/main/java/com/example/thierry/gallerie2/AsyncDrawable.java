package com.example.thierry.gallerie2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import java.lang.ref.WeakReference;

/**
 * Created by thierry on 15/04/16.
 */
public class AsyncDrawable extends BitmapDrawable {
    private final WeakReference<ChargeImageThread> chargeImageThreadRef;

    public AsyncDrawable(Resources res, Bitmap bitmap, ChargeImageThread chargeImageThread) {
        super(res, bitmap);
        chargeImageThreadRef = new WeakReference<ChargeImageThread>(chargeImageThread);
    }

    public ChargeImageThread getChargeImageThread() {
        return chargeImageThreadRef.get();
    }

}
