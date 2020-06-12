package com.example.collectionsframework.c13fresco;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import com.example.collectionsframework.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrescoMultiActivity extends Activity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.sdv_fresco_multi)
    SimpleDraweeView sdvFrescoMulti;
    @BindView(R.id.multi_desc)
    TextView multi_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fresco_multi);
        ButterKnife.bind(this);

        initData();
    }


    private void initData() {
        tvTitle.setText("多图请求及图片复用");
    }

    // 先显示低分辨率的图，然后是高分辨率的图
    @OnClick(R.id.bt_fresco_multiImg)
    void bt_fresco_multiImg_click(View view){

        // 图片地址
        Uri lowUri = Uri.parse("https://profile.csdnimg.cn/B/8/7/3_liusaisaiv1");
        Uri highUri = Uri.parse("http://c.hiphotos.baidu.com/image/pic/item/962bd40735fae6cd21a519680db30f2442a70fa1.jpg");

        // 控制加载图片
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setLowResImageRequest(ImageRequest.fromUri(lowUri))
                .setImageRequest(ImageRequest.fromUri(highUri))
                .build();

        // 加载图片
        sdvFrescoMulti.setController(controller);
    }

    // 本地缩略图预览
    @OnClick(R.id.bt_fresco_thumbnailImg)
    void bt_fresco_thumbnailImg_click(View view){

        // 图片地址
        Uri uri = Uri.fromFile(new File(getFilesDir() +"/i_icon.jpg"));
        // 加载图片的请求
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setLocalThumbnailPreviewsEnabled(true)
                .build();

        // 控制图片的加载
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .build();

        // 加载图片
        sdvFrescoMulti.setController(controller);
    }

    // 本地图片复用
    @OnClick(R.id.bt_fresco_multiplexImg)
    void bt_fresco_multiplexImg_click(View view){
        multi_desc.setVisibility(View.VISIBLE);
        //本地图片的复用
        //在请求之前，还会去内存中请求一次图片，没有才会先去本地，最后去网络uri
        //本地准备复用图片的uri  如果本地这个图片不存在，会自动去加载下一个uri

        // 请求加载图片
        Uri uri1 = Uri.fromFile(new File(getFilesDir()+"/i_icon.jpg"));
        //图片的网络uri
        Uri uri2 = Uri.parse("http://c.hiphotos.baidu.com/image/pic/item/962bd40735fae6cd21a519680db30f2442a70fa1.jpg");

        ImageRequest request1 = ImageRequest.fromUri(uri1);
        ImageRequest request2 = ImageRequest.fromUri(uri2);
        ImageRequest[] requests = {request1, request2};

        // 控制加载图片
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setFirstAvailableImageRequests(requests)
                .setOldController(sdvFrescoMulti.getController())
                .build();

        // 加载图片
        sdvFrescoMulti.setController(controller);
    }
}
