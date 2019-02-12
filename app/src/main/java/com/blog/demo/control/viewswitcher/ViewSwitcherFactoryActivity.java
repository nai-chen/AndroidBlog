package com.blog.demo.control.viewswitcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.blog.demo.R;

public class ViewSwitcherFactoryActivity extends Activity {
    private ViewSwitcher mViewSwitcher;
    private int mPosition = 0;
    private int[] mResIdArray = {
            R.drawable.switcher1,
            R.drawable.switcher2,
            R.drawable.switcher3,
            R.drawable.switcher4,
            R.drawable.switcher5
    };
    private int mCount = mResIdArray.length;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_view_switcher_factory);

        mViewSwitcher = findViewById(R.id.view_switcher);
        mViewSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView iv = new ImageView(ViewSwitcherFactoryActivity.this);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                iv.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                return iv;
            }
        });
        ((ImageView) mViewSwitcher.getCurrentView()).setImageResource(R.drawable.switcher1);
        findViewById(R.id.btn_show_previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previous();
            }
        });
        findViewById(R.id.btn_show_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });
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

}
