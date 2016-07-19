package org.hdm.app.sambia.screens;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.hdm.app.sambia.R;

public class FragmentIntroOne extends Fragment {

    private final String logIndictaor = "IntroPageOneFragment";

    private View rootView;
    private ViewGroup upperContainer;
    private ImageView logo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_intro_one, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setLayout(view);
        //startAnimation();
    }


    private void setLayout(View view) {
        //upperContainer = (ViewGroup) view.findViewById(R.id.upper_container);
        //logo = (ImageView) view.findViewById(R.id.logo);
    }

    private void startAnimation() {
        logo.setVisibility(View.INVISIBLE);
    }

}