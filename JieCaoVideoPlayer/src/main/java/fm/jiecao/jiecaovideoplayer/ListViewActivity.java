package fm.jiecao.jiecaovideoplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.Nullable;

/**
 * Created by Nathen on 16/7/31.
 */
public class ListViewActivity extends AppCompatActivity implements View.OnClickListener {
    Button mNormalList, mViewPagerList, mMultiHolderList, mRecyleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setTitle("About ListView");
        setContentView(R.layout.activity_listview);

        mNormalList = (Button) findViewById(R.id.normal_list);
        mViewPagerList = (Button) findViewById(R.id.viewpayer_list);
        mMultiHolderList = (Button) findViewById(R.id.multi_holder_list);
        mRecyleView = (Button) findViewById(R.id.recyleview);

        mNormalList.setOnClickListener(this);
        mViewPagerList.setOnClickListener(this);
        mMultiHolderList.setOnClickListener(this);
        mRecyleView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.normal_list:
                startActivity(new Intent(ListViewActivity.this, ListViewNormalActivity.class));
                break;
            case R.id.viewpayer_list:
                startActivity(new Intent(ListViewActivity.this, ListViewViewpagerActivity.class));
                break;
            case R.id.multi_holder_list:
                startActivity(new Intent(ListViewActivity.this, ListViewMultiHolderActivity.class));
                break;
            case R.id.recyleview:
                startActivity(new Intent(ListViewActivity.this, RecyclerViewNormalActivity.class));
                break;
            default:
                break;
        }
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
