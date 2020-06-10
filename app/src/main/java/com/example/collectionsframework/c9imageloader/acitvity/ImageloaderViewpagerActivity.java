package com.example.collectionsframework.c9imageloader.acitvity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.collectionsframework.R;
import com.example.collectionsframework.c9imageloader.adapter.ImageloaderViewpagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageloaderViewpagerActivity extends Activity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.vp_imageloader_viewpager)
    ViewPager vpImageloaderViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_imageloader_viewpager);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        // 标题
        tvTitle.setText("Imageloader应用在viewpager中");

        // 初始化viewpager
        ImageloaderViewpagerAdapter imageloaderViewpagerAdapter = new ImageloaderViewpagerAdapter(this);

        vpImageloaderViewpager.setAdapter(imageloaderViewpagerAdapter);

        // 显示第一个条目
        vpImageloaderViewpager.setCurrentItem(1);
    }
}
