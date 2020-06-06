package com.example.collectionsframework;

import android.app.Application;

import org.xutils.x;

/**
 * 作用：代表整个软件
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //xUtils3初始化
        x.Ext.init(this);
        // 是否输出debug日志, 开启debug会影响性能.
        x.Ext.setDebug(true);
    }
}
