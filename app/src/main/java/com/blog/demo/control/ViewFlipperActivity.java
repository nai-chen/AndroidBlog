package com.blog.demo.control;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ViewFlipper;

import com.blog.demo.R;

public class ViewFlipperActivity extends Activity {

    private ViewFlipper mViewFlipper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_view_flipper);

        mViewFlipper = findViewById(R.id.view_flipper);
        findViewById(R.id.btn_show_previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previous();
            }
        });
        findViewById(R.id.btn_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });
        findViewById(R.id.btn_show_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });
    }

    // 显示上一个视图
    private void previous() {
        mViewFlipper.stopFlipping();

        mViewFlipper.setInAnimation(this, R.anim.anim_enter_from_top);
        mViewFlipper.setOutAnimation(this, R.anim.anim_exit_to_bottom);
        mViewFlipper.showPrevious();
    }

    // 手动开始轮播
    private void play() {
        mViewFlipper.setInAnimation(this, R.anim.anim_enter_from_bottom);
        mViewFlipper.setOutAnimation(this, R.anim.anim_exit_to_top);
        mViewFlipper.startFlipping();
    }

    // 显示下一个视图
    private void next() {
        mViewFlipper.stopFlipping();

        mViewFlipper.setInAnimation(this, R.anim.anim_enter_from_bottom);
        mViewFlipper.setOutAnimation(this, R.anim.anim_exit_to_top);
        mViewFlipper.showNext();
    }

}
