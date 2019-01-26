package com.blog.demo.image;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.ChangeClipBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blog.demo.R;

public class TransitionAnimationDelayActivity extends Activity implements View.OnClickListener {
    private ViewGroup mContainer;
    private ImageView mImageView;
    private boolean mChange;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_animation_transition_delay);

        mContainer = findViewById(R.id.layout_container);
        mImageView = findViewById(R.id.image_view);

        findViewById(R.id.btn_change_transform).setOnClickListener(this);
        findViewById(R.id.btn_change_clip_bounds).setOnClickListener(this);
        findViewById(R.id.btn_change_image_transform).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_change_transform) {
            TransitionManager.beginDelayedTransition(mContainer, new ChangeTransform());

            if (mChange) {
                mImageView.setScaleX(1);
                mImageView.setScaleY(1);
            } else {
                mImageView.setScaleX(0.5f);
                mImageView.setScaleY(0.5f);
            }
            mChange = !mChange;
        } else if (v.getId() == R.id.btn_change_clip_bounds) {
            TransitionManager.beginDelayedTransition(mContainer, new ChangeClipBounds());

            if (mChange) {
                mImageView.setClipBounds(null);
            } else {
                mImageView.setClipBounds(new Rect(10, 10, 110, 110));
            }
            mChange = !mChange;
        } else if (v.getId() == R.id.btn_change_image_transform) {
            TransitionManager.beginDelayedTransition(mContainer, new ChangeImageTransform());
            if (mChange) {
                mImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            } else {
                mImageView.setScaleType(ImageView.ScaleType.CENTER);
            }
            mChange = !mChange;
        }
    }
}
