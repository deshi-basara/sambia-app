package org.hdm.app.sambia.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.data.Data;
import org.hdm.app.sambia.listener.ActivityListOnClickListener;
import org.hdm.app.sambia.listener.ViewHolderListener;
import org.hdm.app.sambia.screens.FragmentActivity;
import org.hdm.app.sambia.util.View_Holder;

import java.util.List;

/**
 * Created by Hannes on 27.05.2016.
 */
public class ActivityListAdapter extends RecyclerView.Adapter<View_Holder> implements
        ViewHolderListener {

    private final String TAG = "dfListRecyclerViewAdapter";


    public List<Data> list = null;
    FragmentActivity fr;
    private ActivityListOnClickListener listener;



    public ActivityListAdapter(FragmentActivity fragmentActivity, List<Data> data) {
        fr = fragmentActivity;
        this.list = data;
    }


    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        View_Holder holder = new View_Holder(v);
        return holder;
    }






    @Override
    public void onBindViewHolder(View_Holder holder, int position) {
        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.setListener(this);
        holder.title.setText(list.get(position).title);
        holder.imageView.setImageResource(list.get(position).imageId);
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







    public void setListener (ActivityListOnClickListener listener) {
        this.listener = listener;
    }



    @Override
    public void didClickOnView(View view, String title, View_Holder holder) {
        if(listener != null) listener.didClickOnActivityListItem(title, holder);
    }


    @Override
    public void didLongClickOnView(View view, String title, View_Holder holder) {
    }
}