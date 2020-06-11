package com.example.collectionsframework.c13fresco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.collectionsframework.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrescoActivity extends Activity {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fresco);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        tvTitle.setText("Fresco");
    }

    // 带进度条的图片
    @OnClick(R.id.bt_fresco_spimg)
    void bt_fresco_spimg_click(View view){
        Intent intent = new Intent(FrescoActivity.this, FrescoSpimgActivity.class);

        startActivity(intent);
    }

    // 图片的不同裁剪
    @OnClick(R.id.bt_fresco_crop)
    void bt_fresco_crop_click(View view){
        Intent intent = new Intent(FrescoActivity.this, FrescoCropActivity.class);

        startActivity(intent);
    }

    // 圆形和圆角图片
    @OnClick(R.id.bt_fresco_circleAndCorner)
    void bt_fresco_circleAndCorner_click(View view){
        Intent intent = new Intent(FrescoActivity.this, FrescoCircleAndCornerActivity.class);

        startActivity(intent);
    }

    // 渐进式展示图片
    @OnClick(R.id.bt_fresco_jpeg)
    void bt_fresco_jpeg_click(View view){
        Intent intent = new Intent(FrescoActivity.this, FrescoJpegActivity.class);

        startActivity(intent);
    }

    // GIF动画图片
    @OnClick(R.id.bt_fresco_gif)
    void bt_fresco_gif_click(View view){
        Intent intent = new Intent(FrescoActivity.this, FrescoGifAcitivity.class);

        startActivity(intent);
    }

    // 多图请求及图片复用
    @OnClick(R.id.bt_fresco_multi)
    void bt_fresco_multi_click(View view){
        Intent intent = new Intent(FrescoActivity.this, FrescoMultiActivity.class);

        startActivity(intent);
    }

    // 图片加载监听
    @OnClick(R.id.bt_fresco_listener)
    void bt_fresco_listener_click(View view){
        Intent intent = new Intent(FrescoActivity.this, FrescoListenerActivity.class);

        startActivity(intent);
    }

    // 图片修改和旋转
    @OnClick(R.id.bt_fresco_resize)
    void bt_fresco_resize_click(View view){

        Intent intent = new Intent(FrescoActivity.this, FrescoResizeActivity.class);

        startActivity(intent);
    }

    // 修改图片
    @OnClick(R.id.bt_fresco_modifyImg)
    void bt_fresco_modifyImg_click(View view){
        Intent intent = new Intent(FrescoActivity.this, FrescoModifyActivity.class);

        startActivity(intent);
    }

    // 动态展示图片
    @OnClick(R.id.bt_fresco_autoSizeImg)
    void bt_fresco_autoSizeImg_click(View view){
        Intent intent = new Intent(FrescoActivity.this, FrescoAutoSizeActivity.class);

        startActivity(intent);
    }
}
