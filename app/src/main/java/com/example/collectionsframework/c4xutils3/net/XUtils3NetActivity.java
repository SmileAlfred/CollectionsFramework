package com.example.collectionsframework.c4xutils3.net;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collectionsframework.R;

import org.xutils.common.Callback;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

@ContentView(R.layout.activity_xutils3_net)
public class XUtils3NetActivity extends Activity {

    @ViewInject(R.id.tv_result)
    private TextView textView;

    @ViewInject(R.id.progressbar)
    private ProgressBar progressbar;

    @ViewInject(R.id.tv_title)
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_xutils3_net);
        x.view().inject(this);

        title.setText("xUtils3的网络模块");
    }

    @Event(value = {R.id.btn_get_post, R.id.btn_downloadfile, R.id.btn_uploadfile})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.btn_get_post:
                getAndPostNet();
                break;
            case R.id.btn_downloadfile:
                downloadFile();
                break;
            case R.id.btn_uploadfile:
                uploadFile();
                break;
            default:
                break;
        }
    }

    /**
     * 文件上传
     */
    private void uploadFile() {
        RequestParams params = new RequestParams("http://192.168.0.3:8080/FileUpload/FileUploadServlet");
        //以表单方式上传
        params.setMultipart(true);
        //设置上传文件的路径
        params.addBodyParameter("File", new File(getFilesDir() + "/xUtils3/480.mp4"), null, "oppo.mp4");
        x.http().post(params, new Callback.ProgressCallback<File>() {
            /**
             * 当下载成功的时候回调这个方法，并且把下载到哪个路径回传过来
             * @param file
             */
            @Override
            public void onSuccess(File file) {
                Log.e("TAG", "onSuccess==" + file.toString());
                Toast.makeText(XUtils3NetActivity.this, "onSuccess==" + file.toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", "onError==" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("TAG", "onCancelled==" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                Log.e("TAG", "onFinished==");
            }

            @Override
            public void onWaiting() {
                Log.e("TAG", "onWaiting==");
            }

            @Override
            public void onStarted() {
                Log.e("TAG", "onStarted==");
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                progressbar.setMax((int) total);
                progressbar.setProgress((int) current);
                Log.e("TAG", "onLoading==" + current + "/" + total + ",isDownloading==" + isDownloading);
            }
        });
    }


    private void downloadFile() {
        RequestParams params = new RequestParams("http://vfx.mtime.cn/Video/2016/09/15/mp4/160915092608935956_480.mp4");
        //设置保存数据
        params.setSaveFilePath(getFilesDir() + "/xUtils3/480.mp4");
        //设置是否可以立即取消下载
        params.setCancelFast(true);
        //设置是否自动根据头信息命名;选 true 那么命名为：160915092608935956_480.mp4
        params.setAutoRename(false);
        //设置断点续传
        params.setAutoResume(true);

        //自定义线程池,有效的值范围[1, 3], 设置为3时, 可能阻塞图片加载.
        params.setExecutor(new PriorityExecutor(3, true));
        //核心代码：
        x.http().get(params, new Callback.ProgressCallback<File>() {
            /**
             * 当下载成功的时候回调这个方法，并且把下载到哪个路径回传过来
             * @param file
             */
            @Override
            public void onSuccess(File file) {
                Log.e("TAG", "onSuccess==" + file.toString());
                Toast.makeText(XUtils3NetActivity.this, "onSuccess==" + file.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", "onError==" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("TAG", "onCancelled==" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                Log.e("TAG", "onFinished==");
            }

            @Override
            public void onWaiting() {
                Log.e("TAG", "onWaiting==");
            }

            @Override
            public void onStarted() {
                Log.e("TAG", "onStarted==");
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                progressbar.setMax((int) total);
                progressbar.setProgress((int) current);
                Log.e("TAG", "onLoading==" + current + "/" + total + ",isDownloading==" + isDownloading);
            }
        });
    }

    private void getAndPostNet() {
        //1.Get请求
        //2.Post请求;区别在于；只需要把x.http().post()换成get()即可
        RequestParams params = new RequestParams("http://api.m.mtime.cn/PageSubArea/TrailerList.api");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Log.e("TAG", "xUtis3联网请求成功==" + result);
                //textView.append("\nGet请求的结果:\n" + result);
                textView.append("\nPost请求的结果:\n" + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", "xUtis3联网请求失败==" + ex.getMessage());
                textView.setText("xUtis3联网请求失败==" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("TAG", "onCancelled==" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                Log.e("TAG", "onFinished==");
            }
        });
    }
}
