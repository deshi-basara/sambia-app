package org.hdm.app.sambia.listener;

import android.view.View;

import org.hdm.app.sambia.util.View_Holder;

/**
 * Created by Hannes on 27.05.2016.
 */
public interface ViewHolderListener {
     void didClickOnView(View view, int position, String s, View_Holder view_holder);
     void didClickOnView(View view, String title);
     void didLongClickOnView(View view, int position);
}
