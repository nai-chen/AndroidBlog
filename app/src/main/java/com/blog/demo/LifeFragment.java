package com.blog.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LifeFragment extends Fragment {
	private final static String TAG = "LifeFragment";

	private String mName;
	private TextView mTv;

	public void setName(String name) {
		this.mName = "_" + name;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		LogUtil.log(TAG + mName, "onActivityCreated");
	}
	
	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		LogUtil.log(TAG + mName, "onAttach");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LogUtil.log(TAG + mName, "onCreateView");
//		return super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.fragment_life, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		LogUtil.log(TAG + mName, "onViewCreated");
		super.onViewCreated(view, savedInstanceState);
		
		mTv = (TextView) view.findViewById(R.id.tv);
		mTv.setText(mName);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LogUtil.log(TAG + mName, "onCreate");
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		LogUtil.log(TAG + mName, "onStart");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		isHidden();
		LogUtil.log(TAG + mName, "onResume");
	}
	
	@Override
	public void onPause() {
		super.onPause();
		
		LogUtil.log(TAG + mName, "onPause");
	}
	
	@Override
	public void onStop() {
		super.onStop();
		
		LogUtil.log(TAG + mName, "onStop");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		LogUtil.log(TAG + mName, "onDestroy");
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		
		LogUtil.log(TAG + mName, "onDestroyView");
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		
		LogUtil.log(TAG + mName, "onDetach");
	}
	
	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		
		LogUtil.log(TAG + mName, "onHiddenChanged hidden = " + hidden);
	}
	
}
