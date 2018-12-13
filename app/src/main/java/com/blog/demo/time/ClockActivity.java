package com.blog.demo.time;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AnalogClock;
import android.widget.DigitalClock;

import com.blog.demo.R;

/**
 * Created by cn on 2017/4/1.
 */

public class ClockActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_clock);

        AnalogClock ac = (AnalogClock) findViewById(R.id.ac_time);

        DigitalClock dc = (DigitalClock) findViewById(R.id.dc_time);
    }
}
