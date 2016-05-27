package org.hdm.app.sambia.listener;

import android.view.View;

/**
 * Created by Hannes on 27.05.2016.
 */
public interface ViewHolderListener {
     void didClickOnView(View view, int position);
     void didClickOnView(View view, String title);
     void didLongClickOnView(View view, int position);
}
