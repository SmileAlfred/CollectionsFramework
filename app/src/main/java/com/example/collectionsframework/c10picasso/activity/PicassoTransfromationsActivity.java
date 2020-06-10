package com.example.collectionsframework.c10picasso.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.collectionsframework.R;
import com.example.collectionsframework.c10picasso.adapter.PicassoTransformationsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PicassoTransfromationsActivity extends Activity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lv_picasso_transfromations)
    ListView lvPicassoTransfromations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_picasso_transfromations);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        // 标题
        tvTitle.setText("Picasso的转换操作");

        List<String> data = new ArrayList<>();

        for (int i = 1; i<= 36; i++){
            data.add(i+"");
        }

        // 初始化listview
        PicassoTransformationsAdapter picassoTransformationsAdapter = new PicassoTransformationsAdapter(PicassoTransfromationsActivity.this,data);

        lvPicassoTransfromations.setAdapter(picassoTransformationsAdapter);
    }
}
