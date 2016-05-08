package com.hdm.mobileapplication.sambiaapp.pages;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.hdm.mobileapplication.sambiaapp.R;

/**
 * Created by Hannes on 05.05.2016.
 */
public class MyViewPager extends ViewPager{


    private boolean swipeable;


    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyViewPager);
        try {
            swipeable = a.getBoolean(R.styleable.MyViewPager_swipeable, true);
        } finally {
            a.recycle();
        }
    }


    public void setSwipeable(boolean swipeable) {
        this.swipeable = swipeable;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return swipeable ? super.onInterceptTouchEvent(event) : false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return swipeable ? super.onTouchEvent(event) : false;
    }


//    Another easy solution to disable swiping at specific page (in this example, page 2):
//
//    int PAGE = 2;
//    viewPager.setOnTouchListener(new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            if (viewPager.getCurrentItem() == PAGE) {
//                viewPager.setCurrentItem(PAGE-1, false);
//                viewPager.setCurrentItem(PAGE, false);
//                return  true;
//            }
//            return false;
//        }
    }
