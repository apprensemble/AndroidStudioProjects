package com.example.thierry.gallerie2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener{
    private GestureDetectorCompat gestureDetector;

    private final static int NBR_PAGES = 5;
    private ViewPagerDeCompet pager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        gestureDetector = new GestureDetectorCompat(this,this);

        //gestion du cache
        PrepareImageGallery.initCache();

       //instanciation des viewpager et pagerAdapter
        pager = (ViewPagerDeCompet) findViewById(R.id.pager);
        pagerAdapter = new GalleriePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setGestureDetectorCompat(gestureDetector);
        //pager.setOffscreenPageLimit(4);

    }

    @Override
    public void onBackPressed() {
        if (pager.getCurrentItem() == 0) {
            super.onBackPressed();
        }
        else {
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
        //return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.i("main","onShowPress");

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
        Log.i("main","onLongPress yeah man!");

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.i("main","onFling");
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.i("main", "double tap");
        return true;
    }


    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.i("main", "double tap");
        return true;
    }


    private class GalleriePagerAdapter extends FragmentStatePagerAdapter {
    public GalleriePagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }



    @Override
    public Fragment getItem(int position) {
        Bundle b = new Bundle();
        b.putInt("position", position);
        Fragment f = new PageFragment();
        f.setArguments(b);
        return f;
    }

    @Override
    public int getCount() {
        return NBR_PAGES;
    }
}
}
