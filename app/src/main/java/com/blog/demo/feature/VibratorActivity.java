package com.blog.demo.feature;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;

import androidx.annotation.Nullable;

import com.blog.demo.R;

public class VibratorActivity extends Activity implements View.OnClickListener {
    private Vibrator vibrator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_component_vibrator_manager);
        findViewById(R.id.btn_vibrator).setOnClickListener(this);
        findViewById(R.id.btn_vibrator_wave).setOnClickListener(this);
        findViewById(R.id.btn_vibrator_effect).setOnClickListener(this);
        findViewById(R.id.btn_vibrator_effect_wave).setOnClickListener(this);
        findViewById(R.id.btn_cancel_vibrator).setOnClickListener(this);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_vibrator) {
            vibrator.vibrate(1000);
        } else if (v.getId() == R.id.btn_vibrator_wave) {
            vibrator.vibrate(new long[]{300, 1000, 200, 200}, 2);
        } else if (v.getId() == R.id.btn_cancel_vibrator) {
            vibrator.cancel();
        } else if (v.getId() == R.id.btn_vibrator_effect) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(1000, -1));
            }
        } else if (v.getId() == R.id.btn_vibrator_effect_wave) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createWaveform(new long[]{200, 1000, 200, 200}, 2));
            }
        }
    }
}
