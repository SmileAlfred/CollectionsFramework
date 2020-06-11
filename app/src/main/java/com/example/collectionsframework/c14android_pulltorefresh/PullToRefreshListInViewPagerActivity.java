package com.atguigu.android.android_pulltorefresh;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.android.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.Arrays;

public class PullToRefreshListInViewPagerActivity extends Activity implements OnRefreshListener<ListView> {

	private static final String[] STRINGS = { "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance",
			"Ackawi", "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
			"Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
			"Allgauer Emmentaler" };

	private ViewPager mViewPager;
	private TextView tv_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ptr_list_in_vp);
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText("ViewPager");

		mViewPager = (ViewPager) findViewById(R.id.vp_list);
		mViewPager.setAdapter(new ListViewPagerAdapter());
	}

	private class ListViewPagerAdapter extends PagerAdapter {

		@Override
		public View instantiateItem(ViewGroup container, int position) {
			Context context = container.getContext();

			PullToRefreshListView plv = (PullToRefreshListView) LayoutInflater.from(context).inflate(
					R.layout.layout_listview_in_viewpager, container, false);

			ListAdapter adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,
					Arrays.asList(STRINGS));


			plv.setAdapter(adapter);

			//设置刷新
			plv.setOnRefreshListener(PullToRefreshListInViewPagerActivity.this);

			// Now just add ListView to ViewPager and return it
			container.addView(plv, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

			return plv;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		/**
		 * 设置总页面3个
		 * @return
         */
		@Override
		public int getCount() {
			return 3;
		}

	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		new GetDataTask(refreshView).execute();
	}

	private class GetDataTask extends AsyncTask<Void, Void, Void> {

		PullToRefreshBase<?> mRefreshedView;

		public GetDataTask(PullToRefreshBase<?> refreshedView) {
			mRefreshedView = refreshedView;
		}

		@Override
		protected Void doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			mRefreshedView.onRefreshComplete();
			//得到当前刷新的时间
			String label = DateUtils.formatDateTime(PullToRefreshListInViewPagerActivity.this.getApplicationContext(), System.currentTimeMillis(),
					DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

			// Update the LastUpdatedLabel
			//设置更新时间
			mRefreshedView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
			super.onPostExecute(result);
		}
	}

}
