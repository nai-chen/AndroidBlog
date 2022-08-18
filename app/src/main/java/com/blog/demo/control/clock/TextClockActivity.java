package com.blog.demo.control.clock;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.blog.demo.R;

public class TextClockActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_control_clock_text);
    }
}
