package com.blog.demo.image;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.blog.demo.R;

public class TweenAnimationActivity extends Activity implements View.OnClickListener{
    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_animation_tween);

        findViewById(R.id.btn_translate_xml).setOnClickListener(this);
        findViewById(R.id.btn_translate_code).setOnClickListener(this);

        findViewById(R.id.btn_scale_xml).setOnClickListener(this);
        findViewById(R.id.btn_scale_code).setOnClickListener(this);

        findViewById(R.id.btn_rotate_xml).setOnClickListener(this);
        findViewById(R.id.btn_rotate_code).setOnClickListener(this);

        findViewById(R.id.btn_alpha_xml).setOnClickListener(this);
        findViewById(R.id.btn_alpha_code).setOnClickListener(this);

        findViewById(R.id.btn_set_xml).setOnClickListener(this);
        findViewById(R.id.btn_set_code).setOnClickListener(this);

        findViewById(R.id.btn_cancel).setOnClickListener(this);

        mImageView = findViewById(R.id.image_view);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_translate_xml) {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
            mImageView.startAnimation(animation);
        } else if (v.getId() == R.id.btn_translate_code) {
            Animation animation = new TranslateAnimation(0, 0, Animation.RELATIVE_TO_SELF, 1.0f,
                    0, 0, 0, 0);
            animation.setDuration(3000);
            mImageView.startAnimation(animation);
        } else if (v.getId() == R.id.btn_scale_xml) {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
            mImageView.startAnimation(animation);
        } else if (v.getId() == R.id.btn_scale_code) {
            Animation animation = new ScaleAnimation(1.0f, 0.5f, 1.0f, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(3000);
            mImageView.startAnimation(animation);
        } else if (v.getId() == R.id.btn_rotate_xml) {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
            mImageView.startAnimation(animation);
        } else if (v.getId() == R.id.btn_rotate_code) {
            Animation animation = new RotateAnimation(0, 360, // -360逆时针
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(3000);
            mImageView.startAnimation(animation);
        } else if (v.getId() == R.id.btn_alpha_xml) {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
            mImageView.startAnimation(animation);
        } else if (v.getId() == R.id.btn_alpha_code) {
            Animation animation = new AlphaAnimation(1.0f, 0f);
            animation.setDuration(3000);
            mImageView.startAnimation(animation);
        } else if (v.getId() == R.id.btn_set_xml) {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_set);
            mImageView.startAnimation(animation);
        } else if (v.getId() == R.id.btn_set_code) {
            AnimationSet animation = new AnimationSet(true);

            Animation alphaAnim = new AlphaAnimation(0f, 1.0f);
            animation.addAnimation(alphaAnim);

            Animation scaleAnim = new ScaleAnimation( 0.5f, 1.0f, 0.5f, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            animation.addAnimation(scaleAnim);

            animation.setDuration(3000);
            mImageView.startAnimation(animation);
        } else if (v.getId() == R.id.btn_cancel) {
            mImageView.clearAnimation();
        }
    }
}
