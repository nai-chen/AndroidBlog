package com.blog.demo.image;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.blog.demo.image.widget.FontMetricsView;

public class FontMetricsActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new FontMetricsView(this));
    }

}
