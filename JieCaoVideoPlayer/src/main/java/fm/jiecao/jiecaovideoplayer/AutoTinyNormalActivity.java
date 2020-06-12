package fm.jiecao.jiecaovideoplayer;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Nathen on 16/8/23.
 */
public class AutoTinyNormalActivity extends AppCompatActivity implements AbsListView.OnScrollListener {
    ListView listView;
    LinearLayout headerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setTitle("AutoTinyNormal");
        setContentView(R.layout.activity_listview_content);

        listView = (ListView) findViewById(R.id.listview);
        headerLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.header_auto_tiny_normal, null);
        listView.addHeaderView(headerLayout);

        JCVideoPlayerStandard jcVideoPlayerStandard = (JCVideoPlayerStandard) headerLayout.findViewById(R.id.jc_video);
        jcVideoPlayerStandard.setUp("http://vfx.mtime.cn/Video/2019/06/27/mp4/190627231412433967.mp4"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "《决战中途岛》预告再现海空激战");
        Picasso.get()
                .load("http://img5.mtime.cn/mg/2019/06/27/231348.59732586_120X90X4.jpg")
                .into(jcVideoPlayerStandard.thumbImageView);

        Map<String, String> keyValuePair = new HashMap<>();
        keyValuePair.put("key", "list item");
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(keyValuePair);
        }

        ListAdapter adapter = new SimpleAdapter(this, list,
                android.R.layout.simple_list_item_1, new String[]{"key"}, new int[]{android.R.id.text1});

        listView.setAdapter(adapter);
        listView.setOnScrollListener(this);
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

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        JCVideoPlayer.onScroll();
    }
}
