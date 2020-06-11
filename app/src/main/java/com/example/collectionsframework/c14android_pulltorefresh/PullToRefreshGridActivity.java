/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.atguigu.android.android_pulltorefresh;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.android.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.extras.SoundPullEventListener;

import java.util.Arrays;
import java.util.LinkedList;

public final class PullToRefreshGridActivity extends Activity {


	private LinkedList<String> mListItems;
	private PullToRefreshGridView mPullRefreshGridView;
	/**
	 * 得到的GridView
	 */
	private GridView mGridView;
	private ArrayAdapter<String> mAdapter;
	private TextView tv_title;


	private String[] mStrings = { "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
			"Allgauer Emmentaler" };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ptr_grid);
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText("PullToRefreshGridView");

		mPullRefreshGridView = (PullToRefreshGridView) findViewById(R.id.pull_refresh_grid);

		mGridView = mPullRefreshGridView.getRefreshableView();

		// Set a listener to be invoked when the list should be refreshed.
		mPullRefreshGridView.setOnRefreshListener(new OnRefreshListener2<GridView>() {

			/**
			 * 下拉刷新
			 * @param refreshView
             */
			@Override
			public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
				//得到当前刷新的时间
				String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

				// Update the LastUpdatedLabel
				//设置更新时间
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
				Toast.makeText(PullToRefreshGridActivity.this, "下拉刷新", Toast.LENGTH_SHORT).show();

				new GetDataTask().execute();
			}

			/**
			 * 上拉刷新
			 * @param refreshView
             */
			@Override
			public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
				//得到当前刷新的时间
				String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

				// Update the LastUpdatedLabel
				//设置更新时间
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
				Toast.makeText(PullToRefreshGridActivity.this, "上拉刷新!", Toast.LENGTH_SHORT).show();
				new GetDataTask().execute();
			}

		});

		/**
		 * Add Sound Event Listener
		 * 添加刷新事件并且发出声音
		 */
		SoundPullEventListener<GridView> soundListener = new SoundPullEventListener<GridView>(this);
		soundListener.addSoundEvent(PullToRefreshBase.State.PULL_TO_REFRESH, R.raw.pull_event);
		soundListener.addSoundEvent(PullToRefreshBase.State.RESET, R.raw.reset_sound);
		soundListener.addSoundEvent(PullToRefreshBase.State.REFRESHING, R.raw.refreshing_sound);
		mPullRefreshGridView.setOnPullEventListener(soundListener);

		//创建集合
		mListItems = new LinkedList<String>();

		TextView tv = new TextView(this);
		tv.setGravity(Gravity.CENTER);
		tv.setText("还没有加载数据，请下拉/上拉刷新...");
		//设置没有内容的时候显示的视图
		mPullRefreshGridView.setEmptyView(tv);

		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListItems);
		mGridView.setAdapter(mAdapter);
	}

	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			return mStrings;
		}

		@Override
		protected void onPostExecute(String[] result) {
			mListItems.addFirst("Added after refresh...");
			//把数组转换成集合，并且添加到LinkedList<String>集合
			mListItems.addAll(Arrays.asList(result));
			mAdapter.notifyDataSetChanged();

			// Call onRefreshComplete when the list has been refreshed.
			//把下拉和上拉状态还原
			mPullRefreshGridView.onRefreshComplete();




			super.onPostExecute(result);
		}
	}



}
