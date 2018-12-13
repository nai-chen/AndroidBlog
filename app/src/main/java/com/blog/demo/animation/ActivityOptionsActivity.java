package com.blog.demo.animation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.view.Window;

import com.blog.demo.R;

/**
 * Created by cn on 2017/8/24.
 */

public class ActivityOptionsActivity extends Activity implements View.OnClickListener {

    private View mSharedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_activity_options);

        findViewById(R.id.btn_custom).setOnClickListener(this);
        findViewById(R.id.btn_scale).setOnClickListener(this);
        findViewById(R.id.btn_thumbnail).setOnClickListener(this);
        findViewById(R.id.btn_scene).setOnClickListener(this);

        mSharedView = findViewById(R.id.view_shared);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_custom:
                startActivityUseCustom();
                break;
            case R.id.btn_scale:
                startActivityUseScale();
                break;
            case R.id.btn_thumbnail:
                startActivityUseThumbnail();
                break;
            case R.id.btn_scene:
                startActivityUseScene();
                break;
        }
    }

    private void startActivityUseCustom() {
        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeCustomAnimation(
                this, R.anim.anim_enter_from_bottom, R.anim.anim_alpha);
        ActivityCompat.startActivity(this, new Intent(this, ActivityOptionsOpenActivity.class),
                activityOptions.toBundle());
    }

    private void startActivityUseScale() {
        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeScaleUpAnimation(
                mSharedView, 0, 0, 100, 100);
        ActivityCompat.startActivity(this, new Intent(this, ActivityOptionsOpenActivity.class),
                activityOptions.toBundle());
    }

    private void startActivityUseThumbnail() {
        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeThumbnailScaleUpAnimation(
                mSharedView, BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher), 0, 0);
        ActivityCompat.startActivity(this, new Intent(this, ActivityOptionsOpenActivity.class),
                activityOptions.toBundle());
    }

    private void startActivityUseScene() {
        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, mSharedView, "picture");
        ActivityCompat.startActivity(this, new Intent(this, ActivityOptionsOpenActivity.class),
                activityOptions.toBundle());
    }

}
