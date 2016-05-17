package org.hdm.app.sambia.screens;

/**
 * Created by Hannes on 13.05.2016.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.main.MainActivityListener;
import org.hdm.app.sambia.views.BaseFragment;

/**
 * A fragment representing the front of the card.
 */
public class DayViewFragemnt extends Fragment implements View.OnClickListener{

    private View view;
    private MainActivityListener listener;



    public DayViewFragemnt() {
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dayview, container, false);

        view.setOnClickListener(this);

        return view;
    }



    @Override
    public void onClick(View v) {
        listener.flip();
    }



    public void setListener(BaseFragment mainFragment) {
        listener = (MainActivityListener) mainFragment;

    }
}