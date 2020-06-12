package com.example.collectionsframework.c6volley;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.collectionsframework.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

// volley
public class VolleyActivity extends Activity implements View.OnClickListener {
    private TextView tv_title;
    private Button bt_volley_get;
    private Button bt_volley_post;
    private Button bt_volley_getjson;
    private Button bt_volley_imagerequest;
    private Button bt_volley_imageloader;
    private Button bt_volley_networkimageview;

    private ImageView iv_volley_result;
    private NetworkImageView iv_volley_networkimagview;
    private TextView tv_volley_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_volley);

        initView();

        initData();
    }

    private void initData() {
        tv_title.setText("Volley");
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        bt_volley_get = (Button) findViewById(R.id.bt_volley_get);
        bt_volley_post = (Button) findViewById(R.id.bt_volley_post);
        bt_volley_getjson = (Button) findViewById(R.id.bt_volley_getjson);
        bt_volley_imagerequest = (Button) findViewById(R.id.bt_volley_imagerequest);
        bt_volley_imageloader = (Button) findViewById(R.id.bt_volley_imageloader);
        bt_volley_networkimageview = (Button) findViewById(R.id.bt_volley_networkimageview);

        iv_volley_result = (ImageView) findViewById(R.id.iv_volley_result);
        iv_volley_networkimagview = (NetworkImageView) findViewById(R.id.iv_volley_networkimagview);
        tv_volley_result = (TextView) findViewById(R.id.tv_volley_result);

        bt_volley_get.setOnClickListener(this);
        bt_volley_post.setOnClickListener(this);
        bt_volley_getjson.setOnClickListener(this);
        bt_volley_imagerequest.setOnClickListener(this);
        bt_volley_imageloader.setOnClickListener(this);
        bt_volley_networkimageview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        RequestQueue requestQueue;
        String url;
        StringRequest stringRequest;
        ImageLoader imageLoader;
        switch (v.getId()) {
            // get请求
            case R.id.bt_volley_get:

                // 1 创建一个请求队列
                requestQueue = Volley.newRequestQueue(VolleyActivity.this);

                // 2 创建一个请求
                url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";

                stringRequest = new StringRequest(url, new Response.Listener<String>() {
                    // 正确接收数据回调
                    @Override
                    public void onResponse(String s) {
                        tv_volley_result.setText(s);
                    }
                    // 发生异常后的监听回调
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        tv_volley_result.setText("加载失败" + volleyError);
                    }
                });

                // 3 将创建的请求添加到请求队列中
                requestQueue.add(stringRequest);
                break;
            // post请求
            case R.id.bt_volley_post:

                // 1 创建一个请求队列
                requestQueue = Volley.newRequestQueue(VolleyActivity.this);

                // 2 创建一个post请求
                url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
                stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        tv_volley_result.setText(s);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        tv_volley_result.setText("请求失败" + volleyError);
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> map = new HashMap<String, String>();
                        //map.put("value1","param1");
                        return map;
                    }
                };

                // 3 将post请求添加到队列中
                requestQueue.add(stringRequest);
                break;
            // 获取json数据
            case R.id.bt_volley_getjson:

                // 1 创建一个请求队列
                requestQueue = Volley.newRequestQueue(VolleyActivity.this);
                
                // 2 创建一个请求
                url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        tv_volley_result.setText(jsonObject.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        tv_volley_result.setText("请求失败" + volleyError);
                    }
                });

                // 3 将创建的请求添加到请求队列中
                requestQueue.add(jsonObjectRequest);
                break;
            // imagerequest加载图片
            case R.id.bt_volley_imagerequest:
                // 1 创建一个请求队列
                requestQueue = Volley.newRequestQueue(VolleyActivity.this);

                // 2 创建一个图片的请求
                url = "http://img5.mtime.cn/mg/2019/06/15/103753.65527244_120X90X4.jpg";

            ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        // 正确接收到图片
                        iv_volley_result.setVisibility(View.VISIBLE);
                        iv_volley_result.setImageBitmap(bitmap);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        iv_volley_result.setImageResource(R.drawable.i_icon);
                    }
                });

                // 3 将请求添加到请求队列中
                requestQueue.add(imageRequest);
                break;
            // imageloader加载图片
            case R.id.bt_volley_imageloader:

                // 创建一个请求队列
                requestQueue = Volley.newRequestQueue(VolleyActivity.this);

                // 创建一个imageloader；——这里使用的是：系统的ImageCache
                //ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
                //    @Override
                //    public Bitmap getBitmap(String s) {
                //        return null;
                //    }

                //     @Override
                //    public void putBitmap(String s, Bitmap bitmap) {

                //    }
                //});

                //这里使用 自定义的 Bitmap 缓存类
                imageLoader = new ImageLoader(requestQueue, new BitmapCache());

                // 加载图片
                url = "http://img5.mtime.cn/mg/2019/06/15/103753.65527244_120X90X4.jpg";
                iv_volley_result.setVisibility(View.VISIBLE);
                ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(iv_volley_result, R.drawable.i_icon, R.drawable.i_icon);
                imageLoader.get(url, imageListener);
                break;
            // networkimageview加载图片
            case R.id.bt_volley_networkimageview:

                // 让控件显示
                iv_volley_networkimagview.setVisibility(View.VISIBLE);

                // 创建一个请求队列
                requestQueue = Volley.newRequestQueue(VolleyActivity.this);

                // 创建一个Imageloader
                imageLoader = new ImageLoader(requestQueue, new BitmapCache());

                // 默认图片和异常图片设置
                iv_volley_networkimagview.setDefaultImageResId(R.drawable.i_icon);
                iv_volley_networkimagview.setErrorImageResId(R.drawable.i_icon);

                // 加载图片
                url = "http://img5.mtime.cn/mg/2019/06/15/103753.65527244_120X90X4.jpg";
                iv_volley_networkimagview.setImageUrl(url, imageLoader);
                break;
            default:
                break;
        }
    }
}
