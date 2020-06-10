package com.example.collectionsframework.c9imageloader.acitvity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.collectionsframework.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

// Imageloader
public class ImageLoaderActivity extends Activity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.bt_imageloader_listview)
    Button btImageloaderListview;
    @BindView(R.id.bt_imageloader_gridview)
    Button btImageloaderGridview;
    @BindView(R.id.bt_imageloader_viewpager)
    Button btImageloaderViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_loader);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        // 标题
        tvTitle.setText("Imageloader");
    }

    @OnClick(R.id.bt_imageloader_listview)
    void bt_imageloader_listview_click(View view){
        // 跳转到listview案例页面
        Intent intent = new Intent(ImageLoaderActivity.this, ImageloaderListviewActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt_imageloader_gridview)
    void bt_imageloader_gridview_click(View view){
        // 跳转到gridview案例页面
        Intent intent = new Intent(ImageLoaderActivity.this, ImageloaderGridviewActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bt_imageloader_viewpager)
    void bt_imageloader_viewpager_click(View view){
        // 跳转到viewpager案例页面
        Intent intent = new Intent(ImageLoaderActivity.this, ImageloaderViewpagerActivity.class);
        startActivity(intent);
    }
}
