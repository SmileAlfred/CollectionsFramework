package com.example.collectionsframework.c16jiecaovideoplayer.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collectionsframework.R;
import com.example.collectionsframework.c16jiecaovideoplayer.adapter.RecyclerViewVideoAdapter;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerManager;

public class RecyclerViewNormalActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewVideoAdapter adapterVideoList;
    TextView tvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局和实例化
        setContentView(R.layout.activity_recyclerview_content);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText("RecyclerView中使用");

        //创建适配器
        adapterVideoList = new RecyclerViewVideoAdapter(this);
        recyclerView.setAdapter(adapterVideoList);


        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                if (JCVideoPlayerManager.getCurrentJcvdOnFirtFloor() != null) {
                    JCVideoPlayer videoPlayer = (JCVideoPlayer) JCVideoPlayerManager.getCurrentJcvdOnFirtFloor();
                    if (((ViewGroup) view).indexOfChild(videoPlayer) != -1 && videoPlayer.currentState == JCVideoPlayer.CURRENT_STATE_PLAYING) {
                        JCVideoPlayer.releaseAllVideos();
                    }
                }
            }
        });
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
