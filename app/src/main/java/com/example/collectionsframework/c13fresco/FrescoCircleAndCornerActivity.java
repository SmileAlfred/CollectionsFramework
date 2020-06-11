package com.example.collectionsframework.c13fresco;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.collectionsframework.R;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrescoCircleAndCornerActivity extends Activity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.sdv_fresco_circleandcorner)
    SimpleDraweeView sdvFrescoCircleandcorner;
    private Uri uri;
    private GenericDraweeHierarchyBuilder builder;
    private RoundingParams parames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fresco_circle_and_corner);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        tvTitle.setText("圆形和圆角图片");

        uri = Uri.parse("http://n.sinaimg.cn/tech/5_img/upload/ad8784c4/80/w1024h656/20200610/6ee6-iuvaazn7189196.jpg");
        //uri = Uri.parse("http://img4q.duitang.com/uploads/item/201304/27/20130427043538_wAfHC.jpeg");

        builder = new GenericDraweeHierarchyBuilder(getResources());
    }

    // 设置圆形图片
    @OnClick(R.id.bt_fresco_circle)
    void bt_fresco_circle_click(View view){

        // 设置圆形图片
        parames = RoundingParams.asCircle();
        GenericDraweeHierarchy hierarchy = builder.setRoundingParams(parames).build();
        sdvFrescoCircleandcorner.setHierarchy(hierarchy);

        sdvFrescoCircleandcorner.setImageURI(uri);
    }

    // 设置圆角图片
    @OnClick(R.id.bt_fresco_corner)
    void bt_fresco_corner_click(View view){

        parames = RoundingParams.fromCornersRadius(100f);// 圆角半径
        //parames.setOverlayColor(getResources().getColor(android.R.color.holo_red_light));//覆盖层
        parames.setBorder(getResources().getColor(android.R.color.holo_blue_light), 5);//边框

        GenericDraweeHierarchy hierarchy = builder.setRoundingParams(parames).build();
        sdvFrescoCircleandcorner.setHierarchy(hierarchy);

        // 加载图片
        sdvFrescoCircleandcorner.setImageURI(uri);
    }
}
