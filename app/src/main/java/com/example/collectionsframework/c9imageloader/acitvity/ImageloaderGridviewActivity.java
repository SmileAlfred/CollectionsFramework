package com.example.collectionsframework.c9imageloader.acitvity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.example.collectionsframework.R;
import com.example.collectionsframework.c9imageloader.adapter.ImageloaderGridviewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageloaderGridviewActivity extends Activity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.gv_imageloader_gridview)
    GridView gvImageloaderGridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_imageloader_gridview);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        // 标题
        tvTitle.setText("Imageloader应用在Gridview中");

        // 初始化Gridview
        ImageloaderGridviewAdapter imageloaderGridviewAdapter = new ImageloaderGridviewAdapter(this);

        gvImageloaderGridview.setAdapter(imageloaderGridviewAdapter);
    }
}
