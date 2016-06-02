package org.hdm.app.sambia.util;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.data.Data;
import org.hdm.app.sambia.listener.ViewHolderListener;

import java.util.Collections;
import java.util.List;

/**
 * Created by Hannes on 27.05.2016.
 */
public class Recycler_View_Adapter extends RecyclerView.Adapter<View_Holder> implements ViewHolderListener {

    private final String TAG = "Recycler_View_Adapter";
    public List<Data> list = null;



    public SparseBooleanArray selectedItems = null;




    public Recycler_View_Adapter(List<Data> list) {

        this.list = list;
        selectedItems = new SparseBooleanArray();
    }



    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        View_Holder holder = new View_Holder(v, this);

        Log.d(TAG, "onCreateViewHolder");

        return holder;
    }






    @Override
    public void onBindViewHolder(View_Holder holder, int position) {
        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.setListener(this);
        holder.title.setText(list.get(position).title);
        holder.imageView.setImageResource(list.get(position).imageId);
        holder.setBackground(list.get(position).getState());

        // Set the selected state of the row depending on the position

        Log.d(TAG, "onBilnde " + selectedItems.get(position, false));

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









    @Override
    public void didClickOnView(View view, int position) {

        Log.d("onClick ",  "" + position );
    }



    @Override
    public void didClickOnView(View view, String title) {
        Log.d(TAG, "didClickOnView " + view.getId() + " "+  title);
    }

    @Override
    public void didLongClickOnView(View view, int position) {

        Log.d("onLongClick", ""+ position);
    }

}