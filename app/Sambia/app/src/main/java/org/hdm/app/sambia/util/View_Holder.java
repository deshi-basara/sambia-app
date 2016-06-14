package org.hdm.app.sambia.util;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.listener.ViewHolderListener;

/**
 * Created by Hannes on 27.05.2016.
 */
public class View_Holder extends RecyclerView.ViewHolder implements
        View.OnClickListener,
        View.OnLongClickListener {

    private final String TAG = "View_Holder";


    private ImageView iv_play;
    private ViewHolderListener listener;




    public CardView cv;
    public TextView title;
    public ImageView imageView;
    public int position;






    public View_Holder(View v) {
        super(v);
        initLayout();
        initListener();
    }



    private void initLayout() {
        cv = (CardView) itemView.findViewById(R.id.cardView);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        title = (TextView) itemView.findViewById(R.id.title);
        iv_play = (ImageView) itemView.findViewById(R.id.iv_play);
        iv_play.setVisibility(View.GONE);
    }


    private void initListener() {
        cv.setOnClickListener(this);
    }



    public void setListener(ViewHolderListener listener) {
        this.listener = listener;
    }




    @Override
    public void onClick(View v) {
        if(listener!= null) listener.didClickOnView(v, position, title.getText().toString(), this);
    }









    @Override
    public boolean onLongClick(View v) {
        if(listener != null) listener.didLongClickOnView(v, position);
        return false;
    }




    public void setBackground(boolean state) {
//        cv.setBackgroundColor(Color.WHITE);

        if(state) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) {
                // below lillipop
                cv.setCardBackgroundColor(Color.GREEN);
                // working for lower questions as per another answer posted here
            } else {
                // lollipop and above
                cv.setBackgroundColor(cv.getResources().getColor(R.color.green));
                // known to be working for lillipop as per your question
            }
            iv_play.setVisibility(View.VISIBLE);

        } else {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) {
                // below lillipop
                cv.setCardBackgroundColor(Color.WHITE);
                // working for lower questions as per another answer posted here
            } else {
                // lollipop and above
                cv.setBackgroundColor(cv.getResources().getColor(R.color.white));
                // known to be working for lillipop as per your question
            }
            iv_play.setVisibility(View.GONE);
        }
    }


}