package cn.jzvd.demo;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.IOException;

import cn.jzvd.JZDataSource;
import cn.jzvd.JZMediaSystem;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import cn.jzvd.demo.CustomMedia.JZMediaExo;
import cn.jzvd.demo.CustomMedia.JZMediaIjk;
import cn.jzvd.demo.CustomMedia.JZMediaSystemAssertFolder;

/**
 * Created by Nathen on 2017/11/23.
 */

public class ActivityApiCustomMedia extends AppCompatActivity {
    JzvdStd jzvdStd;
    Handler handler = new Handler();//这里其实并不需要handler，为了防止播放中切换播放器引擎导致的崩溃，实际使用时一般不会遇到，可以随时调用JZVideoPlayer.setMediaInterface();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setTitle("CustomMediaPlayer");
        setContentView(R.layout.activity_api_custom_mediaplayer);

        jzvdStd = findViewById(R.id.videoplayer);

        JZDataSource jzDataSource = null;
        try {
            jzDataSource = new JZDataSource(getAssets().openFd("local_video.mp4"));
            jzDataSource.title = "烈火英雄 留言版预告";
        } catch (IOException e) {
            e.printStackTrace();
        }
        jzvdStd.setUp(jzDataSource, JzvdStd.SCREEN_NORMAL, JZMediaSystemAssertFolder.class);


//        jzvdStd.setUp("http://vfx.mtime.cn/Video/2019/06/26/mp4/190626111517361726.mp4"
//                , "烈火英雄 留言版预告", JzvdStd.SCREEN_NORMAL, new JZMediaIjk(jzvdStd));

        Glide.with(this)
                .load("http://img5.mtime.cn/mg/2019/06/26/110121.18314261_120X90X4.jpg")
                .into(jzvdStd.thumbImageView);

    }


    public void clickChangeToIjkplayer(View view) {
        Jzvd.releaseAllVideos();
        jzvdStd.setUp("http://vfx.mtime.cn/Video/2019/06/26/mp4/190626111517361726.mp4"
                , "烈火英雄 留言版预告", JzvdStd.SCREEN_NORMAL, JZMediaIjk.class);
        jzvdStd.startVideo();
        Toast.makeText(this, "Change to Ijkplayer", Toast.LENGTH_SHORT).show();
    }

    public void clickChangeToSystem(View view) {
        Jzvd.releaseAllVideos();
        jzvdStd.setUp("http://vfx.mtime.cn/Video/2019/06/26/mp4/190626111517361726.mp4"
                , "烈火英雄 留言版预告", JzvdStd.SCREEN_NORMAL, JZMediaSystem.class);
        jzvdStd.startVideo();
        Toast.makeText(this, "Change to MediaPlayer", Toast.LENGTH_SHORT).show();
    }

    public void clickChangeToExo(View view) {
        Jzvd.releaseAllVideos();
        jzvdStd.setUp("http://vfx.mtime.cn/Video/2019/06/26/mp4/190626111517361726.mp4"
                , "烈火英雄 留言版预告", JzvdStd.SCREEN_NORMAL, JZMediaExo.class);
        jzvdStd.startVideo();
        Toast.makeText(this, "Change to ExoPlayer", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
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
