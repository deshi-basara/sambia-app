package org.hdm.app.sambia.util;

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
public class View_Holder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private final String TAG = "View_Holder";
    private ViewHolderListener listener;




    public CardView cv;
    public TextView title;
    public ImageView imageView;
    public int position;


    View_Holder(View itemView) {
        super(itemView);

        cv = (CardView) itemView.findViewById(R.id.cardView);
        title = (TextView) itemView.findViewById(R.id.title);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);

        initListener();

    }

    private void initListener() {
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);


        // Set Listener to every View you want

    }


    public void setListener(ViewHolderListener listener) {
        this.listener = listener;
    }


    @Override
    public void onClick(View v) {
        if(listener!= null) listener.didClickOnView(v, position);
        if(listener!= null) listener.didClickOnView(v, title.getText().toString());

    }

    @Override
    public boolean onLongClick(View v) {
        if(listener != null) listener.didLongClickOnView(v, position);
        return false;
    }
}