package com.blog.demo.image;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.blog.demo.R;

public class FrameAnimationActivity extends Activity implements View.OnClickListener {

    private AnimationDrawable mDrawable;
    private CheckBox cbOneShot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_animation_frame);

        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_stop).setOnClickListener(this);

        cbOneShot = findViewById(R.id.checkbox);

        ImageView imageView = findViewById(R.id.iv_frame);
        mDrawable = (AnimationDrawable) imageView.getDrawable();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_start) {
            mDrawable.setOneShot(cbOneShot.isChecked());
            mDrawable.start();
        } else if (v.getId() == R.id.btn_stop) {
            mDrawable.stop();
        }
    }
}
