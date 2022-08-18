package com.blog.demo.image.animation;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.Nullable;

import com.blog.demo.R;

public class TransitionAnimationActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_animation_transition);

        findViewById(R.id.iv_link).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_link) {
            Intent intent = new Intent(this, TransitionAnimationResultActivity.class);

            Transition transition = new TransitionSet()
                    .addTransition(new Slide(Gravity.LEFT).addTarget(R.id.iv_link).addTarget(R.id.iv_pyq))
                    .addTransition(new Slide(Gravity.RIGHT).addTarget(R.id.iv_qq).addTarget(R.id.iv_wx))
                    .setDuration(1000);

            getWindow().setExitTransition(transition);
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();

            startActivity(intent, bundle);
        }
    }
}
