package com.example.collectionsframework.c2okhttp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 作者：尚硅谷-杨光福 on 2016/7/21 20:37
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：常用框架的Frament
 */
public class CommonFrameFragmentAdapter extends BaseAdapter {

    private final Context mContext;
    private final String[] mDatas;

    public CommonFrameFragmentAdapter(Context context,String[] datas){
        this.mContext = context;
        this.mDatas = datas;
    }
    @Override
    public int getCount() {
        return mDatas.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(mContext);
        textView.setPadding(30,30,0,30);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(24);
        textView.setText(mDatas[position]);
        return textView;
    }
}
