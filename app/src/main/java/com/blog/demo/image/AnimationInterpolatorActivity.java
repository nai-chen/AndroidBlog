package com.blog.demo.image;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import com.blog.demo.R;

public class AnimationInterpolatorActivity extends Activity implements View.OnClickListener {

    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_animation_interpolator);

        findViewById(R.id.btn_accelerate_decelerate).setOnClickListener(this);
        findViewById(R.id.btn_accelerate).setOnClickListener(this);
        findViewById(R.id.btn_anticipate).setOnClickListener(this);
        findViewById(R.id.btn_anticipate_overshoot).setOnClickListener(this);
        findViewById(R.id.btn_bounce).setOnClickListener(this);
        findViewById(R.id.btn_cycle).setOnClickListener(this);
        findViewById(R.id.btn_decelerate).setOnClickListener(this);
        findViewById(R.id.btn_linear).setOnClickListener(this);
        findViewById(R.id.btn_overshoot).setOnClickListener(this);

        imageView = findViewById(R.id.image_view);
    }

    @Override
    public void onClick(View v) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_translate_down);
        if (v.getId() == R.id.btn_accelerate_decelerate) {
            animation.setInterpolator(new AccelerateDecelerateInterpolator());
        } else if (v.getId() == R.id.btn_accelerate) {
            animation.setInterpolator(new AccelerateInterpolator());
        } else if (v.getId() == R.id.btn_anticipate) {
            animation.setInterpolator(new AnticipateInterpolator());
        } else if (v.getId() == R.id.btn_anticipate_overshoot) {
            animation.setInterpolator(new AnticipateOvershootInterpolator());
        } else if (v.getId() == R.id.btn_bounce) {
            animation.setInterpolator(new BounceInterpolator());
        } else if (v.getId() == R.id.btn_cycle) {
            animation.setInterpolator(new CycleInterpolator(4));
        } else if (v.getId() == R.id.btn_decelerate) {
            animation.setInterpolator(new DecelerateInterpolator());
        } else if (v.getId() == R.id.btn_linear) {
            animation.setInterpolator(new LinearInterpolator());
        } else if (v.getId() == R.id.btn_overshoot) {
            animation.setInterpolator(new OvershootInterpolator());
        }

        imageView.startAnimation(animation);
    }
}
