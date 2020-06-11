package com.example.collectionsframework.c13fresco;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.collectionsframework.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrescoModifyActivity extends Activity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.sdv_fresco_modify)
    SimpleDraweeView sdvFrescoModify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fresco_modify);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        tvTitle.setText("修改图片");
    }

    @OnClick(R.id.bt_fresco_modify)
    void bt_fresco_modify_click(View view){

        // 图片地址
        Uri uri = Uri.parse("http://c.hiphotos.baidu.com/image/pic/item/962bd40735fae6cd21a519680db30f2442a70fa1.jpg");

        // 修改图片
        Postprocessor postProcessor = new BasePostprocessor() {
            @Override
            public String getName() {
                return "postProcessor";
            }

            @Override
            public void process(Bitmap bitmap) {

                for (int x = 0; x < bitmap.getWidth(); x += 2) {

                    for (int y = 0; y < bitmap.getHeight(); y += 2) {
                        bitmap.setPixel(x, y, Color.WHITE);
                    }
                }
            }
        };

        // 创建图片请求
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setPostprocessor(postProcessor)
                .build();

        // 控制加载
        PipelineDraweeController controller = (PipelineDraweeController)Fresco.newDraweeControllerBuilder()
                .setOldController(sdvFrescoModify.getController())
                .setImageRequest(request)
                .build();

        // 加载图片
        sdvFrescoModify.setController(controller);
    }
}
