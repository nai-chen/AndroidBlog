package com.blog.demo.component;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import com.blog.demo.R;

public class AudioManagerActivity extends Activity implements View.OnClickListener {

    private RadioGroup mRgStreamType, mRgDirection;
    private CheckBox mCbShowUI, mCbAllowRingerModes, mCbPlaySound, mCbRemoveSoundAndVibrate, mCbVibrate;

    private AudioManager audioManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_component_audio_manager);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        findViewById(R.id.btn_adjust_stream_volume).setOnClickListener(this);
        mRgStreamType = findViewById(R.id.rg_stream_type);
        mRgDirection = findViewById(R.id.rg_direction);

        mCbShowUI = findViewById(R.id.cb_show_ui);
        mCbAllowRingerModes = findViewById(R.id.cb_allow_ringer_modes);
        mCbPlaySound = findViewById(R.id.cb_play_sound);
        mCbRemoveSoundAndVibrate = findViewById(R.id.cb_remove_sound_and_vibrate);
        mCbVibrate = findViewById(R.id.cb_vibrate);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_adjust_stream_volume) {
            audioManager.adjustStreamVolume(getStreamType(), getDirection(), getFlag());
        }
    }

    public int getStreamType() {
        if (mRgStreamType.getCheckedRadioButtonId() == R.id.rb_stream_voice_call) {
            return AudioManager.STREAM_VOICE_CALL;
        } else if (mRgStreamType.getCheckedRadioButtonId() == R.id.rb_stream_system) {
            return AudioManager.STREAM_SYSTEM;
        } else if (mRgStreamType.getCheckedRadioButtonId() == R.id.rb_stream_ring) {
            return AudioManager.STREAM_RING;
        } else if (mRgStreamType.getCheckedRadioButtonId() == R.id.rb_stream_music) {
            return AudioManager.STREAM_MUSIC;
        } else if (mRgStreamType.getCheckedRadioButtonId() == R.id.rb_stream_alarm) {
            return AudioManager.STREAM_ALARM;
        } else if (mRgStreamType.getCheckedRadioButtonId() == R.id.rb_stream_notification) {
            return AudioManager.STREAM_NOTIFICATION;
        }
        throw new IllegalArgumentException();
    }

    public int getDirection() {
        if (mRgDirection.getCheckedRadioButtonId() == R.id.rb_adjust_raise) {
            return AudioManager.ADJUST_RAISE;
        } else if (mRgDirection.getCheckedRadioButtonId() == R.id.rb_adjust_lower) {
            return AudioManager.ADJUST_LOWER;
        } else if (mRgDirection.getCheckedRadioButtonId() == R.id.rb_adjust_same) {
            return AudioManager.ADJUST_SAME;
        } else if (mRgDirection.getCheckedRadioButtonId() == R.id.rb_adjust_mute) {
            return AudioManager.ADJUST_MUTE;
        } else if (mRgDirection.getCheckedRadioButtonId() == R.id.rb_adjust_unmute) {
            return AudioManager.ADJUST_UNMUTE;
        } else if (mRgDirection.getCheckedRadioButtonId() == R.id.rb_adjust_toggle_mute) {
            return AudioManager.ADJUST_TOGGLE_MUTE;
        }
        throw new IllegalArgumentException();
    }

    private int getFlag() {
        int flag = 0;
        if (mCbShowUI.isChecked()) {
            flag |= AudioManager.FLAG_SHOW_UI;
        }
        if (mCbAllowRingerModes.isChecked()) {
            flag |= AudioManager.FLAG_ALLOW_RINGER_MODES;
        }
        if (mCbPlaySound.isChecked()) {
            flag |= AudioManager.FLAG_PLAY_SOUND;
        }
        if (mCbRemoveSoundAndVibrate.isChecked()) {
            flag |= AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE;
        }
        if (mCbVibrate.isChecked()) {
            flag |= AudioManager.FLAG_VIBRATE;
        }

        return flag;
    }
}
