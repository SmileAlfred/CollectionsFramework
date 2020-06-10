package com.example.collectionsframework.c10picasso.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.collectionsframework.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

// picasso
public class PicassoActivity extends Activity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.bt_picasso_base)
    Button btPicassoBase;
    @BindView(R.id.bt_picasso_listview)
    Button btPicassoListview;
    @BindView(R.id.bt_picasso_tranformations)
    Button btPicassoTranformations;
    @BindView(R.id.iv_picasso_result1)
    ImageView ivPicassoResult1;
    @BindView(R.id.iv_picasso_result2)
    ImageView ivPicassoResult2;
    @BindView(R.id.iv_picasso_result3)
    ImageView ivPicassoResult3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_picasso);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        // 标题
        tvTitle.setText("Picasso");


    }

    @OnClick(R.id.bt_picasso_base)
    void bt_picasso_base_click(View view) {
        // 基本用法

        // 普通加载图片
        Picasso.get()
                .load("http://n.sinaimg.cn/translate/20160819/9BpA-fxvcsrn8627957.jpg")
                .into(ivPicassoResult1);

        // 裁剪的方式加载图片
        Picasso.get()
                .load("http://n.sinaimg.cn/translate/20160819/9BpA-fxvcsrn8627957.jpg")
                .resize(100,100)
                .into(ivPicassoResult2);

        // 选择180度
        Picasso.get()
                .load("http://n.sinaimg.cn/translate/20160819/9BpA-fxvcsrn8627957.jpg")
                .rotate(180)
                .into(ivPicassoResult3);
    }

    @OnClick(R.id.bt_picasso_listview)
    void bt_picasso_listview_click(View view) {
        // 跳转到listview页面
        Intent intent = new Intent(PicassoActivity.this, PicassoListviewActivity.class);

        startActivity(intent);
    }

    @OnClick(R.id.bt_picasso_tranformations)
    void bt_picasso_tranformations_click(View view) {
        // 跳转到图片转换页面
        Intent intent = new Intent(PicassoActivity.this, PicassoTransfromationsActivity.class);
        startActivity(intent);
    }
}
