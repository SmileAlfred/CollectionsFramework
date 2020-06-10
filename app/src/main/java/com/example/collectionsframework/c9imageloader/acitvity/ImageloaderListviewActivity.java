package com.example.collectionsframework.c9imageloader.acitvity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.collectionsframework.R;
import com.example.collectionsframework.c9imageloader.adapter.ImageloaderListviewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageloaderListviewActivity extends Activity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lv_imageloader)
    ListView lvImageloader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_imageloader_listview);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        // 初始化标题
        tvTitle.setText("Imageloader应用在Listview中");

        ImageloaderListviewAdapter imageloaderListviewAdapter = new ImageloaderListviewAdapter(this);

        lvImageloader.setAdapter(imageloaderListviewAdapter);
    }
}
