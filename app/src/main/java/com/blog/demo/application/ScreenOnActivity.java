package com.blog.demo.application;

import android.app.Activity;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.blog.demo.R;

/**
 * Created by cn on 2017/11/10.
 */

public class ScreenOnActivity extends Activity implements View.OnClickListener {
    private boolean mFlagSet = false;
    private boolean mScreenOnSet = false;
    private PowerManager.WakeLock mWakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_screen_on);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                if (mFlagSet) {
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                    showToast("clear flags");
                } else {
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                    showToast("add flags");
                }
                mFlagSet = !mFlagSet;
                break;
            case R.id.btn2:
                if (mScreenOnSet) {
                    findViewById(R.id.btn2).setKeepScreenOn(false);
                    showToast("screen off");
                } else {
                    findViewById(R.id.btn2).setKeepScreenOn(true);
                    showToast("screen on");
                }

                mScreenOnSet = !mScreenOnSet;
                break;
            case R.id.btn3:
                if (mWakeLock == null) {
                    PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
                    mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "TAG");
                    mWakeLock.acquire(60 * 1000);
                    showToast("lock acquire");
                } else {
                    mWakeLock.release();
                    mWakeLock = null;
                    showToast("lock release");
                }

                break;
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}
