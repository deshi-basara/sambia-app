package com.hdm.mobileapplication.sambiaapp.pages;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Hannes on 05.05.2016.
 */
public class MyAdapter extends FragmentPagerAdapter {

    static final int NUM_ITEMS = 2;
    private boolean canScroll = true;


    public MyAdapter(FragmentManager fm) {
        super(fm);
    }



    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }


    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return ChooseActivityFragment.newInstance(0, "Choose Activity");
            case 1:
                return ViewActivitysFragment.newInstance(1, "View Activity");
            default:
                return null;
        }
        // never reach
    }


    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return getItem(position).getArguments().getString("someTitle");
    }
}
