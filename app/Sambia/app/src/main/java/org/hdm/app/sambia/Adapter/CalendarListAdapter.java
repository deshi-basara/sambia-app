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

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.data.Data;
import org.hdm.app.sambia.listener.CalendarItemOnClickListener;
import org.hdm.app.sambia.listener.ViewHolderListener;
import org.hdm.app.sambia.util.View_Holder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import static org.hdm.app.sambia.util.Consts.CALENDARITEMROW;

/**
 * Created by Hannes on 27.05.2016.
 */
public class CalendarListAdapter extends RecyclerView.Adapter<View_Holder> implements
        ViewHolderListener, CalendarItemOnClickListener {

    private final String TAG = "CalendarListAdapter";
    public Context context;



    private TreeMap calendarMap;
    public LinkedHashMap data;
    public ArrayList list;
    private CalendarItemOnClickListener listener;
    private View v;




    private CalendarListItemAdapter resAdapter;



    public CalendarListAdapter(Activity activity, LinkedHashMap data, TreeMap calendar) {
        this.context = activity;
        this.data = data;
        this.calendarMap = calendar;
        list = new ArrayList(calendar.keySet());
       // removeStrings();
    }





    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_calendar, parent, false);
        View_Holder holder = new View_Holder(v, this);
        return holder;
    }




    @Override
    public void onBindViewHolder(View_Holder holder, int position) {
        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView

        String title = list.get(position).toString();


        holder.setListener(this);

        // Display only Hours and Minutes
        holder.title.setText(title.substring(11, title.length()-13));

        // Init RowItemContent
        resAdapter = new CalendarListItemAdapter(context, data, (ArrayList) calendarMap.get(title));
        resAdapter.setListener(this);
        holder.rv_content.setAdapter(resAdapter);
        holder.rv_content.setLayoutManager(new LinearLayoutManager(context));
        holder.rv_content.setLayoutManager(new StaggeredGridLayoutManager(
                CALENDARITEMROW,StaggeredGridLayoutManager.HORIZONTAL));

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

    }


    @Override
    public void didLongClickOnView(View view, String s, View_Holder view_holder) {
    }




    private void removeStrings() {
        for(int i=0; i<list.size(); i++){
            String str = (String) list.get(i);
            String strNew = str.substring(11, str.length()-13);
            list.set(i,strNew);
        }
    }



    @Override
    public void didOnClick(int position, String s, View_Holder holder) {
            Log.d(TAG, "didONCLICK " + position + " " + s);
    }
}