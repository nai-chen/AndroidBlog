package com.blog.demo.control.clock;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.DigitalClock;

import com.blog.demo.R;

import java.lang.reflect.Field;

public class DigitalClockActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_control_clock_digital);

        DigitalClock clock = findViewById(R.id.digital_clock);
        Class cls = clock.getClass();
        try {
            Field f = cls.getDeclaredField("mFormat");
            f.setAccessible(true);
            f.set(clock, "yyyy-MM-dd hh:mm");
        } catch (Exception e) {
        }

    }
}
