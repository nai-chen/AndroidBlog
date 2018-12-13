package com.blog.demo.system;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.blog.demo.R;

/**
 * Created by cn on 2017/3/30.
 */

public class DisplayMetricsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_metrics);

        TextView tv = (TextView) findViewById(R.id.tv_dm);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        dm = getResources().getDisplayMetrics();
        tv.setText("widthPixels = " + dm.widthPixels // 宽度(像素)
                + "\nheightPixels = " + dm.heightPixels // 高度(像素)
                + "\ndensity = " + dm.density
                + "\ndensityDpi = " + dm.densityDpi
                + "\nxdpi = " + dm.xdpi
                + "\nydpi = " + dm.ydpi);

    }
}
