package org.hdm.app.sambia.util;

import android.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.hdm.app.sambia.R;
import org.hdm.app.sambia.data.Data;
import org.hdm.app.sambia.data.EventManager;
import org.hdm.app.sambia.data.RecordedData;
import org.hdm.app.sambia.dialogs.DFragment;
import org.hdm.app.sambia.listener.AdapterListener;
import org.hdm.app.sambia.listener.ViewHolderListener;
import org.hdm.app.sambia.screens.FragmentActivity;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Hannes on 27.05.2016.
 */
public class Recycler_View_Adapter extends RecyclerView.Adapter<View_Holder> implements ViewHolderListener {

    private final String TAG = "Recycler_View_Adapter";
    public List<Data> list = null;
    FragmentActivity fr;
    private AdapterListener listener;


    public Recycler_View_Adapter(FragmentActivity fragmentActivity, List<Data> data) {
        fr = fragmentActivity;
        this.list = data;
    }


    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        View_Holder holder = new View_Holder(v, this);
        return holder;
    }






    @Override
    public void onBindViewHolder(View_Holder holder, int position) {
        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView

        holder.setListener(this);
        holder.title.setText(list.get(position).title);
        holder.imageView.setImageResource(list.get(position).imageId);
        holder.position = position;
        holder.setBackground(list.get(position).activeState);

        // Set the selected state of the row depending on the position

        Log.d(TAG, "onBilnde ");

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







    public void setListener (AdapterListener listener) {
        this.listener = listener;
    }


    @Override
    public void didClickOnView(View view, int position, View_Holder holder) {


//        if(listener != null) listener.didOnClick(position, holder);



        // Get the DataObject which was selected
        // here are all information stored about the activity object
        // state, names image ect.
        String name = list.get(position).title;
        Data data = EventManager.getInstance().getActivityObject(name);


        if(!data.activeState){

            // Show DialogFragment
//            if(list.get(position).sub_category && list.get(position).activeState) {
//                DFragment dFragment = new DFragment(data);
//                FragmentManager fm = fr.getFragmentManager();
//                dFragment.show(fm, "Dialog Fragment");
//            }

            // Set State to active
            data.activeState = true;

            // set temporary start time
            data.startTime = Calendar.getInstance().getTime();
        } else  {
            // Deactivate Activity
            data.activeState = false;

            // set temporary end time
            data.endTime = Calendar.getInstance().getTime();


            // Calculation for Time messurement
//            long difference = data.startTime.getTime() - data.endTime.getTime();
//            int days = (int) (difference / (1000*60*60*24));
//            int hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
//            int min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
//            hours = (hours < 0 ? -hours : hours);
//            min = (min < 0 ? -min : min);
//            days = (days < 0 ? -days : days);
//
//            Log.d(TAG, "======= Hours "+ hours);
//            Log.d(TAG, "======= min "+ min);
//            Log.d(TAG, "======= days "+ days);


            // Save Time and subCategory in Data
            data.recordedDataList.add(new RecordedData(data.startTime, data.endTime, data.subCategoryName));
            data.startTime = null;
            data.endTime = null;
            data.subCategoryName = "";
        }








        holder.setBackground(data.activeState);

        // Store edited Data back in EventManager
        EventManager.getInstance().setActivityObject(data);
    }




    @Override
    public void didClickOnView(View view, String title) {
    }


    @Override
    public void didLongClickOnView(View view, int position) {
    }

}