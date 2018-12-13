package com.blog.demo.control;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.blog.demo.R;

/**
 * Created by cn on 2018/4/3.
 */

public class AppBarLayoutActivity extends Activity implements View.OnClickListener {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_app_bar_layout);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        findViewById(R.id.tv_text1).setOnClickListener(this);
        findViewById(R.id.tv_text2).setOnClickListener(this);
        findViewById(R.id.tv_text3).setOnClickListener(this);
        findViewById(R.id.tv_text4).setOnClickListener(this);
        findViewById(R.id.tv_text5).setOnClickListener(this);
        findViewById(R.id.tv_text6).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) mToolbar.getLayoutParams();
        if (v.getId() == R.id.tv_text1) {
            params.setScrollFlags(0);
        } else if (v.getId() == R.id.tv_text2) {
            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
        } else if (v.getId() == R.id.tv_text3) {
            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|
                    AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
        } else if (v.getId() == R.id.tv_text4) {
            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|
                    AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS |
                    AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED);
        } else if (v.getId() == R.id.tv_text5) {
            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|
                    AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
        } else if (v.getId() == R.id.tv_text6) {
            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|
                    AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);
        }
        mToolbar.setLayoutParams(params);
    }
}
