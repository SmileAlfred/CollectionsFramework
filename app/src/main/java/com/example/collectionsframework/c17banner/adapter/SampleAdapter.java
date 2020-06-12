package com.example.collectionsframework.c17banner.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.collectionsframework.R;

public class SampleAdapter extends BaseAdapter {

    private String[] mDataset;
    private Context context;

    public SampleAdapter(Context context, String[] mDataset) {
        this.mDataset = mDataset;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mDataset.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=View.inflate(context, R.layout.text_item,null);
        TextView textView = (TextView) convertView.findViewById(R.id.text);
        textView.setText(mDataset[position]);
        if (position%2==0){
            textView.setBackgroundColor(Color.parseColor("#f5f5f5"));
        }else{
            textView.setBackgroundColor(Color.WHITE);
        }
        return convertView;
    }

}
