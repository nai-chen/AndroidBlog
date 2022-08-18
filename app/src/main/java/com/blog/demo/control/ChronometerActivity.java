package com.blog.demo.control;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;

import androidx.annotation.Nullable;

import com.blog.demo.R;

public class ChronometerActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_control_chronometer);

        Chronometer mChronometer = findViewById(R.id.chronometer);
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.setFormat("计时开始 %s");
        mChronometer.start();
    }

}
