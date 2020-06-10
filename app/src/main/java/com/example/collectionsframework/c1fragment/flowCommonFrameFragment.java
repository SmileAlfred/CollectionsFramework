package com.example.collectionsframework.c1fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collectionsframework.R;
import com.example.collectionsframework.c10picasso.activity.PicassoActivity;
import com.example.collectionsframework.c11recyclerview.RecyclerViewActivity;
import com.example.collectionsframework.c2okhttp.activity.OKHttpActivity;
import com.example.collectionsframework.c2okhttp.adapter.CommonFrameFragmentAdapter;
import com.example.collectionsframework.c2okhttp.base.BaseFragment;
import com.example.collectionsframework.c3json.activity.FastJsonActivity;
import com.example.collectionsframework.c3json.activity.GsonActivity;
import com.example.collectionsframework.c3json.activity.NativeJsonPraseActivity;
import com.example.collectionsframework.c4xutils3.XUtils3MainActivity;
import com.example.collectionsframework.c5afinal.AfinalActivity;
import com.example.collectionsframework.c6volley.VolleyActivity;
import com.example.collectionsframework.c7eventbus.EventBusActivity;
import com.example.collectionsframework.c8butterknife.ButterknifeActivity;
import com.example.collectionsframework.c9imageloader.acitvity.ImageLoaderActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作用：常用框架Fragment
 * 首页重构成 流式布局
 */
public class flowCommonFrameFragment extends BaseFragment implements View.OnClickListener {

    private List<TextView> mTextViews;
    private String[] datas;

    private TextView item1;
    private TextView item2;
    private TextView item3;
    private TextView item4;
    private TextView item5;
    private TextView item6;
    private TextView item7;
    private TextView item8;
    private TextView item9;
    private TextView item10;
    private TextView item11;
    private TextView item12;
    private TextView item13;
    private TextView item14;
    private TextView item15;
    private TextView item16;
    private TextView item17;
    private TextView item18;
    private TextView item19;
    private TextView item20;
    private TextView item21;
    private TextView item22;
    private TextView item23;
    private TextView item24;
    private TextView item25;
    private TextView item26;
    private TextView item27;
    private TextView item28;
    private TextView item29;
    private TextView item30;

    //CommonFrameFragment
    private static final String TAG = flowCommonFrameFragment.class.getSimpleName();


    @Override
    protected View initView() {
        Log.e(TAG, "常用框架Fragment页面被初始化了...");
        View view = View.inflate(mContext, R.layout.flowfragment_common_frame, null);
        mTextViews = new ArrayList<>();


        item1 = view.findViewById(R.id.item1);
        item2 = view.findViewById(R.id.item2);
        item3 = view.findViewById(R.id.item3);
        item4 = view.findViewById(R.id.item4);
        item5 = view.findViewById(R.id.item5);
        item6 = view.findViewById(R.id.item6);
        item7 = view.findViewById(R.id.item7);
        item8 = view.findViewById(R.id.item8);
        item9 = view.findViewById(R.id.item9);
        item10 = view.findViewById(R.id.item10);
        item11 = view.findViewById(R.id.item11);
        item12 = view.findViewById(R.id.item12);
        item13 = view.findViewById(R.id.item13);
        item14 = view.findViewById(R.id.item14);
        item15 = view.findViewById(R.id.item15);
        item16 = view.findViewById(R.id.item16);
        item17 = view.findViewById(R.id.item17);
        item18 = view.findViewById(R.id.item18);
        item19 = view.findViewById(R.id.item19);
        item20 = view.findViewById(R.id.item20);
        item21 = view.findViewById(R.id.item21);
        item22 = view.findViewById(R.id.item22);
        item23 = view.findViewById(R.id.item23);
        item24 = view.findViewById(R.id.item24);
        item25 = view.findViewById(R.id.item25);
        item26 = view.findViewById(R.id.item26);
        item27 = view.findViewById(R.id.item27);
        item28 = view.findViewById(R.id.item28);
        item29 = view.findViewById(R.id.item29);
        item30 = view.findViewById(R.id.item30);
        Log.e(TAG, "常用框架Fragment页面.item1 =? null "+ (item1 == null)  );


        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG, "常用框架Fragment数据被初始化了...");
        //准备数据
        datas = new String[]{"OKHttp", "NativeJsonPrase", "Gson", "FastJson", "xUtils3"
                , "Afinal", "Volley", "EventBus", "ButterKnife", "ImageLoader", "Picasso"
                , "RecyclerView", "Retrofit2", "Fresco", "Glide", "greenDao", "RxJava"
                , "jcvideoplayer", "pulltorefresh", "Expandablelistview"
                , "UniversalVideoView", "更多"};
        mTextViews.add(item1);
        mTextViews.add(item2);
        mTextViews.add(item3);
        mTextViews.add(item4);
        mTextViews.add(item5);
        mTextViews.add(item6);
        mTextViews.add(item7);
        mTextViews.add(item8);
        mTextViews.add(item9);
        mTextViews.add(item10);
        mTextViews.add(item11);
        mTextViews.add(item12);
        mTextViews.add(item13);
        mTextViews.add(item14);
        mTextViews.add(item15);
        mTextViews.add(item16);
        mTextViews.add(item17);
        mTextViews.add(item18);
        mTextViews.add(item19);
        mTextViews.add(item20);
        mTextViews.add(item21);
        mTextViews.add(item22);
        mTextViews.add(item23);
        mTextViews.add(item24);
        mTextViews.add(item25);
        mTextViews.add(item26);
        mTextViews.add(item27);
        mTextViews.add(item28);
        mTextViews.add(item29);
        mTextViews.add(item30);
        Log.e(TAG, "常用框架Fragment数据：" + mTextViews.size());
        for (int i = 0; i < Math.min(datas.length, mTextViews.size()); i++) {
            if (mTextViews.get(i) != null) {
                mTextViews.get(i).setText(datas[i]);
                mTextViews.get(i).setOnClickListener(this);
            }else {
                Log.e(TAG, "常用框架Fragment页面.第 "+ i +" 个TextView为null");
            }
        }
        //如果数据少而 TextView 多，那么让多余的 TextView 隐藏
        if(datas.length< mTextViews.size()){
            for (int i = datas.length; i < mTextViews.size(); i++) {
                mTextViews.get(i).setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onClick(View v) {
        String data = ((TextView) v).getText().toString();
        Intent intent = null;
        switch (data.toLowerCase()) {
            case "okhttp":
                intent = new Intent(mContext, OKHttpActivity.class);
                break;
            case "nativejsonprase":
                // 点击条目跳转到手动JSON解析页面
                intent = new Intent(mContext, NativeJsonPraseActivity.class);
                break;
            case "gson":
                // 点击条目跳转到Gson解析页面
                intent = new Intent(mContext, GsonActivity.class);
                break;
            case "fastjson":
                // 点击条目跳转到FastJson解析页面
                intent = new Intent(mContext, FastJsonActivity.class);
                break;
            case "xutils3":
                intent = new Intent(mContext, XUtils3MainActivity.class);
                break;
            case "afinal":
                intent = new Intent(mContext, AfinalActivity.class);
                break;
            case "volley":
                intent = new Intent(mContext, VolleyActivity.class);
                break;
            case "eventbus":
                intent = new Intent(mContext, EventBusActivity.class);
                break;
            case "butterknife":
                intent = new Intent(mContext, ButterknifeActivity.class);
                break;
            case "imageloader":
                intent = new Intent(mContext, ImageLoaderActivity.class);
                break;
            case "picasso":
                intent = new Intent(mContext, PicassoActivity.class);
                break;
            case "recyclerview":
                intent = new Intent(mContext, RecyclerViewActivity.class);
                break;
            case "更多":
                Toast.makeText(getContext(),"敬请期待",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        if(intent != null){
            startActivity(intent);
        }
    }
}
