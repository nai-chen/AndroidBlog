package com.blog.demo.feature;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.blog.demo.R;

public class AlarmManagerActivity extends Activity implements View.OnClickListener {

    private final static String ACTION_ALARM = "com.blog.demo.feature.ACTION_ALARM";

    private AlarmManager alarmManager;
    private RadioGroup mRgType;
    private EditText editText;

    private PendingIntent pendingIntent;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ACTION_ALARM)) {
                Toast.makeText(AlarmManagerActivity.this, "alarm", Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_alarm_manager);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        findViewById(R.id.btn_set_alarm).setOnClickListener(this);
        findViewById(R.id.btn_set_repeat_alarm).setOnClickListener(this);
        findViewById(R.id.btn_cancel_alarm).setOnClickListener(this);
        mRgType = findViewById(R.id.rg_type);

        editText = findViewById(R.id.edit_text);
        pendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_ALARM), 0);
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(broadcastReceiver, new IntentFilter(ACTION_ALARM));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onClick(View v) {
        int time = Integer.parseInt(editText.getText().toString()) * 1000;
        if (v.getId() == R.id.btn_set_alarm) {
            alarmManager.set(getType(), time + SystemClock.elapsedRealtime(), pendingIntent);
        } else if (v.getId() == R.id.btn_set_repeat_alarm) {
            alarmManager.setRepeating(getType(), time + SystemClock.elapsedRealtime(), 10000, pendingIntent);
        } else if (v.getId() == R.id.btn_cancel_alarm) {
            alarmManager.cancel(pendingIntent);
        }
    }

    private int getType() {
        int id = mRgType.getCheckedRadioButtonId();
        if (id == R.id.rb_elapsed_realtime) {
            return AlarmManager.ELAPSED_REALTIME;
        } else if (id == R.id.rb_elapsed_realtime_wakeup) {
            return AlarmManager.ELAPSED_REALTIME_WAKEUP;
        } else if (id == R.id.rb_rtc) {
            return AlarmManager.RTC;
        } else if (id == R.id.rb_rtc_wakeup) {
            return AlarmManager.RTC_WAKEUP;
        }
        return -1;
    }
}
