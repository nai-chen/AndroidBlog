package com.blog.demo.component;

import com.blog.demo.LifeFragment;
import com.blog.demo.LogUtil;
import com.blog.demo.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;

public class LifeFragmentActivity extends FragmentActivity implements OnClickListener {
	private String mCurTag;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		LogUtil.log("LifeFragmentActivity", "before onCreate");
		super.onCreate(savedInstanceState);
		LogUtil.log("LifeFragmentActivity", "after onCreate");
		
		setContentView(R.layout.activity_life_fragment);
		
		changeToFragment("btn1");
		
		findViewById(R.id.btn1).setOnClickListener(this);
		findViewById(R.id.btn2).setOnClickListener(this);
		findViewById(R.id.btn3).setOnClickListener(this);
	}
	
	private void changeToFragment(String tag) {
		if (mCurTag != null && mCurTag.equals(tag)) {
			return;
		}
		
		FragmentManager fm = getSupportFragmentManager();		
		FragmentTransaction ft = fm.beginTransaction();
		if (mCurTag != null) {
			Fragment fragment = fm.findFragmentByTag(mCurTag);
			if (fragment != null) ft.hide(fragment);
		}
		
		Fragment fragment = fm.findFragmentByTag(tag);
		if (fragment != null) {
			ft.show(fragment);
		} else {
			LifeFragment lifeFragment = new LifeFragment();
			lifeFragment.setName(tag);
			fragment = lifeFragment;
			ft.add(R.id.container, fragment, tag);
		}
		ft.commitAllowingStateLoss();
		
		mCurTag = tag;
	}
	
//	@Override
//	protected void onRestart() {
//		LogUtil.log("LifeFragmentActivity", "before onRestart");
//		super.onRestart();
//		LogUtil.log("LifeFragmentActivity", "after onRestart");
//	}
//	
//	@Override
//	protected void onStart() {
//		LogUtil.log("LifeFragmentActivity", "before onStart");
//		super.onStart();
//		LogUtil.log("LifeFragmentActivity", "after onStart");
//	}
//	
//	@Override
//	protected void onResume() {
//		LogUtil.log("LifeFragmentActivity", "before onResume");
//		super.onResume();
//		LogUtil.log("LifeFragmentActivity", "after onResume");
//	}
//	
//	@Override
//	protected void onPause() {
//		LogUtil.log("LifeFragmentActivity", "before onPause");
//		super.onPause();
//		LogUtil.log("LifeFragmentActivity", "after onPause");
//	}
//	
//	@Override
//	protected void onStop() {
//		LogUtil.log("LifeFragmentActivity", "before onStop");
//		super.onStop();
//		LogUtil.log("LifeFragmentActivity", "after onStop");
//	}
//	
//	@Override
//	protected void onDestroy() {
//		LogUtil.log("LifeFragmentActivity", "before onDestroy");
//		super.onDestroy();
//		LogUtil.log("LifeFragmentActivity", "after onDestroy");
//	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn1:
			changeToFragment("btn1");
			break;
		case R.id.btn2:
			changeToFragment("btn2");
			break;
		case R.id.btn3:
			changeToFragment("btn3");
			break;
		}
	}
	
}

