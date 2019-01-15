package com.blog.demo.system;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.blog.demo.LogTool;
import com.blog.demo.R;

public class DisplayMetricsActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_text);

        TextView textView = findViewById(R.id.text_view);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        textView.setText("widthPixels = " + dm.widthPixels + "\n"
                    + "heightPixels = " + dm.heightPixels + "\n"
                    + "densityDpi = " + dm.densityDpi + "\n"
                    + "density = " + dm.density);
    }
}
