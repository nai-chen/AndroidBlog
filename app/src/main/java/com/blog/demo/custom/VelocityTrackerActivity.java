package com.blog.demo.custom;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blog.demo.R;

public class VelocityTrackerActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_custom_velocity_tracker);
    }

}
