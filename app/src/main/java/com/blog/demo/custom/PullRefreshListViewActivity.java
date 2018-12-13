package com.blog.demo.custom;

import com.blog.demo.R;
import com.blog.demo.custom.control.PullRefreshListView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;

public class PullRefreshListViewActivity extends Activity {
	
	private String[] month = new String[]{
			"January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December"
	};
	private PullRefreshListView mListView;
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			mListView.done();
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pull_refresh_listview);
		
		mListView = (PullRefreshListView) findViewById(R.id.listview);
		mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, month));
		
		mListView.setOnPullToRefreshListener(new PullRefreshListView.OnPullToRefreshListener() {
			@Override
			public void onPullToRefresh() {
				mHandler.sendEmptyMessageDelayed(0, 3000);
			}
		});
	}
	
}
