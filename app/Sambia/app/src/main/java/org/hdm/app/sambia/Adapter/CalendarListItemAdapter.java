package org.hdm.app.sambia.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.data.Data;
import org.hdm.app.sambia.listener.CalendarItemOnClickListener;
import org.hdm.app.sambia.listener.ViewHolderListener;
import org.hdm.app.sambia.util.View_Holder;
import org.hdm.app.sambia.views.CustomerList;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
 * Created by Hannes on 27.05.2016.
 */
public class CalendarListItemAdapter extends RecyclerView.Adapter<View_Holder> implements
        ViewHolderListener {

    private final String TAG = "CalendarListItemAdapter";
    private final LinkedHashMap data;
    private Context context;



    public ArrayList list;
    private CalendarItemOnClickListener listener;
    private View v;




    public CalendarListItemAdapter(Context context, LinkedHashMap data, ArrayList recActivityTitles) {
        this.context = context;
        this.data = data;
        list = recActivityTitles;
        Log.d(TAG, "list" + list.toString());
    }




    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_calender_content, parent, false);
        View_Holder holder = new View_Holder(v, this);
        return holder;
    }




    @Override
    public void onBindViewHolder(View_Holder holder, int position) {
        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.setListener(this);
        if(holder.imageView != null) {

            Data dataa =(Data) data.get(list.get(position));
            Log.d(TAG, "dataa " + dataa.title);
            holder.imageView.setImageResource(dataa.imageId);
            holder.title.setText(dataa.title);
        }
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







    public void setListener (CalendarItemOnClickListener listener) {
        this.listener = listener;
    }



    @Override
    public void didClickOnView(View view, String s, View_Holder view_holder) {
            Log.d(TAG, "titlle " + s);
    }



    @Override
    public void didLongClickOnView(View view, String s, View_Holder view_holder) {
    }

}