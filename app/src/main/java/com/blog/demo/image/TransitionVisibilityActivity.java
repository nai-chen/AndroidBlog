package com.blog.demo.image;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.blog.demo.R;

public class TransitionVisibilityActivity extends Activity implements View.OnClickListener {

    private ViewGroup mContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_animation_transition_visibility);

        findViewById(R.id.btn_visibility_code).setOnClickListener(this);
        findViewById(R.id.btn_visibility_xml).setOnClickListener(this);

        mContainer = findViewById(R.id.layout_container);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_visibility_code) {
            if (mContainer.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(mContainer,
                        new TransitionSet()
                                .addTransition(new Fade()
                                        .addTarget(R.id.iv_link))
                                .addTransition(new Slide(Gravity.TOP)
                                        .addTarget(R.id.iv_qq))
                                .addTransition(new Explode()
                                        .addTarget(R.id.iv_pyq)
                                        .addTarget(R.id.iv_wx)));
                mContainer.setVisibility(View.INVISIBLE);
            } else {
                mContainer.setVisibility(View.VISIBLE);
            }
        } else if (v.getId() == R.id.btn_visibility_xml) {
            if (mContainer.getVisibility() == View.VISIBLE) {
                Transition transition = TransitionInflater.from(this)
                        .inflateTransition(R.transition.transition_visibility);
                TransitionManager.beginDelayedTransition(mContainer,  transition);
            }
        }
    }

}
