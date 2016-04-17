package com.example.thierry.gallerie2;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by thierry on 16/04/16.
 */
public class ViewPagerDeCompet extends ViewPager{
    private GestureDetectorCompat gestureDetectorCompat;
    public ViewPagerDeCompet(Context context) {
        super(context);
    }

    public ViewPagerDeCompet(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev) &&
                gestureDetectorCompat.onTouchEvent(ev);
    }
    public void setGestureDetectorCompat(GestureDetectorCompat gestureDetectorCompat) {
        this.gestureDetectorCompat = gestureDetectorCompat;
    }
}
