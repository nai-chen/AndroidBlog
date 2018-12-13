package com.blog.demo.media;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.blog.demo.R;

/**
 * Created by cn on 2017/10/30.
 */

public class ViewSwitcherActivity extends Activity implements View.OnClickListener {

    private ViewSwitcher mViewSwitcher;
    private int mPosition = 0;
    private int[] mResIdArray = {
            R.drawable.switcher1,
            R.drawable.switcher2,
            R.drawable.switcher3,
            R.drawable.switcher4,
    };
    private int mCount = mResIdArray.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_switcher);

        final ViewSwitcher viewSwitcher = (ViewSwitcher) findViewById(R.id.view_switcher1);
        findViewById(R.id.btn_show_next1).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                viewSwitcher.showNext();
            }
        });

        mViewSwitcher = (ViewSwitcher) findViewById(R.id.view_switcher);

        mViewSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView iv = new ImageView(ViewSwitcherActivity.this);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                iv.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                return iv;
            }
        });

        findViewById(R.id.btn_show_previous).setOnClickListener(this);
        findViewById(R.id.btn_show_next).setOnClickListener(this);

        mPosition = -1;
        next();
    }

    private void next() {
        ++mPosition;
        setSelection((ImageView) mViewSwitcher.getNextView());

        mViewSwitcher.setInAnimation(this, R.anim.anim_enter_from_bottom);
        mViewSwitcher.setOutAnimation(this, R.anim.anim_exit_to_top);
        mViewSwitcher.showNext();
    }

    private void previous() {
        --mPosition;
        setSelection((ImageView) mViewSwitcher.getNextView());

        mViewSwitcher.setInAnimation(this, R.anim.anim_enter_from_top);
        mViewSwitcher.setOutAnimation(this, R.anim.anim_exit_to_bottom);
        mViewSwitcher.showPrevious();
    }

    private void setSelection(ImageView imageView) {
        if (mPosition < 0) {
            mPosition = mCount - 1;
        } else if (mPosition >= mCount) {
            mPosition = 0;
        }
        imageView.setImageResource(mResIdArray[mPosition]);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_show_previous) {
            previous();
        } else if (v.getId() == R.id.btn_show_next) {
            next();
        }
    }

}
