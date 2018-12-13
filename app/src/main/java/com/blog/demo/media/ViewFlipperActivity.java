package com.blog.demo.media;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

import com.blog.demo.R;

/**
 * Created by cn on 2017/10/30.
 */

public class ViewFlipperActivity extends Activity implements View.OnClickListener {
    private ViewFlipper mViewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);

        mViewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
        findViewById(R.id.btn_show_previous).setOnClickListener(this);
        findViewById(R.id.btn_play).setOnClickListener(this);
        findViewById(R.id.btn_show_next).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_show_previous) {
            previous();
        } else if (v.getId() == R.id.btn_play) {
            play();
        } else if (v.getId() == R.id.btn_show_next) {
            next();
        }
    }

    private void previous() {
        mViewFlipper.stopFlipping();

        mViewFlipper.setInAnimation(this, R.anim.anim_enter_from_top);
        mViewFlipper.setOutAnimation(this, R.anim.anim_exit_to_bottom);
        mViewFlipper.showPrevious();
    }

    private void play() {
        mViewFlipper.setInAnimation(this, R.anim.anim_enter_from_bottom);
        mViewFlipper.setOutAnimation(this, R.anim.anim_exit_to_top);
        mViewFlipper.startFlipping();
    }

    private void next() {
        mViewFlipper.stopFlipping();

        mViewFlipper.setInAnimation(this, R.anim.anim_enter_from_bottom);
        mViewFlipper.setOutAnimation(this, R.anim.anim_exit_to_top);
        mViewFlipper.showNext();
    }

}
