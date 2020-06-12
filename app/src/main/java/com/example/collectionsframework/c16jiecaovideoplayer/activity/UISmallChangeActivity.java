package com.example.collectionsframework.c16jiecaovideoplayer.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.collectionsframework.R;
import com.example.collectionsframework.c16jiecaovideoplayer.customview.JCVideoPlayerStandardAutoComplete;
import com.example.collectionsframework.c16jiecaovideoplayer.customview.JCVideoPlayerStandardShowShareButtonAfterFullscreen;
import com.example.collectionsframework.c16jiecaovideoplayer.customview.JCVideoPlayerStandardShowTextureViewAfterAutoComplete;
import com.example.collectionsframework.c16jiecaovideoplayer.customview.JCVideoPlayerStandardShowTitleAfterFullscreen;
import com.squareup.picasso.Picasso;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class UISmallChangeActivity extends AppCompatActivity {
    private TextView tvTitle;
    JCVideoPlayerStandardShowShareButtonAfterFullscreen jcVideoPlayerStandardWithShareButton;
    JCVideoPlayerStandardShowTitleAfterFullscreen jcVideoPlayerStandardShowTitleAfterFullscreen;
    JCVideoPlayerStandardShowTextureViewAfterAutoComplete jcVideoPlayerStandardShowTextureViewAfterAutoComplete;
    JCVideoPlayerStandardAutoComplete jcVideoPlayerStandardAutoComplete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_small_change);
        tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText("修改播放器UI");
        jcVideoPlayerStandardWithShareButton = (JCVideoPlayerStandardShowShareButtonAfterFullscreen) findViewById(R.id.custom_videoplayer_standard_with_share_button);
        jcVideoPlayerStandardWithShareButton.setUp("http://vfx.mtime.cn/Video/2019/06/28/mp4/190628075308350550.mp4", JCVideoPlayer.SCREEN_LAYOUT_NORMAL
                , "霹雳娇娃 首款预告片");
        Picasso.get()
                .load("http://img5.mtime.cn/mg/2019/06/27/224744.68512147_120X90X4.jpg")
                .into(jcVideoPlayerStandardWithShareButton.thumbImageView);


        jcVideoPlayerStandardShowTitleAfterFullscreen = (JCVideoPlayerStandardShowTitleAfterFullscreen) findViewById(R.id.custom_videoplayer_standard_show_title_after_fullscreen);
        jcVideoPlayerStandardShowTitleAfterFullscreen.setUp(                    "http://vfx.mtime.cn/Video/2019/06/27/mp4/190627225613276924.mp4",
                 JCVideoPlayer.SCREEN_LAYOUT_NORMAL
                , "花椒之味  知味版定档预告片");
        Picasso.get()
                .load("http://img5.mtime.cn/mg/2019/06/27/225551.29349352_120X90X4.jpg")
                .into(jcVideoPlayerStandardShowTitleAfterFullscreen.thumbImageView);

        jcVideoPlayerStandardShowTextureViewAfterAutoComplete = (JCVideoPlayerStandardShowTextureViewAfterAutoComplete) findViewById(R.id.custom_videoplayer_standard_show_textureview_aoto_complete);
        jcVideoPlayerStandardShowTextureViewAfterAutoComplete.setUp("http://video.jiecao.fm/11/18/c/I-KpaMJ-HMDfAy6tX2Jfag__.mp4", JCVideoPlayer.SCREEN_LAYOUT_NORMAL
                , "花椒之味  知味版定档预告片");
        Picasso.get()
                .load("http://img5.mtime.cn/mg/2019/06/27/225551.29349352_120X90X4.jpg")
                .into(jcVideoPlayerStandardShowTextureViewAfterAutoComplete.thumbImageView);

        jcVideoPlayerStandardAutoComplete = (JCVideoPlayerStandardAutoComplete) findViewById(R.id.custom_videoplayer_standard_aoto_complete);
        jcVideoPlayerStandardAutoComplete.setUp("http://vfx.mtime.cn/Video/2019/06/27/mp4/190627104751316049.mp4", JCVideoPlayer.SCREEN_LAYOUT_NORMAL
                , "九龙不败 终极版预告");
        Picasso.get()
                                .load("http://img5.mtime.cn/mg/2019/06/27/104144.36321374_120X90X4.jpg")
                .into(jcVideoPlayerStandardAutoComplete.thumbImageView);
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
