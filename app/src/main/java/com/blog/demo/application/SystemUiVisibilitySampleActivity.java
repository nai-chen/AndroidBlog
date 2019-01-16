package com.blog.demo.application;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;

import com.blog.demo.LogTool;
import com.blog.demo.R;

public class SystemUiVisibilitySampleActivity extends Activity {
    private static final String LOG_TAG = "SystemUiVisibilitySampleActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int value = getIntent().getIntExtra("value", 0);
        boolean fitsSystemWindows = getIntent().getBooleanExtra("fitsSystemWindows", false);

        LogTool.logi(LOG_TAG, "uiOptions = " + value);
        LogTool.logi(LOG_TAG, "fitsSystemWindows = " + fitsSystemWindows);

        getWindow().getDecorView().setSystemUiVisibility(value);
        if ((value & View.SYSTEM_UI_FLAG_FULLSCREEN) == View.SYSTEM_UI_FLAG_FULLSCREEN) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_application_system_ui_visibility_sample);
        findViewById(R.id.layout_system_ui_visibility).setFitsSystemWindows(fitsSystemWindows);

    }

}
