package com.atguigu.android.android_pulltorefresh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.atguigu.android.R;

/**
 * 作者：尚硅谷-杨光福 on 11/5/2016 10:05
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：PullToRefresh主入口
 */
public class PullToRefreshMainActivity extends Activity implements View.OnClickListener {

    private Button listview;
    private Button gridview;
    private Button fragment;
    private Button viewpager;
    private Button viewpager2;
    private Button webview;
    private TextView tv_title;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2016-11-05 10:08:50 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        setContentView(R.layout.activity_pulltorefresh_main);
        listview = (Button)findViewById( R.id.listview );
        gridview = (Button)findViewById( R.id.gridview );
        fragment = (Button)findViewById( R.id.fragment );
        viewpager = (Button)findViewById( R.id.viewpager );
        viewpager2 = (Button)findViewById( R.id.viewpager2 );
        webview = (Button)findViewById( R.id.webview );
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("Android-PullToRefresh");

        listview.setOnClickListener( this );
        gridview.setOnClickListener( this );
        fragment.setOnClickListener( this );
        viewpager.setOnClickListener( this );
        viewpager2.setOnClickListener( this );
        webview.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2016-11-05 10:08:50 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == listview ) {
            // Handle clicks for listview
            startActivity(new Intent(this,PullToRefreshListActivity.class));
        } else if ( v == gridview ) {
            startActivity(new Intent(this,PullToRefreshGridActivity.class));
            // Handle clicks for gridview
        } else if ( v == fragment ) {
            startActivity(new Intent(this,PullToRefreshListFragmentActivity.class));
            // Handle clicks for fragment
        } else if ( v == viewpager ) {
            startActivity(new Intent(this,PullToRefreshListInViewPagerActivity.class));
            // Handle clicks for viewpager
        } else if ( v == viewpager2 ) {
            startActivity(new Intent(this,PullToRefreshViewPagerActivity.class));
            // Handle clicks for viewpager2
        } else if ( v == webview ) {
            // Handle clicks for webview
            startActivity(new Intent(this,PullToRefreshWebViewActivity.class));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViews();
    }
}
