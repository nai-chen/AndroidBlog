package com.blog.demo.time;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;

import com.blog.demo.R;

/**
 * Created by cn on 2017/11/2.
 */

public class ChronometerActivity extends Activity implements View.OnClickListener {
    private Chronometer mChronometer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chronometer);

        mChronometer = (Chronometer) findViewById(R.id.chronometer);

        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_stop).setOnClickListener(this);
        findViewById(R.id.btn_reset).setOnClickListener(this);
        findViewById(R.id.btn_count_down).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_start) {
            mChronometer.start();
        } else if (v.getId() == R.id.btn_stop) {
            mChronometer.stop();
        } else if (v.getId() == R.id.btn_reset) {
            mChronometer.setBase(SystemClock.elapsedRealtime() - 100 * 60 * 60 * 1000 + 50 * 1000);
        } else if (v.getId() == R.id.btn_count_down) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                mChronometer.setBase(SystemClock.elapsedRealtime() + 60 * 60 * 1000);
                mChronometer.setCountDown(true);
            }
        }
    }
}
