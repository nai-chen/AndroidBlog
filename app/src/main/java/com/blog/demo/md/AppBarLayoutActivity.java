package com.blog.demo.md;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.blog.demo.R;
import com.google.android.material.appbar.AppBarLayout;

public class AppBarLayoutActivity extends Activity implements View.OnClickListener {

    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design_app_bar_layout);
        toolbar = findViewById(R.id.toolbar);

        findViewById(R.id.btn_scroll).setOnClickListener(this);
        findViewById(R.id.btn_enter_always).setOnClickListener(this);
        findViewById(R.id.btn_enter_always_collapsed).setOnClickListener(this);
        findViewById(R.id.btn_exit_until_collapsed).setOnClickListener(this);
        findViewById(R.id.btn_snap).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        AppBarLayout.LayoutParams lp = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        if (v.getId() == R.id.btn_scroll) {
            lp.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
        } else if (v.getId() == R.id.btn_enter_always) {
            lp.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                    | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
        } else if (v.getId() == R.id.btn_enter_always_collapsed) {
            lp.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                    | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                    | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED);
        } else if (v.getId() == R.id.btn_exit_until_collapsed) {
            lp.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                    | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
        } else if (v.getId() == R.id.btn_snap) {
            lp.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                    | AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);
        }
        toolbar.setLayoutParams(lp);
    }
}
