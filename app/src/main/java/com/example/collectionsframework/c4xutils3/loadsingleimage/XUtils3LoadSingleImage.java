package com.example.collectionsframework.c4xutils3.loadsingleimage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.collectionsframework.R;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * 使用 x-Utils3 请求单张照片或者gif
 */
public class XUtils3LoadSingleImage extends AppCompatActivity implements View.OnClickListener {
    private Button bt_x_single_image;
    private Button bt_x_gif_image;
    private ImageView iv_x_single_image;
    private TextView tvTitle;
    private ImageView iv_x_gif_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_utils3_load_single_image);

        iv_x_single_image = findViewById(R.id.iv_x_single_image);
        iv_x_gif_image = findViewById(R.id.iv_x_gif_image);
        tvTitle = findViewById(R.id.tv_title);

        tvTitle.setText("XUtils3加载单张图片/Gif");
        bt_x_single_image = findViewById(R.id.bt_x_single_image);
        bt_x_single_image.setOnClickListener(this);

        bt_x_gif_image = findViewById(R.id.bt_x_gif_image);
        bt_x_gif_image.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_x_single_image:
                iv_x_single_image.setVisibility(View.VISIBLE);
                getImage(iv_x_single_image);
                break;
            case R.id.bt_x_gif_image:
                iv_x_gif_image.setVisibility(View.VISIBLE);
                getGifImage(iv_x_gif_image);
                break;
            default:
                break;
        }
    }

    private void getImage(ImageView imageView) {
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(80), DensityUtil.dip2px(80))
                //设置圆角
                .setRadius(DensityUtil.dip2px(5))
                //.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.mipmap.ic_launcher).setFailureDrawableId(R.mipmap.ic_launcher).build();
        x.image().bind(imageView, "http://img31.mtime.cn/mg/2016/09/02/113643.51941003.jpg", imageOptions);
    }


    /**
     * 加载 gif；和上个方法的区别在于：.setIgnoreGif(false)
     */
    private void getGifImage(ImageView gif_imageView) {
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(80), DensityUtil.dip2px(80))
                //设置圆角
                .setRadius(DensityUtil.dip2px(5))
                //是否忽略gif图。false表示不忽略。不写这句，默认是true
                .setIgnoreGif(false)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setFailureDrawableId(R.mipmap.ic_launcher).build();
        // x.image().bind(imageView,"http://img31.mtime.cn/mg/2016/09/02/113643.51941003.jpg",imageOptions);
        x.image().bind(gif_imageView, "https://n.sinaimg.cn/tech/transform/506/w324h182/20200610/4605-iuvaazn7228574.gif", imageOptions); // x.image().bind(iv_icon, Environment.getExternalStorageDirectory()+"/test.gif", imageOptions); // x.image().bind(iv_icon, "assets://test.gif", imageOptions);

        /**
         * 加载本地 gif
         */
        // x.image().bind(gif_imageView, "file:///sdcard/test.gif", imageOptions); }
    }
}