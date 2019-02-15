package com.blog.demo.component.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.blog.demo.LogTool;

public class DemoBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        LogTool.logi("DemoBroadcastReceiver", "onReceive " + intent.getStringExtra("value"));
    }

}
