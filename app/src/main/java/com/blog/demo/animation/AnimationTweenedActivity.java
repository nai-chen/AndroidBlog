package com.blog.demo.animation;

import com.blog.demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class AnimationTweenedActivity extends Activity 
		implements View.OnClickListener {
	private View view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_tweened_animation);
		
		findViewById(R.id.btn_translate).setOnClickListener(this);
		findViewById(R.id.btn_scale).setOnClickListener(this);
		findViewById(R.id.btn_rotate).setOnClickListener(this);
		findViewById(R.id.btn_alpha).setOnClickListener(this);
		findViewById(R.id.btn_set).setOnClickListener(this);
		
		findViewById(R.id.btn_custom_translate).setOnClickListener(this);
		findViewById(R.id.btn_custom_scale).setOnClickListener(this);
		findViewById(R.id.btn_custom_rotate).setOnClickListener(this);
		findViewById(R.id.btn_custom_alpha).setOnClickListener(this);
		findViewById(R.id.btn_custom_set).setOnClickListener(this);
		
		view = findViewById(R.id.iv_tweened);
	}
	
	@Override
	public void onClick(View v) {
		Animation animation = null;
		switch (v.getId()) {
		case R.id.btn_translate:
			animation = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
			break;
		case R.id.btn_scale:
			animation = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
			break;
		case R.id.btn_rotate:
			animation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
			break;
		case R.id.btn_alpha:
			animation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
			break;
		case R.id.btn_set:
			animation = AnimationUtils.loadAnimation(this, R.anim.anim_set);
			break;
		case R.id.btn_custom_translate:
			animation = new TranslateAnimation(0, 0, Animation.RELATIVE_TO_SELF, 1.0f, 0, 0, 0, 0);
			animation.setDuration(3000);
			break;
		case R.id.btn_custom_scale:
			animation = new ScaleAnimation(1.0f, 0.5f, 1.0f, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			animation.setDuration(3000);
			break;
		case R.id.btn_custom_rotate:
			animation = new RotateAnimation(0, 360,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			animation.setDuration(3000);
			break;
		case R.id.btn_custom_alpha:
			animation = new AlphaAnimation(1.0f, 0f);
			animation.setDuration(3000);
			break;
		case R.id.btn_custom_set:
			AnimationSet animationSet = new AnimationSet(true);
			
			animation = new AlphaAnimation(0f, 1.0f);
			animationSet.addAnimation(animation);
			
			animation = new ScaleAnimation( 0.5f, 1.0f, 0.5f, 1.0f,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			animationSet.addAnimation(animation);
			
			animation = animationSet;
			animation.setDuration(3000);
			break;
		}
		if (animation != null) {
			view.clearAnimation();
			view.startAnimation(animation);
		}
	}
	
}
