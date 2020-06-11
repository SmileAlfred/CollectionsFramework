package com.example.collectionsframework.c13fresco;

import android.app.Activity;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.collectionsframework.R;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrescoCropActivity extends Activity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.sdv_fresco_crop)
    SimpleDraweeView sdvFrescoCrop;
    @BindView(R.id.tv_fresco_explain)
    TextView tvFrescoExplain;
    private GenericDraweeHierarchyBuilder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fresco_crop);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        tvTitle.setText("图片的不同裁剪");

        builder = new GenericDraweeHierarchyBuilder(getResources());
    }

    // 居中，无缩放
    @OnClick(R.id.bt_fresco_center)
    void bt_fresco_center_click(View view){
        // 设置描述
        tvFrescoExplain.setText("居中，无缩放");

        // 样式设置
        GenericDraweeHierarchy hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.CENTER).build();

        // 图片显示
        imageDisplay(hierarchy);

    }

    private void imageDisplay(GenericDraweeHierarchy hierarchy) {
        sdvFrescoCrop.setHierarchy(hierarchy);

        // 加载图片
        Uri uri = Uri.parse("http://n.sinaimg.cn/tech/5_img/upload/ad8784c4/80/w1024h656/20200610/6ee6-iuvaazn7189196.jpg");
        //Uri uri = Uri.parse("http://img4q.duitang.com/uploads/item/201305/20/20130520115416_VrUUR.jpeg");
        sdvFrescoCrop.setImageURI(uri);
    }

    // 保持宽高比缩小或放大，使得两边都大于或等于显示边界。居中显示
    @OnClick(R.id.bt_fresco_centercrop)
    void bt_fresco_centercrop_click(View view){
        // 设置描述
        tvFrescoExplain.setText("保持宽高比缩小或放大，使得两边都大于或等于显示边界。居中显示");

        // 样式设置
        GenericDraweeHierarchy hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP).build();

        // 图片显示
        imageDisplay(hierarchy);
    }

    // 同centerCrop, 但居中点不是中点，而是指定的某个点,这里我设置为图片的左上角那点
    @OnClick(R.id.bt_fresco_focuscrop)
    void bt_fresco_focuscrop_click(View view){

        // 设置描述
        tvFrescoExplain.setText("同centerCrop, 但居中点不是中点，而是指定的某个点,这里我设置为图片的左上角那点");

        // 样式设置
        PointF point = new PointF(0,0);
        GenericDraweeHierarchy hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.FOCUS_CROP)
                .setActualImageFocusPoint(point).build();

        // 图片显示
        imageDisplay(hierarchy);
    }

    // 使两边都在显示边界内，居中显示。如果图尺寸大于显示边界，则保持长宽比缩小图片
    @OnClick(R.id.bt_fresco_centerinside)
    void bt_fresco_centerinside_click(View view){
        // 设置描述
        tvFrescoExplain.setText("使两边都在显示边界内，居中显示。如果图尺寸大于显示边界，则保持长宽比缩小图片");

        // 样式设置
        GenericDraweeHierarchy hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).build();

        // 图片显示
        imageDisplay(hierarchy);
    }

    // 保持宽高比，缩小或者放大，使得图片完全显示在显示边界内。居中显示
    @OnClick(R.id.bt_fresco_fitcenter)
    void bt_fresco_fitcenter_click(View view){
        // 设置描述
        tvFrescoExplain.setText("保持宽高比，缩小或者放大，使得图片完全显示在显示边界内。居中显示");

        // 样式设置
        GenericDraweeHierarchy hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).build();

        // 图片显示
        imageDisplay(hierarchy);
    }

    // 保持宽高比，缩小或者放大，使得图片完全显示在显示边界内，不居中，和显示边界左上对齐
    @OnClick(R.id.bt_fresco_fitstart)
    void bt_fresco_fitstart_click(View view){
        // 设置描述
        tvFrescoExplain.setText("保持宽高比，缩小或者放大，使得图片完全显示在显示边界内，不居中，和显示边界左上对齐");

        // 样式设置
        GenericDraweeHierarchy hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).build();

        // 图片显示
        imageDisplay(hierarchy);
    }

    // 保持宽高比，缩小或者放大，使得图片完全显示在显示边界内，不居中，和显示边界右下对齐
    @OnClick(R.id.bt_fresco_fitend)
    void bt_fresco_fitend_click(View view){
        // 设置描述
        tvFrescoExplain.setText("保持宽高比，缩小或者放大，使得图片完全显示在显示边界内，不居中，和显示边界右下对齐");

        // 样式设置
        GenericDraweeHierarchy hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_END).build();

        // 图片显示
        imageDisplay(hierarchy);
    }

    // 不保持宽高比，填充满显示边界
    @OnClick(R.id.bt_fresco_fitxy)
    void bt_fresco_fitxy_click(View view){
        // 设置描述
        tvFrescoExplain.setText("不保持宽高比，填充满显示边界");

        // 样式设置
        GenericDraweeHierarchy hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY).build();

        // 图片显示
        imageDisplay(hierarchy);
    }

    // 如要使用title mode显示, 需要设置为none
    @OnClick(R.id.bt_fresco_none)
    void bt_fresco_none_click(View view){
        // 设置描述
        tvFrescoExplain.setText("如要使用title mode显示, 需要设置为none");

        // 样式设置
        GenericDraweeHierarchy hierarchy = builder.setActualImageScaleType(null).build();

        // 图片显示
        imageDisplay(hierarchy);
    }
}
