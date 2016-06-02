package org.hdm.app.sambia.util;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private ViewHolderListener listener;




    public CardView cv;
    public TextView title;
    public ImageView imageView;
    public int position;
    Recycler_View_Adapter parentAdapter;



    View_Holder(View itemView, Recycler_View_Adapter recycler_view_adapter) {
        super(itemView);

        parentAdapter = recycler_view_adapter;
        cv = (CardView) itemView.findViewById(R.id.cardView);
        title = (TextView) itemView.findViewById(R.id.title);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        initListener();

    }

    private void initListener() {
        cv.setOnClickListener(this);
    }


    public void setListener(ViewHolderListener listener) {
        this.listener = listener;
    }




    @Override
    public void onClick(View v) {

        parentAdapter.list.get(getAdapterPosition()).setState();
        boolean state = parentAdapter.list.get(getAdapterPosition()).getState();

        setBackground(state);



        if(listener!= null) {
            listener.didClickOnView(v, position);
        }
    }









    @Override
    public boolean onLongClick(View v) {
        if(listener != null) listener.didLongClickOnView(v, position);
        return false;
    }



    public void setActive() {


    }


    public void setBackground(boolean state) {
        cv.setBackgroundColor(Color.WHITE);
        if(state) cv.setBackgroundColor(Color.RED);
    }


}