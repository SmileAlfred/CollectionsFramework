package com.example.collectionsframework.c13fresco;

import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.collectionsframework.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrescoListenerActivity extends Activity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.sdv_fresco_listener)
    SimpleDraweeView sdvFrescoListener;
    @BindView(R.id.tv_fresco_listener)
    TextView tvFrescoListener;
    @BindView(R.id.tv_fresco_listener2)
    TextView tvFrescoListener2;
    private ControllerListener controllerListener = new BaseControllerListener<ImageInfo>(){
        // 加载图片完毕
        @Override
        public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
            super.onFinalImageSet(id, imageInfo, animatable);

            if (imageInfo == null) {
                return;
            }

            // 获取图片的质量
            QualityInfo qualityInfo = imageInfo.getQualityInfo();

            tvFrescoListener.setText("Final image received! " +
                    "\nSize: " + imageInfo.getWidth()
                    + "x" + imageInfo.getHeight()
                    + "\nQuality level: " + qualityInfo.getQuality()
                    + "\ngood enough: " + qualityInfo.isOfGoodEnoughQuality()
                    + "\nfull quality: " + qualityInfo.isOfFullQuality());
        }

        // 渐进式加载图片回调
        @Override
        public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
            super.onIntermediateImageSet(id, imageInfo);

            tvFrescoListener2.setText("IntermediateImageSet image receiced");
        }

        // 加载图片失败
        @Override
        public void onFailure(String id, Throwable throwable) {
            super.onFailure(id, throwable);


            tvFrescoListener.setText("Error loading" + id);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fresco_listener);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        tvTitle.setText("图片加载监听");
    }

    @OnClick(R.id.bt_fresco_listener)
    void bt_fresco_listener(View view){

        // 加载图片质量配置
        ProgressiveJpegConfig jpegConfig = new ProgressiveJpegConfig() {
            @Override
            public int getNextScanNumberToDecode(int scanNumber) {
                return scanNumber + 2;
            }

            @Override
            public QualityInfo getQualityInfo(int scanNumber) {
                boolean isGoodEnough = (scanNumber >= 5);

                return ImmutableQualityInfo.of(scanNumber, isGoodEnough, false);
            }
        };

        ImagePipelineConfig.newBuilder(this).setProgressiveJpegConfig(jpegConfig).build();

        // 图片地址
        Uri uri = Uri.parse("http://h.hiphotos.baidu.com/zhidao/pic/item/58ee3d6d55fbb2fbac4f2af24f4a20a44723dcee.jpg");

        // 图片请求
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();

        // 图片加载的控制
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(sdvFrescoListener.getController())
                .setImageRequest(request)
                .setControllerListener(controllerListener)
                .build();

        // 加载图片
        sdvFrescoListener.setController(controller);
    }
}
