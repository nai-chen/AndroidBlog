package com.blog.demo.component

import android.app.Activity
import android.media.AudioManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import com.blog.demo.R

class AudioManagerActivity : Activity(), View.OnClickListener {

    private lateinit var mRgStreamType: RadioGroup
    private lateinit var mRgDirection: RadioGroup
    private lateinit var mCbShowUI: CheckBox
    private lateinit var mCbAllowRingerModes: CheckBox
    private lateinit var mCbPlaySound: CheckBox
    private lateinit var mCbRemoveSoundAndVibrate: CheckBox
    private lateinit var mCbVibrate: CheckBox
    
    private lateinit var audioManager: AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_component_audio_manager)

        findViewById<Button>(R.id.btn_adjust_stream_volume).setOnClickListener(this)

        mRgStreamType = findViewById(R.id.rg_stream_type)
        mRgDirection = findViewById(R.id.rg_direction)

        mCbShowUI = findViewById(R.id.cb_show_ui)
        mCbAllowRingerModes = findViewById(R.id.cb_allow_ringer_modes)
        mCbPlaySound = findViewById(R.id.cb_play_sound)
        mCbRemoveSoundAndVibrate = findViewById(R.id.cb_remove_sound_and_vibrate)
        mCbVibrate = findViewById(R.id.cb_vibrate)

        audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_adjust_stream_volume) {
            audioManager.adjustStreamVolume(getStreamType(), getDirection(), getFlag())
        }
    }

    fun getStreamType(): Int {
        if (mRgStreamType.checkedRadioButtonId == R.id.rb_stream_voice_call) {
            return AudioManager.STREAM_VOICE_CALL
        } else if (mRgStreamType.checkedRadioButtonId == R.id.rb_stream_system) {
            return AudioManager.STREAM_SYSTEM
        } else if (mRgStreamType.checkedRadioButtonId == R.id.rb_stream_ring) {
            return AudioManager.STREAM_RING
        } else if (mRgStreamType.checkedRadioButtonId == R.id.rb_stream_music) {
            return AudioManager.STREAM_MUSIC
        } else if (mRgStreamType.checkedRadioButtonId == R.id.rb_stream_alarm) {
            return AudioManager.STREAM_ALARM
        } else if (mRgStreamType.checkedRadioButtonId == R.id.rb_stream_notification) {
            return AudioManager.STREAM_NOTIFICATION
        }
        throw IllegalArgumentException()
    }

    fun getDirection(): Int {
        if (mRgDirection.checkedRadioButtonId == R.id.rb_adjust_raise) {
            return AudioManager.ADJUST_RAISE
        } else if (mRgDirection.checkedRadioButtonId == R.id.rb_adjust_lower) {
            return AudioManager.ADJUST_LOWER
        } else if (mRgDirection.checkedRadioButtonId == R.id.rb_adjust_same) {
            return AudioManager.ADJUST_SAME
        } else if (mRgDirection.checkedRadioButtonId == R.id.rb_adjust_mute) {
            return AudioManager.ADJUST_MUTE
        } else if (mRgDirection.checkedRadioButtonId == R.id.rb_adjust_unmute) {
            return AudioManager.ADJUST_UNMUTE
        } else if (mRgDirection.checkedRadioButtonId == R.id.rb_adjust_toggle_mute) {
            return AudioManager.ADJUST_TOGGLE_MUTE
        }
        throw IllegalArgumentException()
    }

    private fun getFlag(): Int {
        var flag = 0
        if (mCbShowUI!!.isChecked) {
            flag = flag or AudioManager.FLAG_SHOW_UI
        }
        if (mCbAllowRingerModes.isChecked) {
            flag = flag or AudioManager.FLAG_ALLOW_RINGER_MODES
        }
        if (mCbPlaySound.isChecked) {
            flag = flag or AudioManager.FLAG_PLAY_SOUND
        }
        if (mCbRemoveSoundAndVibrate.isChecked) {
            flag = flag or AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE
        }
        if (mCbVibrate.isChecked) {
            flag = flag or AudioManager.FLAG_VIBRATE
        }
        return flag
    }

}