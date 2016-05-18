package org.hdm.app.sambia.screens;

/**
 * Created by Hannes on 13.05.2016.
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.hdm.app.sambia.R;

/**
 * A fragment representing the front of the card.
 */
public class FragmentDayView extends BaseFragemnt {


    private String TAG = "DayView";
    private View view;



    public FragmentDayView(FragmentContainer context) {
        super(context);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dayview, container, false);
        initMenu(view);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        setMenuTitle(TAG);
        setMenuBackground(android.R.color.holo_blue_light);
        setMenuBtn(R.drawable.ic_back);
    }
}