package com.blog.demo.image.animation;

import android.app.Activity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.blog.demo.R;

public class TransitionAnimationGoActivity extends Activity implements View.OnClickListener {

    private ViewGroup mContainer;
    private boolean mChange;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_animation_transition_go);

        findViewById(R.id.btn_go).setOnClickListener(this);

        mContainer = findViewById(R.id.layout_container);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_go) {
            go();
        }
    }

    private void go() {
        if (mChange) {
            Scene scene = Scene.getSceneForLayout(mContainer, R.layout.layout_scene_init, this);
            TransitionManager.go(scene, new ChangeBounds());
        } else {
            Scene scene = Scene.getSceneForLayout(mContainer, R.layout.layout_scene_change_bounds, this);
            TransitionManager.go(scene, new ChangeBounds());
        }
        mChange = !mChange;
    }

}
