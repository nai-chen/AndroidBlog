package com.blog.demo.component

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.Button
import com.blog.demo.R

class VibratorActivity : Activity(), View.OnClickListener {

    private lateinit var vibrator: Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_component_vibrator_manager)

        findViewById<Button>(R.id.btn_vibrator).setOnClickListener(this)
        findViewById<Button>(R.id.btn_vibrator_wave).setOnClickListener(this)
        findViewById<Button>(R.id.btn_vibrator_effect).setOnClickListener(this)
        findViewById<Button>(R.id.btn_vibrator_effect_wave).setOnClickListener(this)
        findViewById<Button>(R.id.btn_cancel_vibrator).setOnClickListener(this)

        vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_vibrator) {
            vibrator.vibrate(1000)
        } else if (v.id == R.id.btn_vibrator_wave) {
            vibrator.vibrate(longArrayOf(300, 1000, 200, 200), 2)
        } else if (v.id == R.id.btn_cancel_vibrator) {
            vibrator.cancel()
        } else if (v.id == R.id.btn_vibrator_effect) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(1000, -1))
            }
        } else if (v.id == R.id.btn_vibrator_effect_wave) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createWaveform(longArrayOf(200, 1000, 200, 200), 2))
            }
        }
    }
}