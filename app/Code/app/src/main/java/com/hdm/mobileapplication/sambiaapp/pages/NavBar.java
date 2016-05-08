package com.hdm.mobileapplication.sambiaapp.pages;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.util.AttributeSet;
import android.view.MenuItem;

import com.hdm.mobileapplication.sambiaapp.R;

import com.hdm.mobileapplication.sambiaapp.listener.MainActivityListener;

import static android.app.PendingIntent.getActivity;

/**
 * Created by Hannes on 05.05.2016.
 */
public class NavBar extends NavigationView implements
        NavigationView.OnNavigationItemSelectedListener {


    MainActivityListener listener;


    public NavBar(Context context) {
        super(context);
        init(context);
    }

    public NavBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NavBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    public void setItemChecked(int item) {
        this.getMenu().getItem(item).setChecked(true);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {

            case R.id.nav_activityview:
                listener.setPage(0);
                break;

            case R.id.nav_dayview:
                listener.setPage(1);
                break;

            case R.id.nav_call:
                break;

            case R.id.nav_about:
                break;

            case R.id.nav_settings:
                break;

            default:
                break;
        }
        return true;
    }


    private void init(Context context) {
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            listener = (MainActivityListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement MainActivityListener");
        }
        this.setNavigationItemSelectedListener(this);

    }
}
