package org.hdm.app.sambia.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.datastorage.ActivityObject;
import org.hdm.app.sambia.listener.ActiveActivityListOnClickListener;
import org.hdm.app.sambia.listener.ViewHolderListener;
import org.hdm.app.sambia.screens.FragmentActivity;
import org.hdm.app.sambia.util.View_Holder;

import java.util.List;

/**
 * Created by Hannes on 27.05.2016.
 */
public class ActiveActivityListAdapter extends RecyclerView.Adapter<View_Holder> implements
        ViewHolderListener {

    private final String TAG = "ActiveActivityListAdapter";


    public List<ActivityObject> list = null;
    FragmentActivity fr;
    private ActiveActivityListOnClickListener listener;



    public ActiveActivityListAdapter(FragmentActivity fragmentActivity, List<ActivityObject> activityObject) {
        fr = fragmentActivity;
        this.list = activityObject;
    }



    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_active, parent, false);
        View_Holder holder = new View_Holder(v);
        return holder;
    }






    @Override
    public void onBindViewHolder(View_Holder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.setListener(this);
        holder.title.setText(list.get(position).title);
        holder.imageView.setImageBitmap(list.get(position).image);
    }




    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }



    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return list.size();

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, ActivityObject activityObject) {
        list.add(position, activityObject);
        notifyItemInserted(position);
    }



    // Remove a RecyclerView item containing a specified Daata object
    public void remove(ActivityObject activityObject) {
        int position = list.indexOf(activityObject);
        list.remove(position);
        notifyItemRemoved(position);
    }







    public void setListener(ActiveActivityListOnClickListener fragmentActivity) {
        this.listener = fragmentActivity;
    }


    @Override
    public void didClickOnView(View view, String s, View_Holder holder) {
        if(listener != null) listener.didOnClickActivityList(s, holder);
    }


    @Override
    public void didLongClickOnView(View view, String s, View_Holder holder) {
    }

}