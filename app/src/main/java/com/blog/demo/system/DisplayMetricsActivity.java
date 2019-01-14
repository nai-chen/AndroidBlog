package com.blog.demo.system;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

import com.blog.demo.LogTool;

public class DisplayMetricsActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        LogTool.logi("DisplayMetricsActivity", "widthPixels = " + dm.widthPixels);
        LogTool.logi("DisplayMetricsActivity", "heightPixels = " + dm.heightPixels);
        LogTool.logi("DisplayMetricsActivity", "densityDpi = " + dm.densityDpi);
        LogTool.logi("DisplayMetricsActivity", "density = " + dm.density);
    }
}
