package com.example.collectionsframework.c13fresco;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.collectionsframework.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrescoResizeActivity extends Activity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.sdv_fresco_resize)
    SimpleDraweeView sdvFrescoResize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fresco_resize);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        tvTitle.setText("图片缩放和旋转");
    }

    // 修内存中改图片大小
    @OnClick(R.id.bt_fresco_resize)
    void bt_fresco_resize_click(View view){

        // 图片地址
        Uri uri = Uri.parse("http://c.hiphotos.baidu.com/image/pic/item/962bd40735fae6cd21a519680db30f2442a70fa1.jpg");

        // 图片的请求
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(50,50))
                .build();

        // 控制图片的加载
        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setOldController(sdvFrescoResize.getController())
                .setImageRequest(request)
                .build();

        // 加载图片
        sdvFrescoResize.setController(controller);
    }

    // 旋转图片
    @OnClick(R.id.bt_fresco_rotate)
    void bt_fresco_rotate_click(View view){

        Uri uri = Uri.parse("http://c.hiphotos.baidu.com/image/pic/item/962bd40735fae6cd21a519680db30f2442a70fa1.jpg");
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setAutoRotateEnabled(true)
                .build();

        // 控制图片的加载
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(sdvFrescoResize.getController())
                .setImageRequest(request)
                .build();


        // 加载图片
        sdvFrescoResize.setController(controller);
    }
}
