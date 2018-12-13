package com.blog.demo.system;

import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

import com.blog.demo.R;

/**
 * Created by cn on 2017/3/31.
 */

public class VibratorActivity extends Activity implements View.OnClickListener {
    private Vibrator mVibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        setContentView(R.layout.activity_vibrator);

        findViewById(R.id.btn_two_second).setOnClickListener(this);
        findViewById(R.id.btn_pattern_once).setOnClickListener(this);
        findViewById(R.id.btn_pattern_multiply).setOnClickListener(this);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 震动持续2秒
            case R.id.btn_two_second:
                mVibrator.vibrate(2000);
                break;
            // 关闭300毫秒，震动1000毫秒，关闭200毫秒，震动200毫秒
            case R.id.btn_pattern_once:
                mVibrator.vibrate(new long[]{300, 1000, 200, 200}, -1);
                break;
            // 关闭300毫秒，震动1000毫秒，关闭200毫秒，震动200毫秒，关闭200毫秒，震动200毫秒 ....
            case R.id.btn_pattern_multiply:
                mVibrator.vibrate(new long[]{300, 1000, 200, 200}, 2);
                break;
            // 取消震动
            case R.id.btn_cancel:
                mVibrator.cancel();
                break;
        }

    }
}
