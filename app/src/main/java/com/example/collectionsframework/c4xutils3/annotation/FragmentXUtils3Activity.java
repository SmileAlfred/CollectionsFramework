package com.example.collectionsframework.c4xutils3.annotation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.collectionsframework.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 在 FragmentActivity 的布局中添加 Fragment
 * 这里使用的 frameLayout;所以切换Fragment后，原来的TextView并没有消失；
 */
@ContentView(R.layout.activity_xutils3_fragment)
public class FragmentXUtils3Activity extends FragmentActivity {

    @ViewInject(R.id.tv_title)
    private TextView textView;
    @ViewInject(R.id.btn_switch_fragment)
    private Button btn_switch_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_xutils3_fragment);
        x.view().inject(this);

        textView.setText("在Fragment中使用注解");
    }

    @Event(value = R.id.btn_switch_fragment)
    private void SetEvent(View view) {
        switch (view.getId()) {
            case R.id.btn_switch_fragment:
                //1.得到FragmentManger
                FragmentManager fm = getSupportFragmentManager();
                //2.开启事务
                FragmentTransaction tt = fm.beginTransaction();
                //3.替换Fragment
                Toast.makeText(this, "我FragmentXUtils3Activity被点击了", Toast.LENGTH_SHORT).show();
                tt.replace(R.id.fl_content, new DemoFragment());
                //4.提交
                tt.commit();
                break;
            default:
                break;
        }
    }
}