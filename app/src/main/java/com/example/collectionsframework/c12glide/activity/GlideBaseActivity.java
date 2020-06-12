package com.example.collectionsframework.c12glide.activity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.example.collectionsframework.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GlideBaseActivity extends Activity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_glide_1)
    TextView tvGlide1;
    @BindView(R.id.iv_glide_1)
    ImageView ivGlide1;
    @BindView(R.id.tv_glide_2)
    TextView tvGlide2;
    @BindView(R.id.iv_glide_2)
    ImageView ivGlide2;
    @BindView(R.id.tv_glide_3)
    TextView tvGlide3;
    @BindView(R.id.iv_glide_3)
    ImageView ivGlide3;
    @BindView(R.id.tv_glide_4)
    TextView tvGlide4;
    @BindView(R.id.iv_glide_4)
    ImageView ivGlide4;
    @BindView(R.id.tv_glide_5)
    TextView tvGlide5;
    @BindView(R.id.iv_glide_5)
    ImageView ivGlide5;
    @BindView(R.id.tv_glide_6)
    TextView tvGlide6;
    @BindView(R.id.iv_glide_6)
    ImageView ivGlide6;
    @BindView(R.id.tv_glide_7)
    TextView tvGlide7;
    @BindView(R.id.iv_glide_7)
    ImageView ivGlide7;
    @BindView(R.id.tv_glide_8)
    TextView tvGlide8;
    @BindView(R.id.iv_glide_8)
    ImageView ivGlide8;
    @BindView(R.id.tv_glide_9)
    TextView tvGlide9;
    @BindView(R.id.iv_glide_9)
    ImageView ivGlide9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_glide_base);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        tvTitle.setText("Glide基本使用");

        //（1）加载网络图片
        tvGlide1.setText("（1）加载网络图片");
        Glide.with(this).load("https://profile.csdnimg.cn/B/8/7/3_liusaisaiv1").into(ivGlide1);

        //（2）加载资源图片
        tvGlide2.setText("（2）加载资源图片");
        Glide.with(this).load(R.drawable.i_icon).into(ivGlide2);

        //（3）加载本地图片
        tvGlide3.setText("（3）加载本地图片");
        String path = getFilesDir() + "/i_icon.jpg";
        File file = new File(path);
        Uri uri = Uri.fromFile(file);
        Glide.with(this).load(uri).into(ivGlide3);

        // （4）加载网络gif
        tvGlide4.setText("（4）加载网络gif");
        String gifUrl = "http://b.hiphotos.baidu.com/zhidao/pic/item/faedab64034f78f066abccc57b310a55b3191c67.jpg";
        Glide.with(this).load(gifUrl).placeholder(R.mipmap.ic_launcher).into(ivGlide4);

        // （5）加载资源gif
        tvGlide5.setText("（5）加载资源gif");
        Glide.with(this).load(R.drawable.loading).placeholder(R.mipmap.ic_launcher).into(ivGlide5);

        //（6）加载本地gif
        tvGlide6.setText("（6）加载本地gif");
        String gifPath = getFilesDir() + "/i_icon.gif";
        File gifFile = new File(gifPath);
        Glide.with(this).load(gifFile).placeholder(R.mipmap.ic_launcher).into(ivGlide6);

        //（7）加载本地小视频和快照
        tvGlide7.setText("（7）加载本地小视频和快照");
        String videoPath = getFilesDir() + "/okhttp-utils-test.mp4";
        File videoFile = new File(videoPath);
        Log.e("TAG", "Glide + 本地视频缩略图存在吗？: " + (videoFile == null) );
        Glide.with(this).load(Uri.fromFile(videoFile)).placeholder(R.mipmap.ic_launcher).into(ivGlide7);

        //（8）设置缩略图比例,然后，先加载缩略图，再加载原图
        tvGlide8.setText("（8）设置缩略图比例,然后，先加载缩略图，再加载原图");
        String urlPath = getFilesDir() + "/i_icon.jpg";
        Glide.with(this).load(new File(urlPath)).thumbnail(0.1f).centerCrop().placeholder(R.mipmap.ic_launcher).into(ivGlide8);

        //（9）先建立一个缩略图对象，然后，先加载缩略图，再加载原图
        tvGlide9.setText("（9）先建立一个缩略图对象，然后，先加载缩略图，再加载原图");
        RequestBuilder<Drawable> thumbnailRequest = Glide
                .with(this)
                .applyDefaultRequestOptions(RequestOptions.noTransformation())
                .load("小图网络地址");
        Glide.with(this).applyDefaultRequestOptions(RequestOptions.noTransformation()).load(Uri.fromFile(videoFile)).thumbnail(thumbnailRequest).centerCrop().placeholder(R.mipmap.ic_launcher).into(ivGlide9);
    }
}
