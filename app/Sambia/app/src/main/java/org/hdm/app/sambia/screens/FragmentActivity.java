package org.hdm.app.sambia.screens;

/**
 * Created by Hannes on 13.05.2016.
 */

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.data.ActivityObject;
import org.hdm.app.sambia.listener.SliderListener;
import org.hdm.app.sambia.util.MyCustomListAdapter;
import org.hdm.app.sambia.views.SwipeView;

import java.util.ArrayList;

/**
 * A fragment representing the front of the card.
 */
public class FragmentActivity extends BaseFragemnt implements SliderListener {


    private final String TAG = "Activity";


    private View view;
    private MyCustomListAdapter mAdapter;
    private ListView activity_frag_lv;



    private SwipeView mSwipeView;


    public FragmentActivity(FragmentContainer context) {
        super(context);
    }



//



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_activitys, container, false);

        initMenu(view);

        initActivityList();
        initSliderListener();
        return view;

    }




    @Override
    public void onResume() {
        super.onResume();
            setMenuTitle(TAG);
            setMenuBackground(android.R.color.holo_green_light);
            setMenuBtn(R.drawable.ic_forward);
    }






    private void initActivityList() {

        activity_frag_lv = (ListView) view.findViewById(R.id.activity_frag_lv);
        mAdapter = new MyCustomListAdapter(getActivity());

//        ArrayList<ActivityObject> arrayList = EventManager.getInstance().getActivityObject();
        ArrayList<ActivityObject> arrayList = new ArrayList<>();

        int size = arrayList.size();
        Log.d(TAG, "" + arrayList);
        size = 50;


        for (int i = 0; i < size; i++) {
//            ActivityObject mActivityObject = arrayList.get(i);
//            String name = mActivityObject.activity;
//            String image = mActivityObject.image;
//            int id = mActivityObject.id;
//            if (i % 4 == 0) {
//                mAdapter.addSeparatorItem("separator " + i);
//            } else {
//            mAdapter.addItem(name, image, id);
            mAdapter.addItem("Placeholder", "ic_action_info", i);
        }


        activity_frag_lv.setAdapter(mAdapter);
//        activity_frag_lv.setListAdapter(mAdapter);
        // Change to "CHOICE_MODE_MULTI" need a logic for max possible checked items and delete options
        activity_frag_lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }











    private void initSliderListener() {

        mSwipeView = (SwipeView) view.findViewById(R.id.swipeView);

        // setup our swipe view
        mSwipeView.setSliderListener(this);
        mSwipeView.initialize(getActivity());
    }

    @Override
    public void onEndNotReached() {

    }

    @Override
    public void onEndReached() {

    }

    @Override
    public void onSliding() {

    }
}