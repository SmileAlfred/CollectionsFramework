package com.example.collectionsframework.c13fresco;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.collectionsframework.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrescoAutoSizeActivity extends Activity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_fresco)
    LinearLayout llFresco;
    @BindView(R.id.tv_auto_desc)
    TextView tv_auto_desc;

    private void initData() {
        tvTitle.setText("动态展示图片");
        if (simpleDraweeView == null) {
            simpleDraweeView = new SimpleDraweeView(this);
            // 设置宽高比
            simpleDraweeView.setAspectRatio(2.0f);
        }
    }

    private SimpleDraweeView simpleDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fresco_auto_size);
        ButterKnife.bind(this);

        initData();
    }

    /**
     * 没有添加过控件时才添加，添加过不再重复添加；
     */
    private boolean isAdded = false;

    @OnClick(R.id.bt_fresco_loadsmall)
    void bt_fresco_loadsmall_click(View view) {

        // 图片的地址
        Uri uri = Uri.parse("http://n.sinaimg.cn/tech/5_img/upload/ad8784c4/80/w1024h656/20200610/6ee6-iuvaazn7189196.jpg");
        // 图片的请求
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .build();

        // 加载图片的控制
        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setOldController(simpleDraweeView.getController())
                .setImageRequest(request)
                .build();

        // 加载图片
        simpleDraweeView.setController(controller);

        // 添加View到线性布局中
        if (!isAdded) {
            llFresco.addView(simpleDraweeView);
            isAdded = true;
        }else {
            tv_auto_desc.setVisibility(View.VISIBLE);
        }
    }
}