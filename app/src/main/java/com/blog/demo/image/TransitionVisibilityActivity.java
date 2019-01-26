package com.blog.demo.image;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blog.demo.R;

public class TransitionVisibilityActivity extends Activity implements View.OnClickListener {

    private ViewGroup mContainer;
    private ImageView mIvLink, mIvQQ, mIvPyq, mIvWX;
    private boolean mChange;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_animation_transition_visibility);

        findViewById(R.id.btn_visibility_code).setOnClickListener(this);
        findViewById(R.id.btn_visibility_xml).setOnClickListener(this);

        mContainer = findViewById(R.id.layout_container);
        mIvLink = findViewById(R.id.iv_link);
        mIvQQ = findViewById(R.id.iv_qq);
        mIvPyq = findViewById(R.id.iv_pyq);
        mIvWX = findViewById(R.id.iv_wx);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_visibility_code) {
            TransitionManager.beginDelayedTransition(mContainer,
                    new TransitionSet()
                            .addTransition(new Fade()
                                    .addTarget(R.id.iv_link))
                            .addTransition(new Slide(Gravity.TOP)
                                    .addTarget(R.id.iv_qq))
                            .addTransition(new Explode()
                                    .addTarget(R.id.iv_pyq)
                                    .addTarget(R.id.iv_wx)));
            if (!mChange) {
                mIvLink.setVisibility(View.GONE);
                mIvQQ.setVisibility(View.GONE);
                mIvPyq.setVisibility(View.GONE);
                mIvWX.setVisibility(View.GONE);
            } else {
                mIvLink.setVisibility(View.VISIBLE);
                mIvQQ.setVisibility(View.VISIBLE);
                mIvPyq.setVisibility(View.VISIBLE);
                mIvWX.setVisibility(View.VISIBLE);
            }
            mChange = !mChange;
        } else if (v.getId() == R.id.btn_visibility_xml) {
            Transition transition = TransitionInflater.from(this)
                    .inflateTransition(R.transition.transition_visibility);
            TransitionManager.beginDelayedTransition(mContainer,  transition);

            if (!mChange) {
                mIvLink.setVisibility(View.GONE);
                mIvQQ.setVisibility(View.GONE);
                mIvPyq.setVisibility(View.GONE);
                mIvWX.setVisibility(View.GONE);
            } else {
                mIvLink.setVisibility(View.VISIBLE);
                mIvQQ.setVisibility(View.VISIBLE);
                mIvPyq.setVisibility(View.VISIBLE);
                mIvWX.setVisibility(View.VISIBLE);
            }
            mChange = !mChange;
        }
    }

}
