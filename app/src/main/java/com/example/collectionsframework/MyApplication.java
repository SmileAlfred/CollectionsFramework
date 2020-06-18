package com.example.collectionsframework;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
//import com.zxy.recovery.core.Recovery;

import org.xutils.x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyApplication extends Application {
    public static List<?> images = new ArrayList<>();
    public static List<String> titles = new ArrayList<>();
    //屏幕的高
    public static int H;

    @Override
    public void onCreate() {
        super.onCreate();
        //xUtils3初始化
        x.Ext.init(this);
        // 是否输出debug日志, 开启debug会影响性能.
        x.Ext.setDebug(true);
        // 初始化Imageloader
        initImageloader(this);
        // 初始化Fresco
        initFresco();

        //初始化Banner
        initBanner();

    }

    private void initBanner() {
        H = getScreenH(this);
        Fresco.initialize(this);

        //当软件出现BUG时，自动状态还原的框架——开发时不用
        //Recovery.getInstance()
        //        .debug(true)
        //        .recoverInBackground(false)
        //        .recoverStack(true)
        //        .mainPage(MainActivity.class)
        //        .init(this);


        String[] urls = getResources().getStringArray(R.array.url4);
        String[] tips = getResources().getStringArray(R.array.title);
        List list = Arrays.asList(urls);
        images = new ArrayList(list);
        titles = Arrays.asList(tips);
    }

    /**
     * 得到屏幕的高
     *
     * @param aty
     * @return
     */
    public int getScreenH(Context aty) {
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    private void initFresco() {
        Fresco.initialize(this);
    }

    private void initImageloader(Context context) {

        // 初始化参数
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)               // 线程优先级
                .denyCacheImageMultipleSizesInMemory()                  // 当同一个Uri获取不同大小的图片，缓存到内存时，只缓存一个。默认会缓存多个不同的大小的相同图片
                .discCacheFileNameGenerator(new Md5FileNameGenerator()) // 将保存的时候的URI名称用MD5
                .tasksProcessingOrder(QueueProcessingType.LIFO)         // 设置图片下载和显示的工作队列排序
                .writeDebugLogs()                                       // 打印debug log
                .build();

        // 全局初始化此配置
        ImageLoader.getInstance().init(config);
    }
}
