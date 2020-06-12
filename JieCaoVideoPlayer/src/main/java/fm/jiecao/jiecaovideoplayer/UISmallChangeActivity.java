package fm.jiecao.jiecaovideoplayer;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jiecaovideoplayer.CustomView.JCVideoPlayerStandardAutoComplete;
import fm.jiecao.jiecaovideoplayer.CustomView.JCVideoPlayerStandardShowShareButtonAfterFullscreen;
import fm.jiecao.jiecaovideoplayer.CustomView.JCVideoPlayerStandardShowTextureViewAfterAutoComplete;
import fm.jiecao.jiecaovideoplayer.CustomView.JCVideoPlayerStandardShowTitleAfterFullscreen;

/**
 * Created by Nathen on 16/7/31.
 */
public class UISmallChangeActivity extends AppCompatActivity {
    JCVideoPlayerStandardShowShareButtonAfterFullscreen jcVideoPlayerStandardWithShareButton;
    JCVideoPlayerStandardShowTitleAfterFullscreen jcVideoPlayerStandardShowTitleAfterFullscreen;
    JCVideoPlayerStandardShowTextureViewAfterAutoComplete jcVideoPlayerStandardShowTextureViewAfterAutoComplete;
    JCVideoPlayerStandardAutoComplete jcVideoPlayerStandardAutoComplete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setTitle("SmallChangeUI");
        setContentView(R.layout.activity_ui_small_change);

        jcVideoPlayerStandardWithShareButton = (JCVideoPlayerStandardShowShareButtonAfterFullscreen) findViewById(R.id.custom_videoplayer_standard_with_share_button);
        jcVideoPlayerStandardWithShareButton.setUp( "http://vfx.mtime.cn/Video/2019/05/24/mp4/190524102909878004.mp4", JCVideoPlayer.SCREEN_LAYOUT_NORMAL
                , "《爱宠大机密2》乡村冒险预告");
        Picasso.get()
                .load("http://img5.mtime.cn/mg/2019/05/24/102859.56156442_120X90X4.jpg")
                .into(jcVideoPlayerStandardWithShareButton.thumbImageView);


        jcVideoPlayerStandardShowTitleAfterFullscreen = (JCVideoPlayerStandardShowTitleAfterFullscreen) findViewById(R.id.custom_videoplayer_standard_show_title_after_fullscreen);
        jcVideoPlayerStandardShowTitleAfterFullscreen.setUp("http://vfx.mtime.cn/Video/2019/05/24/mp4/190524093650003718.mp4", JCVideoPlayer.SCREEN_LAYOUT_NORMAL
                , "《八佰》战火英雄预告");
        Picasso.get()
                .load("http://img5.mtime.cn/mg/2019/05/24/093747.76754839_120X90X4.jpg")
                .into(jcVideoPlayerStandardShowTitleAfterFullscreen.thumbImageView);

        jcVideoPlayerStandardShowTextureViewAfterAutoComplete = (JCVideoPlayerStandardShowTextureViewAfterAutoComplete) findViewById(R.id.custom_videoplayer_standard_show_textureview_aoto_complete);
        jcVideoPlayerStandardShowTextureViewAfterAutoComplete.setUp("http://vfx.mtime.cn/Video/2019/05/24/mp4/190524105156675387.mp4", JCVideoPlayer.SCREEN_LAYOUT_NORMAL
                , "终结者6 中文预告片");
        Picasso.get()
                .load("http://img5.mtime.cn/mg/2019/05/23/211752.86735336_120X90X4.jpg")
                .into(jcVideoPlayerStandardShowTextureViewAfterAutoComplete.thumbImageView);

        jcVideoPlayerStandardAutoComplete = (JCVideoPlayerStandardAutoComplete) findViewById(R.id.custom_videoplayer_standard_aoto_complete);
        jcVideoPlayerStandardAutoComplete.setUp("http://vfx.mtime.cn/Video/2019/05/28/mp4/190528005132295518.mp4", JCVideoPlayer.SCREEN_LAYOUT_NORMAL
                , "《千与千寻》中国内地定档预告");
        Picasso.get()
                .load("http://img5.mtime.cn/mg/2019/05/28/004938.55258124_120X90X4.jpg")
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
