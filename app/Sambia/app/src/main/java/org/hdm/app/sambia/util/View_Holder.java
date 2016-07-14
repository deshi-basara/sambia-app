package org.hdm.app.sambia.util;

import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.hdm.app.sambia.adapter.CalendarListAdapter;
import org.hdm.app.sambia.adapter.CalendarListItemAdapter;
import org.hdm.app.sambia.R;
import org.hdm.app.sambia.listener.ViewHolderListener;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Hannes on 27.05.2016.
 */
public class View_Holder extends RecyclerView.ViewHolder implements
        View.OnClickListener,
        View.OnLongClickListener {


    private final String TAG = "View_Holder";


    private ViewHolderListener listener;
    public RecyclerView rv_content;


    public  CardView cv;
    private ImageView iv_play;
    public  TextView title;
    public  TextView time;
    public  ImageView imageView;
    private LinearLayout ll_layout;
    private Timer timer;
    


    /************** Constructors ******************/


    public View_Holder(View v) {
        super(v);
        initActivityItemLayout();
        initActivityItemListener();
    }


    public View_Holder(View v, CalendarListAdapter calendarListAdapter) {
        super(v);
        initCalendarLayout(calendarListAdapter);
    }

    public View_Holder(View v, CalendarListItemAdapter calendarListItemAdapter) {
        super(v);
        initActivityItemLayout();
        initActivityItemListener();
    }






    private void initActivityItemLayout() {
        cv = (CardView) itemView.findViewById(R.id.cardView);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        title = (TextView) itemView.findViewById(R.id.title);
        time = (TextView)itemView.findViewById(R.id.tv_time);
        iv_play = (ImageView) itemView.findViewById(R.id.iv_play);
        iv_play.setVisibility(View.GONE);
        ll_layout = (LinearLayout) imageView.findViewById(R.id.ll_cardView);
        if(time!= null) time.setText("Hello");
    }



    private void initActivityItemListener() {
        cv.setOnClickListener(this);
    }








    private void initCalendarLayout(CalendarListAdapter calendarListAdapter) {
        title = (TextView) itemView.findViewById(R.id.title);
        rv_content = (RecyclerView) itemView.findViewById(R.id.rv_calender_item_content);
    }




    /**************** change view content ************************/




    //Change CardView Background to transparent
    public void setBackground(int value) {
        if(cv!= null) cv.setBackgroundColor(cv.getResources().getColor(R.color.transparent));
    }


    // Change CardView Style of Activitys
    // Activity == active - green background / PlayIcon visible
    // Activity != active - white background / PlayIcon not Visible
    public void setBackground(boolean state) {

        if(state) {

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) {
                // below lollipop
                cv.setCardBackgroundColor(Color.GREEN);
            } else {
                // lollipop and above
                cv.setBackgroundColor(cv.getResources().getColor(R.color.green));
            }
            iv_play.setVisibility(View.VISIBLE);
            time.setVisibility(View.VISIBLE);
            runCount();

        } else {

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) {
                // below lillipop
                cv.setCardBackgroundColor(Color.WHITE);
            } else {
                // lollipop and above
                cv.setBackgroundColor(cv.getResources().getColor(R.color.white));
            }
            iv_play.setVisibility(View.GONE);
            time.setVisibility(View.GONE);
            stopCount();
        }
    }

    private void stopCount() {
        if(timer != null) timer.cancel();
    }

    private int count = 0;
    private Handler handler = new Handler();

    private void runCount() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                Log.d(TAG, "count = "+ count);
                handler.post(new Runnable() {
                    public void run() {

                        int seconds = count % 60;
                        int minutes = count / 60;
                        int houres = minutes / 60;
                        String stringTime = String.format("%02d:%02d:%02d", houres, minutes, seconds);
                        time.setText(stringTime);
                        count++;
                    }
                });
            }
        },
        //Set how long before to start calling the TimerTask (in milliseconds)
        0,
        //Set the amount of time between each execution (in milliseconds)
        1000);
    }

    
    

    /******************* Listener **************************/



    // reference parent listener
    public void setListener(ViewHolderListener listener) {
        this.listener = listener;
    }



    // Listener Interface with parent class
    @Override
    public void onClick(View v) {
        if(listener!= null) listener.didClickOnView(v, title.getText().toString(), this);
    }


    @Override
    public boolean onLongClick(View v) {
        if(listener != null) listener.didLongClickOnView(v, title.getText().toString(), this);
        return false;
    }
}