package org.hdm.app.sambia.listener;

/**
 * Created by Hannes on 17.05.2016.
 */
/**
 * Callback interface that notifies clients in what state this view is currently at.
 *
 * @author quiqueqs`
 */
public interface SliderListener {

    /** Sliding but we've not reached the end */
    public void onEndNotReached();

    /** We've reached the end */
    public void onEndReached();

    /** Still sliding */
    public void onSliding();
}