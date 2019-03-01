package com.blog.demo.application;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.blog.demo.R;

public class BuildActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_text);
        TextView textView = findViewById(R.id.text_view);
        textView.setText("BOARD = " + Build.BOARD + "\n"
                + "BRAND = " + Build.BRAND + "\n"
                + "CPU_ABI = " + Build.CPU_ABI + "\n"
                + "DEVICE = " + Build.DEVICE + "\n"
                + "DISPLAY = " + Build.DISPLAY + "\n"
                + "FINGERPRINT = " + Build.FINGERPRINT + "\n"
                + "ID = " + Build.ID + "\n"
                + "MANUFACTURER = " + Build.MANUFACTURER + "\n"
                + "MODEL = " + Build.MODEL + "\n"
                + "PRODUCT = " + Build.PRODUCT + "\n"
                + "SERIAL = " + Build.SERIAL + "\n"
                + "TAGS = " + Build.TAGS + "\n"
                + "TYPE = " + Build.TYPE);
    }

}
