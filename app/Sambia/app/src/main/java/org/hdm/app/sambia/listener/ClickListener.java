package org.hdm.app.sambia.listener;

import android.view.View;

/**
 * Created by Hannes on 02.06.2016.
 */
public interface ClickListener {
    void onClick(View view, int position);
    void onLongClick(View view, int position);
}