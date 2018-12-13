package com.blog.demo.time;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.TextView;

import com.blog.demo.R;

/**
 * Created by cn on 2017/11/10.
 */

public class SystemClockActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_system_clock);

        TextView tv = (TextView) findViewById(R.id.tv1);
        tv.setText(SystemClock.uptimeMillis() + "");

        tv = (TextView) findViewById(R.id.tv2);
        tv.setText(SystemClock.elapsedRealtime() + "");

        tv = (TextView) findViewById(R.id.tv3);
        tv.setText(SystemClock.currentThreadTimeMillis() + "");
    }

}
