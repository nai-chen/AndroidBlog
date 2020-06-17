package com.blog.demo.feature;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.blog.demo.R;

public class BatteryManagerActivity extends Activity {
    private TextView textView;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            textView.setText("Status = " + getStatus(intent) + "\n"
                    + "Health = " + getHealth(intent) + "\n"
                    + "Present = " + intent.getBooleanExtra(BatteryManager.EXTRA_PRESENT, false) + "\n"
                    + "Level = " +intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0) + "\n"
                    + "Scale = " + intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0) + "\n"
                    + "Plugged = " + getPlug(intent) + "\n"
                    + "Voltage = " + intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0) + "\n"
                    + "Temperature = " + intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) + "\n"
                    + "Technology = " + intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY));
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_text);
        textView = findViewById(R.id.text_view);

        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    private String getStatus(Intent intent) {
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN);
        if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
            return "CHARGING";
        } else if (status == BatteryManager.BATTERY_STATUS_DISCHARGING) {
            return "DISCHARGING";
        } else if (status == BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
            return "NOT_CHARGING";
        } else if (status == BatteryManager.BATTERY_STATUS_FULL) {
            return "FULL";
        } else {
            return "UNKNOWN";
        }
    }

    private String getHealth(Intent intent) {
        int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, BatteryManager.BATTERY_HEALTH_UNKNOWN);
        if (health == BatteryManager.BATTERY_HEALTH_GOOD) {
            return "GOOD";
        } else if (health == BatteryManager.BATTERY_HEALTH_OVERHEAT) {
            return "OVERHEAT";
        } else if (health == BatteryManager.BATTERY_HEALTH_DEAD) {
            return "DEAD";
        } else if (health == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE) {
            return "OVER_VOLTAGE";
        } else if (health == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE) {
            return "UNSPECIFIED_FAILURE";
        } else if (health == BatteryManager.BATTERY_HEALTH_COLD) {
            return "COLD";
        } else {
            return "UNKNOWN";
        }
    }

    private String getPlug(Intent intent) {
        int plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, BatteryManager.BATTERY_PLUGGED_AC);
        if (plug == BatteryManager.BATTERY_PLUGGED_USB) {
            return "USB";
        } else if (plug == BatteryManager.BATTERY_PLUGGED_WIRELESS) {
            return "WIRELESS";
        } else {
            return "AC";
        }
    }

}
