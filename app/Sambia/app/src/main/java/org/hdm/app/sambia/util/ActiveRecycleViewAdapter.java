package org.hdm.app.sambia.util;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.data.Data;
import org.hdm.app.sambia.listener.ActiveRecycleViewItemOnClickListener;
import org.hdm.app.sambia.listener.ViewHolderListener;
import org.hdm.app.sambia.screens.FragmentActivity;

import java.util.List;

/**
 * Created by Hannes on 27.05.2016.
 */
public class ActiveRecycleViewAdapter extends RecyclerView.Adapter<View_Holder> implements ViewHolderListener {

    private final String TAG = "ActiveRecycleViewAdapter";


    public List<Data> list = null;
    FragmentActivity fr;
    private ActiveRecycleViewItemOnClickListener listener;



    public ActiveRecycleViewAdapter(FragmentActivity fragmentActivity, List<Data> data) {
        fr = fragmentActivity;
        this.list = data;
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
        holder.imageView.setImageResource(list.get(position).imageId);
        holder.position = position;
        holder.setBackground(list.get(position).activeState);
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
    public void insert(int position, Data data) {
        list.add(position, data);
        notifyItemInserted(position);
    }



    // Remove a RecyclerView item containing a specified Data object
    public void remove(Data data) {
        int position = list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);
    }







    public void setListener(ActiveRecycleViewItemOnClickListener fragmentActivity) {
        this.listener = fragmentActivity;
    }


    @Override
    public void didClickOnView(View view, int position, View_Holder holder) {
        if(listener != null) listener.didOnClickActivityList(position, holder);
    }




    @Override
    public void didClickOnView(View view, String title) {
    }


    @Override
    public void didLongClickOnView(View view, int position) {
    }

}