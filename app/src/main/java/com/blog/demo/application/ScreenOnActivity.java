package com.blog.demo.application;

import android.app.Activity;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;

import com.blog.demo.R;

public class ScreenOnActivity extends Activity implements View.OnClickListener {

    private View mContentView;
    private PowerManager.WakeLock wakeLock;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_screen_on);

        mContentView = findViewById(R.id.view_content);

        findViewById(R.id.btn_add_flags).setOnClickListener(this);
        findViewById(R.id.btn_clear_flags).setOnClickListener(this);
        findViewById(R.id.btn_keep_screen_on).setOnClickListener(this);
        findViewById(R.id.btn_keep_screen_off).setOnClickListener(this);
        findViewById(R.id.btn_new_wake_lock).setOnClickListener(this);
        findViewById(R.id.btn_release).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_add_flags) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else if (v.getId() == R.id.btn_clear_flags) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else if (v.getId() == R.id.btn_keep_screen_on) {
            mContentView.setKeepScreenOn(true);
        } else if (v.getId() == R.id.btn_keep_screen_off) {
            mContentView.setKeepScreenOn(false);
        } else if (v.getId() == R.id.btn_new_wake_lock) {
            PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "demo:tag");
            wakeLock.acquire();
        } else if (v.getId() == R.id.btn_release) {
            wakeLock.release();
        }
    }
}
