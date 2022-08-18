package com.blog.demo.image.animation;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionSet;

import androidx.annotation.Nullable;

import com.blog.demo.R;

public class TransitionAnimationResultActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_animation_transition_result);

        Transition transition = new TransitionSet()
                .addTransition(new Fade().addTarget(R.id.iv_link).addTarget(R.id.iv_qq))
                .addTransition(new Explode().addTarget(R.id.iv_pyq).addTarget(R.id.iv_wx))
                .setDuration(1000);
        getWindow().setEnterTransition(transition);
    }

}
