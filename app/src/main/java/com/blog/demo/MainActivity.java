package com.blog.demo;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private final static String LOGTAG = "Main";
	private final static String APKTEST_ACTION = "com.blog.demo.action.content";
	private final static String EXTRA_DATA = "data";

	private ListView mListView;
	private Layer mLayer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		mListView = (ListView) findViewById(R.id.listview);

		if (getIntent() != null) {
			mLayer = getIntent().getParcelableExtra(EXTRA_DATA);
		}

		if (mLayer == null) {
			mLayer = new Layer();
			
			Intent intent = new Intent(APKTEST_ACTION);
			List<ResolveInfo> infoList = getPackageManager().queryIntentActivities(intent,
					PackageManager.GET_INTENT_FILTERS);
			if (infoList != null) {
				for (ResolveInfo info : infoList) {
					mLayer.addValue(info.loadLabel(getPackageManager()).toString(), info.activityInfo.name);
				}
			}
		}
		
		mListView.setAdapter(new ActivityInfoAdapter());
		mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				startActivity(position);
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				startActivity(position);
			}
		});
	}
	
	private void startActivity(int position) {
		Layer child = mLayer.getChild(position);
		if (child.hasChild()) {
			Intent intent = new Intent(this, MainActivity.class);
			intent.putExtra(EXTRA_DATA, child);
			startActivity(intent);
		} else {
			try {
				Class<?> c = Class.forName(child.getClassName());
				startActivity(new Intent(this, c));
			} catch (ClassNotFoundException e) {
				LogUtil.log(LOGTAG, e.getMessage());
			}
		}
	}
	
	private class ActivityInfoAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mLayer.size();
		}

		@Override
		public Object getItem(int position) {
			return mLayer.getChild(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getLayoutInflater().inflate(R.layout.listview_main_item, parent, false);
			}
			Layer layer = mLayer.getChild(position);
			((TextView) convertView.findViewById(R.id.title)).setText(layer.getName());
			
			return convertView;
		}
	}
	
}
