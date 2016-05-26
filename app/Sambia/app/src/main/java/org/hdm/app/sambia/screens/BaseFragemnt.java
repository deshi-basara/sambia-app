package org.hdm.app.sambia.screens;

/**
 * Created by Hannes on 13.05.2016.
 */

import android.app.Fragment;
import android.view.View;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.listener.MainListener;
import org.hdm.app.sambia.listener.MenuListener;
import org.hdm.app.sambia.views.MenuView;

/**
 * A fragment representing the front of the card.
 */
public class BaseFragemnt extends Fragment implements
        View.OnClickListener,
        MenuListener
{


    private String TAG = "BaseFragment";


    private MainListener listener;
    private MenuView menuView;


    public BaseFragemnt(FragmentContainer context) {
        this.listener = context;
    }



    public void initMenu(View view) {
        menuView = (MenuView) view.findViewById(R.id.frag_menu);
        menuView.setListener(this);
    }


    public void setMenuBackground(int i) {
        if (menuView != null) {
            menuView.setBackground(i);}
    }


    public void setMenuTitle(String title) {
        if (menuView != null) {
            menuView.setTitle(title);}
    }


    public void setMenuBtn(int d) {
        if (menuView != null) {
            menuView.setImage(d);}
    }



    public void setMenuListener(MainListener listener) {
    }


    @Override
    public void onClick(View v) {

    }


    @Override
    public void mClickInteraction(View v) {
        this.listener.flip();
    }
}