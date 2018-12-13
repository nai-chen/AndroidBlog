package com.blog.demo.animation;

import com.blog.demo.R;

import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AnimationPropertyActivity extends Activity implements View.OnClickListener {
	private ImageView mImageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_property_anim);
		
		mImageView = (ImageView) findViewById(R.id.iv);
		findViewById(R.id.tv_value_animator).setOnClickListener(this);
		findViewById(R.id.tv_object_animator).setOnClickListener(this);
		findViewById(R.id.tv_animator_set1).setOnClickListener(this);
		findViewById(R.id.tv_animator_set2).setOnClickListener(this);
		findViewById(R.id.tv_animator_set3).setOnClickListener(this);
		findViewById(R.id.tv_property_value_holder).setOnClickListener(this);
		findViewById(R.id.tv_key_frame).setOnClickListener(this);
		findViewById(R.id.tv_type_evaluator).setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_value_animator:
			valueAnimator();
			break;
		case R.id.tv_object_animator:
			objectAnimator();
			break;
		case R.id.tv_animator_set1:
			animatorSet1();
			break;
		case R.id.tv_animator_set2:
			animatorSet2();
			break;
		case R.id.tv_animator_set3:
			animatorSet3();
			break;
		case R.id.tv_property_value_holder:
			propertyValueHolder();
			break;
		case R.id.tv_key_frame:
			keyFrame();
			break;
		case R.id.tv_type_evaluator:
			typeEvaluator();
			break;
		}
		
	}

	private void valueAnimator() {
		ValueAnimator animator = ValueAnimator.ofFloat(1.0f, 0.5f, 1.0f);
		animator.setDuration(3000);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float scale = (Float) animation.getAnimatedValue();
				mImageView.setScaleX(scale);
			}
		});
		animator.start();
	}
	
	private void objectAnimator() {
		ObjectAnimator animator = ObjectAnimator.ofFloat(mImageView, "scaleX", 1.0f, 0.5f, 1.0f);
		animator.setDuration(3000);
		animator.start();
	}
	
	private void animatorSet1() {
		ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(mImageView, "scaleX", 1.0f, 0.5f, 1.0f);
		ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(mImageView, "scaleY", 1.0f, 0.5f, 1.0f);
		AnimatorSet scaleAnimator = new AnimatorSet();
		scaleAnimator.playTogether(scaleXAnimator, scaleYAnimator);
		scaleAnimator.setDuration(2000);
		scaleAnimator.start();
	}
	
	private void animatorSet2() {
		ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mImageView, "alpha", 1.0f, 0.5f, 1.0f);
		alphaAnimator.setDuration(2000);
		
		ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(mImageView, "scaleX", 1.0f, 0.5f, 1.0f);
		ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(mImageView, "scaleY", 1.0f, 0.5f, 1.0f);
		AnimatorSet scaleAnimator = new AnimatorSet();
		scaleAnimator.play(scaleXAnimator).with(scaleYAnimator);
		scaleAnimator.setDuration(2000);
		
		ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(mImageView, "rotation", 0f, 360f);
		rotationAnimator.setDuration(2000);
		
		AnimatorSet set = new AnimatorSet();
		set.playSequentially(alphaAnimator, scaleAnimator, rotationAnimator);
		set.start();
	}
	
	private void animatorSet3() {
		ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mImageView, "alpha", 1.0f, 0.5f, 1.0f);
		alphaAnimator.setDuration(2000);
		
		ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(mImageView, "scaleX", 1.0f, 0.5f, 1.0f);
		scaleXAnimator.setDuration(2000);
		
		ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(mImageView, "scaleY", 1.0f, 0.5f, 1.0f);
		scaleYAnimator.setDuration(2000);
		
		ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(mImageView, "rotation", 0f, 360f);
		rotationAnimator.setDuration(2000);
		
		AnimatorSet set = new AnimatorSet();
		set.play(scaleXAnimator).with(scaleYAnimator).before(rotationAnimator).after(alphaAnimator); 
		set.start();
	}
	
	private void propertyValueHolder() {
		PropertyValuesHolder scaleXHolder = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.5f, 1.0f);
		PropertyValuesHolder scaleYHolder = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.5f, 1.0f);
		
		ObjectAnimator.ofPropertyValuesHolder(mImageView, scaleXHolder, scaleYHolder)
		.setDuration(2000).start();
	}
	
	private void keyFrame() {
		Keyframe keyframe1 = Keyframe.ofFloat(0, 1.0f);
		Keyframe keyframe2 = Keyframe.ofFloat(0.2f, 0.5f);
		Keyframe keyframe3 = Keyframe.ofFloat(0.4f, 1.0f);
		Keyframe keyframe4 = Keyframe.ofFloat(0.6f, 0.2f);
		Keyframe keyframe5 = Keyframe.ofFloat(0.8f, 0f);
		Keyframe keyframe6 = Keyframe.ofFloat(1.0f, 1.0f);
		PropertyValuesHolder scaleXHolder = PropertyValuesHolder.ofKeyframe("scaleX",
					keyframe1, keyframe2, keyframe3, keyframe4, keyframe5, keyframe6);
		PropertyValuesHolder scaleYHolder = PropertyValuesHolder.ofKeyframe("scaleY",
				keyframe1, keyframe2, keyframe3, keyframe4, keyframe5, keyframe6);
		ObjectAnimator.ofPropertyValuesHolder(mImageView, scaleXHolder, scaleYHolder)
		.setDuration(5000).start();
	}
	
	private void typeEvaluator() {
		TypeEvaluator<Float> typeEvaluator = new CustomTypeEvaluator();
		PropertyValuesHolder scaleXHolder = PropertyValuesHolder.ofObject("scaleX", typeEvaluator, 0f, 1.0f);
		PropertyValuesHolder scaleYHolder = PropertyValuesHolder.ofObject("scaleY", typeEvaluator, 0f, 1.0f);
		ObjectAnimator.ofPropertyValuesHolder(mImageView, scaleXHolder, scaleYHolder)
		.setDuration(5000).start();
	}
	
	private class CustomTypeEvaluator implements TypeEvaluator<Float> {
		@Override
		public Float evaluate(float fraction, Float startValue, Float endValue) {
			if (fraction <= 0.2) {
				return startValue + fraction / 0.2f * (endValue - startValue);
			} else if (fraction <= 0.4) {
				return startValue + (0.4f - fraction) / 0.2f * (endValue - startValue);
			} else if (fraction <= 1) {
				return startValue + (fraction - 0.4f) / 0.6f * (endValue - startValue);
			}
			return endValue;
		}
	}
	
}
