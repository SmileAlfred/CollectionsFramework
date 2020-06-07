package com.example.collectionsframework.c1fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.collectionsframework.R;
import com.example.collectionsframework.c2okhttp.activity.OKHttpActivity;
import com.example.collectionsframework.c2okhttp.adapter.CommonFrameFragmentAdapter;
import com.example.collectionsframework.c2okhttp.base.BaseFragment;
import com.example.collectionsframework.c3json.activity.FastJsonActivity;
import com.example.collectionsframework.c3json.activity.GsonActivity;
import com.example.collectionsframework.c3json.activity.NativeJsonPraseActivity;
import com.example.collectionsframework.c4xutils3.XUtils3MainActivity;
import com.example.collectionsframework.c5afinal.AfinalActivity;
import com.example.collectionsframework.c6volley.VolleyActivity;

import net.tsz.afinal.exception.AfinalException;

/**
 * 作者：尚硅谷-杨光福 on 2016/7/21 19:27
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：常用框架Fragment
 */
public class CommonFrameFragment extends BaseFragment {

    private ListView mListView;

    private String[] datas;

    private CommonFrameFragmentAdapter adapter;

    //CommonFrameFragment
    private static final String TAG = CommonFrameFragment.class.getSimpleName();

    @Override
    protected View initView() {
        Log.e(TAG, "常用框架Fragment页面被初始化了...");
        View view = View.inflate(mContext, R.layout.fragment_common_frame, null);
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
        datas = new String[]{"OKHttp", "nativeJsonPrase", "Gson", "FastJson", "xUtils3" , "Afinal"
                , "Volley", "Retrofit2", "Fresco", "Glide", "greenDao", "RxJava", "picasso"
                , "evenBus", "jcvideoplayer", "pulltorefresh", "Expandablelistview"
                , "UniversalVideoView", "....."};
        //设置适配器
        adapter = new CommonFrameFragmentAdapter(mContext, datas);
        mListView.setAdapter(adapter);
    }
}
