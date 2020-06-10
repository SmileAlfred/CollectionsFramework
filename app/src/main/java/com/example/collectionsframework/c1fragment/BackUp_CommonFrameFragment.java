package com.example.collectionsframework.c1fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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

/**
 * 作用：常用框架Fragment
 */
public class BackUp_CommonFrameFragment extends BaseFragment {

    private ListView mListView;

    private String[] datas;

    private CommonFrameFragmentAdapter adapter;

    //CommonFrameFragment
    private static final String TAG = BackUp_CommonFrameFragment.class.getSimpleName();

    @Override
    protected View initView() {
        Log.e(TAG, "常用框架Fragment页面被初始化了...");
        View view = View.inflate(mContext, R.layout.backup_fragment_common_frame, null);
        mListView = (ListView) view.findViewById(R.id.listview);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = datas[position];//okhttp
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
                    default:
                        break;
                }
                startActivity(intent);
            }
        });
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
                , "UniversalVideoView", "....."};
        //设置适配器
        adapter = new CommonFrameFragmentAdapter(mContext, datas);
        mListView.setAdapter(adapter);
    }
}
