package com.blog.demo.animation;

import com.blog.demo.R;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class AnimationFrameActivity extends Activity {
	private AnimationDrawable mDrawable;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_frame_animation);
		
		ImageView ivFrame = (ImageView) findViewById(R.id.iv_frame);
		mDrawable = (AnimationDrawable) ivFrame.getBackground();
		mDrawable.start();
	}

}
