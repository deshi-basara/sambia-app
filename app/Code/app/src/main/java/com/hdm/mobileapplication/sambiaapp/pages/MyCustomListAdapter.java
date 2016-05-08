package com.hdm.mobileapplication.sambiaapp.pages;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.hdm.mobileapplication.sambiaapp.R;

import java.util.ArrayList;
import java.util.TreeSet;

public class MyCustomListAdapter extends BaseAdapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
    private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;


    private ArrayList mData = new ArrayList();
    private LayoutInflater mInflater;
    private TreeSet mSeparatorsSet = new TreeSet();


    public MyCustomListAdapter(Context context) {
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    public void addItem(final String item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void addSeparatorItem(final String item) {
        mData.add(item);
        // save separator position
        mSeparatorsSet.add(mData.size() - 1);
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        return mSeparatorsSet.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_MAX_COUNT;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        Log.d("getView", "getView " + position + " " + convertView);

        ViewHolder holder = null;
        int type = getItemViewType(position);


        if (convertView == null) {
            holder = new ViewHolder();

            switch (type) {

                case TYPE_ITEM:
                    convertView = mInflater.inflate(R.layout.rowlayout, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.label);
                    holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
//                    Log.d("getView", "getView " + type + " " + convertView);
                    convertView.setClickable(false);
                    break;

                case TYPE_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.rowlayout_seperator, null);
                    holder.textView = (TextView)convertView.findViewById(R.id.label);
                    holder.imageView = null;
                    convertView.setClickable(true);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(holder.textView != null) holder.textView.setText((CharSequence) mData.get(position));
        if(holder.imageView != null) holder.imageView.setImageResource(R.drawable.ic_action_photo);
        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }
}
