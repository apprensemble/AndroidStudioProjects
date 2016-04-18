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

public class MainActivity extends FragmentActivity{

    private final static int NBR_PAGES = 5;
    private ViewPagerDeCompet pager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);


        //gestion du cache
        PrepareImageGallery.initCache();

       //instanciation des viewpager et pagerAdapter
        pager = (ViewPagerDeCompet) findViewById(R.id.pager);
        pagerAdapter = new GalleriePagerAdapter(getSupportFragmentManager());
        pager.setGestureDetectorCompat();
        pager.setAdapter(pagerAdapter);
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
