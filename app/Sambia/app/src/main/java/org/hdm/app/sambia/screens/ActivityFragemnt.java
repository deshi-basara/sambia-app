package org.hdm.app.sambia.screens;

/**
 * Created by Hannes on 13.05.2016.
 */

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.main.MainActivityListener;
import org.hdm.app.sambia.main.ViewInteractionListener;
import org.hdm.app.sambia.views.MenuFragment;
import org.hdm.app.sambia.views.MyImageView;

/**
 * A fragment representing the front of the card.
 */
public class ActivityFragemnt extends Fragment implements ViewInteractionListener{

    private View view;
    private MainActivityListener listener;

    private final Handler handler = new Handler();
    private Runnable runPager;


    public ActivityFragemnt() {}




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_activitys, container, false);

        MyImageView imageView = (MyImageView) view.findViewById(R.id.btn_flip);
        imageView.setOnCountReachedListener(this);






        return view;

    }


    @Override
    public void onResume() {
        super.onResume();
        Fragment videoFragment = new MenuFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.frag_menu, videoFragment).commit();
    }

    @Override
    public void onPause() {
        super.onPause();
    }




    public void setListener(MainActivityListener listener) {
        this.listener = listener;
    }



    @Override
    public void mClickInteraction(View v) {
        listener.flip();
    }
}