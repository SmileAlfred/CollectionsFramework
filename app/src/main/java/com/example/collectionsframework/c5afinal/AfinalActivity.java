package com.atguigu.android.afinal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.android.R;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.io.File;
import java.io.FileNotFoundException;


// Afinal框架讲解
public class AfinalActivity extends FinalActivity {

    @ViewInject(id=R.id.bt_afinal_loadimage,click = "bt_afinal_loadimage_click")
    private Button bt_afinal_loadimage;

    @ViewInject(id = R.id.bt_afinal_gettext, click = "bt_afinal_gettext_click")
    private Button bt_afinal_gettext;

    @ViewInject(id = R.id.bt_afinal_loadfile, click = "bt_afinal_loadfile_click")
    private Button bt_afinal_loadfile;

    @ViewInject(id = R.id.bt_afinal_updatetext, click = "bt_afinal_updatetext_click")
    private Button bt_afinal_updatetext;

    @ViewInject(id = R.id.iv_afinal)
    private ImageView iv_afinal;

    @ViewInject(id = R.id.tv_afinal_result)
    private TextView tv_afinal_result;

    @ViewInject(id = R.id.tv_title)
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_afinal);

        initData();
    }

    private void initData() {
        tv_title.setText("Afinal");
    }

    // 加载图片点击事件处理
    public void bt_afinal_loadimage_click(View view){
        FinalBitmap finalBitmap = FinalBitmap.create(this);

        // 网络请求图片时默认显示的图片
        finalBitmap.configLoadingImage(R.drawable.atguigu_logo);

        // 开始加载图片
        finalBitmap.display(iv_afinal,"http://img5.mtime.cn/mg/2016/10/11/160347.30270341.jpg");
    }

    // 请求文本的点击事件处理
    public void bt_afinal_gettext_click(View view){
        FinalHttp finalHttp = new FinalHttp();

        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        finalHttp.get(url, new AjaxCallBack<Object>() {
            @Override
            public void onStart() {
                tv_afinal_result.setText("开始加载");
                super.onStart();
            }

            @Override
            public void onSuccess(Object o) {
                // 显示加载成功后的结果
                tv_afinal_result.setText(o.toString());
                super.onSuccess(o);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                tv_afinal_result.setText("加载失败");

                super.onFailure(t, errorNo, strMsg);
            }
        });
    }

    // 加载文件的点击事件处理
    public void bt_afinal_loadfile_click(View view){
        FinalHttp finalHttp = new FinalHttp();

        // 请求网络资源的地址
        String url = "http://vfx.mtime.cn/Video/2016/10/11/mp4/161011092841270064_480.mp4";

        // 存放视频文件到本地位置
        String target = getFilesDir()+"/afinalmusic.mp4";

        finalHttp.download(url, target, new AjaxCallBack<File>() {
            @Override
            public void onSuccess(File file) {
                tv_afinal_result.setText("下载文件成功");
                super.onSuccess(file);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                tv_afinal_result.setText("下载文件失败");
                super.onFailure(t, errorNo, strMsg);
            }

            @Override
            public void onStart() {
                tv_afinal_result.setText("开始下载");
                super.onStart();
            }
        });
    }

    // 上传文本点击事件的处理
    public void bt_afinal_updatetext_click(View view){
        FinalHttp finalHttp = new FinalHttp();

        // 文件上传到服务器的位置
        String url  = "http://192.168.0.103:8080/FileUpload/FileUploadServlet";

        AjaxParams params = new AjaxParams();
        // 获取要上传的本地资源
        try {
            params.put("File",new File(getFilesDir()+"/afinalmusic.mp4"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        finalHttp.post(url, params, new AjaxCallBack<Object>() {
            @Override
            public void onStart() {
                tv_afinal_result.setText("开始上传");
                super.onStart();
            }

            @Override
            public void onSuccess(Object o) {
                tv_afinal_result.setText("上传成功");
                super.onSuccess(o);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                tv_afinal_result.setText("上传失败");
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }
}
