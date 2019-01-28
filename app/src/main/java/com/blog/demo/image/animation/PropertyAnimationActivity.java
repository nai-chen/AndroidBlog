package com.blog.demo.image.animation;

import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.blog.demo.R;

public class PropertyAnimationActivity extends Activity implements View.OnClickListener {

    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_animation_property);

        findViewById(R.id.btn_value_animator).setOnClickListener(this);
        findViewById(R.id.btn_object_animator).setOnClickListener(this);
        findViewById(R.id.btn_property_values_holder).setOnClickListener(this);
        findViewById(R.id.btn_animator_set).setOnClickListener(this);
        findViewById(R.id.btn_play_sequentially).setOnClickListener(this);
        findViewById(R.id.btn_play).setOnClickListener(this);
        findViewById(R.id.btn_key_frames).setOnClickListener(this);
        findViewById(R.id.btn_type_evaluator).setOnClickListener(this);

        imageView = findViewById(R.id.image_view);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_value_animator) {
            ValueAnimator animator = ValueAnimator.ofFloat(1, 0.5f, 1);
            animator.setDuration(3000);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float value = (Float) animation.getAnimatedValue();
                    imageView.setScaleX(value);
                }
            });
            animator.start();
        } else if (v.getId() == R.id.btn_object_animator) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "scaleX", 1, 0.5f, 1);
            animator.setDuration(3000);
            animator.start();
        } else if (v.getId() == R.id.btn_property_values_holder) {
            PropertyValuesHolder xHolder = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.5f, 1.0f);
            PropertyValuesHolder yHolder = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.5f, 1.0f);

            ObjectAnimator.ofPropertyValuesHolder(imageView, xHolder, yHolder)
                    .setDuration(3000)
                    .start();
        } else if (v.getId() == R.id.btn_animator_set) {
            ObjectAnimator xAnimator = ObjectAnimator.ofFloat(imageView, "scaleX", 1.0f, 0.5f, 1.0f);
            ObjectAnimator yAnimator = ObjectAnimator.ofFloat(imageView, "scaleY", 1.0f, 0.5f, 1.0f);

            AnimatorSet animator = new AnimatorSet();
            animator.playTogether(xAnimator, yAnimator);
            animator.setDuration(3000);
            animator.start();
        } else if (v.getId() == R.id.btn_play_sequentially) {
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView, "alpha", 1.0f, 0.5f, 1.0f);
            animator1.setDuration(2000);

            ObjectAnimator xAnimator2 = ObjectAnimator.ofFloat(imageView, "scaleX", 1.0f, 0.5f, 1.0f);
            ObjectAnimator yAnimator2 = ObjectAnimator.ofFloat(imageView, "scaleY", 1.0f, 0.5f, 1.0f);
            AnimatorSet scaleAnimator = new AnimatorSet();
            scaleAnimator.playTogether(xAnimator2, yAnimator2);
            scaleAnimator.setDuration(2000);

            ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
            animator3.setDuration(2000);

            AnimatorSet animator = new AnimatorSet();
            animator.playSequentially(animator1, scaleAnimator, animator3);

            animator.start();
        } else if (v.getId() == R.id.btn_play) {
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView, "alpha", 1.0f, 0.5f, 1.0f);
            animator1.setDuration(2000);

            ObjectAnimator xAnimator2 = ObjectAnimator.ofFloat(imageView, "scaleX", 1.0f, 0.5f, 1.0f);
            xAnimator2.setDuration(2000);

            ObjectAnimator yAnimator2 = ObjectAnimator.ofFloat(imageView, "scaleY", 1.0f, 0.5f, 1.0f);
            yAnimator2.setDuration(2000);

            ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
            animator3.setDuration(2000);

            AnimatorSet animator = new AnimatorSet();
            animator.play(xAnimator2).with(yAnimator2).after(animator1).before(animator3);

            animator.start();
        } else if (v.getId() == R.id.btn_key_frames) {
            Keyframe keyframe1 = Keyframe.ofFloat(0f, 1.0f);
            Keyframe keyframe2 = Keyframe.ofFloat(0.2f, 0.5f);
            Keyframe keyframe3 = Keyframe.ofFloat(0.4f, 1.0f);
            Keyframe keyframe4 = Keyframe.ofFloat(0.7f, 0f);
            Keyframe keyframe5 = Keyframe.ofFloat(1.0f, 1.0f);

            PropertyValuesHolder xHolder = PropertyValuesHolder.ofKeyframe("scaleX",
                    keyframe1, keyframe2, keyframe3, keyframe4, keyframe5);
            PropertyValuesHolder yHolder = PropertyValuesHolder.ofKeyframe("scaleY",
                    keyframe1, keyframe2, keyframe3, keyframe4, keyframe5);
            ObjectAnimator.ofPropertyValuesHolder(imageView, xHolder, yHolder)
                    .setDuration(3000)
                    .start();
        } else if (v.getId() == R.id.btn_type_evaluator) {
            TypeEvaluator<Float> typeEvaluator = new TypeEvaluator<Float>() {
                FloatEvaluator evaluator = new FloatEvaluator();
                @Override
                public Float evaluate(float fraction, Float startValue, Float endValue) {
                    if (fraction <= 0.2f) {
                        return evaluator.evaluate(fraction/0.2f, 1.0f, 0.5f);
                    } else if (fraction <= 0.4f) {
                        return evaluator.evaluate((fraction - 0.2f)/0.2f, 0.5f, 1.0f);
                    } else if (fraction < 0.8f) {
                        return evaluator.evaluate((fraction - 0.4f)/0.4f, 1.0f, 0f);
                    } else if (fraction <= 1) {
                        return evaluator.evaluate((fraction - 0.8f)/0.2f, 0f, 1.0f);
                    }
                    return endValue;
                }
            };
            PropertyValuesHolder xHolder = PropertyValuesHolder.ofObject("scaleX", typeEvaluator, 0f, 1.0f);
            PropertyValuesHolder yHolder = PropertyValuesHolder.ofObject("scaleY", typeEvaluator, 0f, 1.0f);
            ObjectAnimator.ofPropertyValuesHolder(imageView, xHolder, yHolder)
                    .setDuration(3000)
                    .start();
        }
    }
}
