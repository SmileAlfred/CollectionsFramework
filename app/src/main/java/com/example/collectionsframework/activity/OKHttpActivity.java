    package com.example.collectionsframework.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collectionsframework.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 作用：okhttp:implementation("com.squareup.okhttp3:okhttp:4.7.2")
 * BUG：上传文件时，显示没有权限的BUG，Permission denied
 * 解决：使用API22的模拟器（Android5.1）就可以了，@#￥%……&*——
 * 更高的API会对网络(服务器)进行校验，检查是否有危险；
 */
public class OKHttpActivity extends Activity implements View.OnClickListener {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    /**
     * get请求
     */
    private static final int GET = 1;
    /**
     * post请求
     */
    private static final int POST = 2;
    private static final String TAG = OKHttpActivity.class.getSimpleName();
    private Button btn_get_post;
    private TextView tv_result;
    private Button btn_get_okhttputils;
    private Button btn_downloadfile;
    private ProgressBar mProgressBar;
    private Button btn_uploadfile;
    private Button btn_image;
    private Button btn_image_list;
    private ImageView iv_icon;
    private OkHttpClient client = new OkHttpClient();

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GET:
                case POST:
                    //获取数据
                    tv_result.setText((String) msg.obj);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        findViews();
    }

    private void findViews() {
        btn_get_post = (Button) findViewById(R.id.btn_get_post);
        tv_result = (TextView) findViewById(R.id.tv_result);
        btn_get_okhttputils = (Button) findViewById(R.id.btn_get_okhttputils);
        btn_downloadfile = (Button) findViewById(R.id.btn_downloadfile);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        btn_uploadfile = (Button) findViewById(R.id.btn_uploadfile);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        btn_image = (Button) findViewById(R.id.btn_image);
        btn_image_list = (Button) findViewById(R.id.btn_image_list);

        //设置点击事件
        btn_get_post.setOnClickListener(this);
        btn_get_okhttputils.setOnClickListener(this);
        btn_downloadfile.setOnClickListener(this);
        btn_uploadfile.setOnClickListener(this);
        btn_image.setOnClickListener(this);
        btn_image_list.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //使用原生的okhttp请求网络数据，get和post
            case R.id.btn_get_post:
                tv_result.setText("");
                //点击事件
                getDataFromPost();
                break;
            case R.id.btn_get_okhttputils:
                //getDataGetByOkhttpUtils();
                getDataPostByOkhttpUtils();
                break;
            //下载文件
            case R.id.btn_downloadfile:
                downloadFile();
                break;
            //文件上传
            case R.id.btn_uploadfile:
                multiFileUpload();
                break;
            //请求单张图片
            case R.id.btn_image:
                getImage();
                break;
            //请求列表中的图片
            case R.id.btn_image_list:
                Intent intent = new Intent(OKHttpActivity.this, OKHttpListActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * 使用get请求网络数据
     */
    private void getDataFromGet() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String result = get("http://api.m.mtime.cn/PageSubArea/TrailerList.api");
                    Log.e("TAG", result);
                    Message msg = Message.obtain();
                    msg.what = GET;
                    msg.obj = result;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    /**
     * 使用post请求网络数据
     */
    private void getDataFromPost() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String result = post("http://api.m.mtime.cn/PageSubArea/TrailerList.api", "");
                    Log.e("TAG", result);
                    Message msg = Message.obtain();
                    msg.what = POST;
                    msg.obj = result;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }


    /**
     * get请求：Get a URL：https://square.github.io/okhttp/
     *
     * @param url 网络连接
     * @return
     * @throws IOException
     */
    private String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    /**
     * okhttp3的post请求：Post to a Server：https://square.github.io/okhttp/
     *
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    private String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    /**
     * 使用okhttp-utils的get请求网络文本数据
     */
    public void getDataGetByOkhttpUtils() {
        String url = "http://www.zhiyun-tech.com/App/Rider-M/changelog-zh.txt";
        //url="http://www.391k.com/api/xapi.ashx/info.json?key=bd_hyrzjjfb4modhj&size=10&page=1";
        url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        OkHttpUtils
                .get()
                .url(url)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }

    /**
     * 使用okhttp-utils的post请求网络文本数据：
     * okhttp-utils ：https://github.com/ho ngyangAndroid/okhttputils
     */
    public void getDataPostByOkhttpUtils() {
        String url = "http://www.zhiyun-tech.com/App/Rider-M/changelog-zh.txt";
        //url="http://www.391k.com/api/xapi.ashx/info.json?key=bd_hyrzjjfb4modhj&size=10&page=1";
        url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        OkHttpUtils
                .post()
                .url(url)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }


    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {
            setTitle("loading...");
        }

        @Override
        public void onAfter(int id) {
            setTitle("Sample-okHttp");
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
            tv_result.setText("onError:" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e(TAG, "onResponse：complete");
            tv_result.setText("onResponse:" + response);

            switch (id) {
                case 100:
                    Toast.makeText(OKHttpActivity.this, "http", Toast.LENGTH_SHORT).show();
                    break;
                case 101:
                    Toast.makeText(OKHttpActivity.this, "https", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            Log.e(TAG, "inProgress:" + progress);
            mProgressBar.setProgress((int) (100 * progress));
        }
    }


    /**
     * 使用okhttp-utils下载大文件
     */
    public void downloadFile() {
        String url = "http://vfx.mtime.cn/Video/2019/06/26/mp4/190626111517361726.mp4";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new FileCallBack(getFilesDir().getAbsolutePath(), "okhttp-utils-test.mp4") {
                    @Override
                    public void onBefore(Request request, int id) {

                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        mProgressBar.setProgress((int) (100 * progress));
                        Log.e(TAG, "inProgress :" + (int) (100 * progress));
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError :" + e.getMessage());
                    }

                    @Override
                    public void onResponse(File file, int id) {
                        Log.e(TAG, "onResponse :" + file.getAbsolutePath());
                    }
                });
    }


    /**
     * 使用okhttp-utils上传多个或者单个文件；
     * 长传文件报错：一是报没有权限实际已经给了；二是报无法运行，解释是模拟器版本高，实际上并不高。
     */
    public void multiFileUpload() {
        String mBaseUrl = "http://192.168.0.4:8080/FileUpload/FileUploadServlet";
        File file = new File(getFilesDir(), "afu.png");
        File file2 = new File(getFilesDir(), "test.txt");
        if (!file.exists() || !file2.exists()) {
            Toast.makeText(OKHttpActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("username", "刘学习");
        params.put("password", "123");

        String url = mBaseUrl;
        OkHttpUtils.post()
                .addFile("mFile", "server_afu.png", file)
                .addFile("mFile", "server_test.txt", file2)
                .url(url)
                .params(params)
                .build()
                .execute(new MyStringCallback());
    }

    public void getImage() {
        tv_result.setText("");
        String url = "https://profile.csdnimg.cn/B/8/7/3_liusaisaiv1";
        OkHttpUtils
                .get()
                .url(url)
                .tag(this)
                .build()
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tv_result.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int id) {
                        Log.e("TAG", "onResponse：complete");
                        iv_icon.setImageBitmap(bitmap);
                    }
                });
    }
}
