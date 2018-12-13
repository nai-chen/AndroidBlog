package com.blog.demo.system;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.ListView;

import com.blog.demo.MessageInfoAdapter;
import com.blog.demo.MessageInfoAdapter.MessageInfo;
import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cn on 2017/4/1.
 */

public class BatteryActivity extends Activity {
    private MessageInfoAdapter mAdapter;
    private List<MessageInfo> mInfoList = new ArrayList<MessageInfo>();

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                mInfoList.clear();

                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
                if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
                    mInfoList.add(new MessageInfo("status = ", "CHARGING"));
                } else if (status == BatteryManager.BATTERY_STATUS_DISCHARGING) {
                    mInfoList.add(new MessageInfo("status = ", "DISCHARGING"));
                } else if (status == BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
                    mInfoList.add(new MessageInfo("status = ", "NOT_CHARGING"));
                } else if (status == BatteryManager.BATTERY_STATUS_FULL) {
                    mInfoList.add(new MessageInfo("status = ", "FULL"));
                } else {
                    mInfoList.add(new MessageInfo("status = ", "UNKNOW"));
                }

                int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
                if (health == BatteryManager.BATTERY_HEALTH_GOOD) {
                    mInfoList.add(new MessageInfo("health = ", "GOOD"));
                } else if (health == BatteryManager.BATTERY_HEALTH_OVERHEAT) {
                    mInfoList.add(new MessageInfo("health = ", "OVERHEAT"));
                } else if (health == BatteryManager.BATTERY_HEALTH_DEAD) {
                    mInfoList.add(new MessageInfo("health = ", "DEAD"));
                } else if (health == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE) {
                    mInfoList.add(new MessageInfo("health = ", "OVER_VOLTAGE"));
                } else if (health == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE) {
                    mInfoList.add(new MessageInfo("health = ", "UNSPECIFIED_FAILURE"));
                } else if (health == BatteryManager.BATTERY_HEALTH_COLD) {
                    mInfoList.add(new MessageInfo("health = ", "COLD"));
                } else {
                    mInfoList.add(new MessageInfo("health = ", "UNKNOW"));
                }

                boolean present = intent.getBooleanExtra(BatteryManager.EXTRA_PRESENT, false);
                mInfoList.add(new MessageInfo("present = ", Boolean.toString(present)));

                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                mInfoList.add(new MessageInfo("level = ", Integer.toString(level)));
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
                mInfoList.add(new MessageInfo("scale = ", Integer.toString(scale)));

                int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
                if (plugged == BatteryManager.BATTERY_PLUGGED_AC) {
                    mInfoList.add(new MessageInfo("plugged = ", "AC"));
                } else if (plugged == BatteryManager.BATTERY_PLUGGED_USB) {
                    mInfoList.add(new MessageInfo("plugged = ", "USB"));
                } else if (plugged == BatteryManager.BATTERY_PLUGGED_WIRELESS) {
                    mInfoList.add(new MessageInfo("plugged = ", "WIRELESS"));
                } else {
                    mInfoList.add(new MessageInfo("plugged = ", "UNKNOW"));
                }

                int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
                mInfoList.add(new MessageInfo("voltage = ", Integer.toString(voltage)));

                int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
                mInfoList.add(new MessageInfo("temperature = ", Integer.toString(temperature)));

                String technology = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
                mInfoList.add(new MessageInfo("technology = ", technology));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listview);

        ListView lvMemoryInfo = (ListView) findViewById(R.id.id_listview);
        mAdapter = new MessageInfoAdapter(this, mInfoList);
        lvMemoryInfo.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(mReceiver);
    }
}
