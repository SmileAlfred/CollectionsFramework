package com.example.collectionsframework.c16jiecaovideoplayer.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.AbsoluteLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.collectionsframework.R;
import com.squareup.picasso.Picasso;

import fm.jiecao.jcvideoplayer_lib.JCUtils;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Nathen on 16/10/13.
 */

public class WebViewActivity extends AppCompatActivity {
    WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setTitle("AboutWebView");
        setContentView(R.layout.activity_webview);
        mWebView = (WebView) findViewById(R.id.webview);
        //设置值JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);
        //添加JavascriptInterface
        mWebView.addJavascriptInterface(new JCCallBack(), "jcvd");
        mWebView.loadUrl("file:///android_asset/jcvd.html");
    }

    public class JCCallBack {

        @JavascriptInterface
        public void adViewJieCaoVideoPlayer(final int width, final int height, final int top, final int left, final int index) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (index == 0) {
                        //代码实例化
                        JCVideoPlayerStandard webVieo = new JCVideoPlayerStandard(WebViewActivity.this);


                        webVieo.setUp("http://vfx.mtime.cn/Video/2019/06/16/mp4/190616155507259516.mp4",
                                JCVideoPlayer.SCREEN_LAYOUT_LIST, "攀登者 “点将”版预告片");
                        Picasso.get()
                                .load("http://img5.mtime.cn/mg/2019/06/16/155142.11248460_120X90X4.jpg")
                                .into(webVieo.thumbImageView);
                        ViewGroup.LayoutParams ll = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        AbsoluteLayout.LayoutParams layoutParams = new AbsoluteLayout.LayoutParams(ll);
                        layoutParams.y = JCUtils.dip2px(WebViewActivity.this, top);
                        layoutParams.x = JCUtils.dip2px(WebViewActivity.this, left);
                        layoutParams.height = JCUtils.dip2px(WebViewActivity.this, height);
                        layoutParams.width = JCUtils.dip2px(WebViewActivity.this, width);
                        //添加到webview中
                        mWebView.addView(webVieo, layoutParams);
                    } else {
                        //代码实例化
                        JCVideoPlayerStandard webVieo = new JCVideoPlayerStandard(WebViewActivity.this);
                        webVieo.setUp("http://vfx.mtime.cn/Video/2019/06/05/mp4/190605101703931259.mp4",
                                JCVideoPlayer.SCREEN_LAYOUT_LIST, "黑衣人：全球追缉 星际对决终极预告");
                        Picasso.get()
                                .load("http://img5.mtime.cn/mg/2019/06/05/101656.54539982_120X90X4.jpg")
                                .into(webVieo.thumbImageView);
                        ViewGroup.LayoutParams ll = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        AbsoluteLayout.LayoutParams layoutParams = new AbsoluteLayout.LayoutParams(ll);
                        layoutParams.y = JCUtils.dip2px(WebViewActivity.this, top);
                        layoutParams.x = JCUtils.dip2px(WebViewActivity.this, left);
                        layoutParams.height = JCUtils.dip2px(WebViewActivity.this, height);
                        layoutParams.width = JCUtils.dip2px(WebViewActivity.this, width);
                        mWebView.addView(webVieo, layoutParams);
                    }
                }
            });

        }
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
