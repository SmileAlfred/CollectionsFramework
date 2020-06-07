package com.example.collectionsframework.c4xutils3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collectionsframework.R;
import com.example.collectionsframework.c4xutils3.annotation.FragmentXUtils3Activity;
import com.example.collectionsframework.c4xutils3.net.XUtils3NetActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 初始布局和view页面；这里需要一个全局的 application
 * 代替 setContentView(R.layout.activity_xutils3_main);
 * PS：这里请求单张和多张图片未实现
 */
@ContentView(R.layout.activity_xutils3_main)
public class XUtils3MainActivity extends Activity {

    @ViewInject(R.id.tv_title)
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_xutils3_main);
        x.view().inject(XUtils3MainActivity.this);

        //设置标题
        textView.setText("xUtils3的使用");
    }

    /**
     * 代替点击事件
     *
     * @param view
     */
    @Event(value = {R.id.btn_annotation, R.id.btn_net, R.id.btn_image, R.id.btn_image_list})
    private void getEvent(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_annotation:
                intent = new Intent(XUtils3MainActivity.this, FragmentXUtils3Activity.class);
                break;
            case R.id.btn_net:
                intent = new Intent(XUtils3MainActivity.this, XUtils3NetActivity.class);
                break;
            case R.id.btn_image:
                Toast.makeText(XUtils3MainActivity.this, "加载单张图片未实现", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_image_list:
                Toast.makeText(XUtils3MainActivity.this, "加载列表图片未实现", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}