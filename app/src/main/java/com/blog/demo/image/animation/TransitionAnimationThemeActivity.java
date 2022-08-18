package com.blog.demo.image.animation;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import androidx.annotation.Nullable;

import com.blog.demo.R;

public class TransitionAnimationThemeActivity extends TransitionAnimationActivity {
    private View mIvLink, mIvQQ, mIvPyq, mIvWx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mIvLink = findViewById(R.id.iv_link);
        mIvLink.setOnClickListener(this);
        mIvQQ = findViewById(R.id.iv_qq);
        mIvQQ.setOnClickListener(this);
        mIvPyq = findViewById(R.id.iv_pyq);
        mIvPyq.setOnClickListener(this);
        mIvWx = findViewById(R.id.iv_wx);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_link) {
            Intent intent = new Intent(this, TransitionAnimationThemeResultActivity.class);
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();

            startActivity(intent, bundle);
        } else if (view.getId() == R.id.iv_qq) {
            Intent intent = new Intent(this, TransitionAnimationThemeResultActivity.class);
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this, mIvQQ, "sharedView2").toBundle();

            startActivity(intent, bundle);
        } else if (view.getId() == R.id.iv_pyq) {
            Intent intent = new Intent(this, TransitionAnimationThemeResultActivity.class);
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this,
                    Pair.create(mIvLink, "sharedView1"),
                    Pair.create(mIvQQ, "sharedView2"),
                    Pair.create(mIvPyq, "sharedView3"),
                    Pair.create(mIvWx, "sharedView4")).toBundle();
            startActivity(intent, bundle);
        }
    }

}
