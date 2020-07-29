package com.example.collectionsframework.c12glide.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaderFactory;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.collectionsframework.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GlideActivity extends Activity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_test_tomcat)
    ImageView iv_test_tomcat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_glide);
        ButterKnife.bind(this);

        initData();

        //Glide 加载图片时，图片地址 地址必须加 http://
        /**
         * android9.0系统默认禁止http协议，即禁止明文传输，必须使用https来通讯；而app中所使用的图片和某些地方用的正好是http协议的方式。
         * 在Manifest的application中加入以下语句即可
         * android:usesCleartextTraffic="true"
         */
        Glide.with(this)
                //.load("http://hfsRailMeasurement.nat123.cc:44501/WebRoot/images/1.jpg")//口袋电脑联网，PAD通过外网访问电脑文件
                //.load("http://profile.csdnimg.cn/B/8/7/1_liusaisaiv1")//加载网络图片
                //.load("http://192.168.0.8:8080/WebRoot/images/1.jpg")//电脑开启热点，模拟器连接热点访问TomCat服务器文件
                .load("http://192.168.137.1:8080/WebRoot/images/1.jpg")//电脑开启WIFI，PAD连接热点访问TomCat服务器文件
                //跳过内存缓存；解决 加载动态网络图片时不是最新的问题
                .skipMemoryCache(true)
                //不缓冲disk硬盘中
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(iv_test_tomcat);
    }

    private void initData() {
        tvTitle.setText("Glide");
    }

    // 基本使用
    @OnClick(R.id.bt_glide_base)
    void bt_glide_base_click(View view){
        Intent intent = new Intent(GlideActivity.this, GlideBaseActivity.class);
        startActivity(intent);
    }

    // 在Recyclerview中使用
    @OnClick(R.id.bt_glide_recyclerview)
    void bt_glide_recyclerview_click(View view){
        Intent intent = new Intent(GlideActivity.this, GlideRecyclerviewActivity.class);
        startActivity(intent);
    }

    // 图片变换
    @OnClick(R.id.bt_glide_tranfromations)
    void bt_glide_tranfromations_click(View view){
        Intent intent = new Intent(GlideActivity.this, GlideTranformationsActivity.class);
        startActivity(intent);
    }
}
