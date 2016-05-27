package org.hdm.app.sambia.listener;

import android.view.View;

/**
 * Created by Hannes on 13.05.2016.
 */
public interface OnRecyclerViewItemClickListener {


    /**
     * Called when any item with in recyclerview or any item with in item
     * clicked
     *
     * @param position
     *            The position of the item
     * @param id
     *            The id of the view which is clicked with in the item or
     *            -1 if the item itself clicked
     */
    void onRecyclerViewItemClicked(int position, int id);
}