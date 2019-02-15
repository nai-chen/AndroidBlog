package com.blog.demo.component.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;

import com.blog.demo.LogTool;
import com.blog.demo.R;

public class BroadcastReceiverActivity extends Activity implements View.OnClickListener {
    private LocalBroadcastManager mLocalBroadcastManager;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            LogTool.logi("BroadcastReceiverActivity", "onReceive " + intent.getStringExtra("value"));
//            abortBroadcast();

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component_receiver);

        findViewById(R.id.btn_register_receiver).setOnClickListener(this);
        findViewById(R.id.btn_unregister_receiver).setOnClickListener(this);
        findViewById(R.id.btn_send_broadcast).setOnClickListener(this);
        findViewById(R.id.btn_send_order_broadcast).setOnClickListener(this);
        findViewById(R.id.btn_local_register_receiver).setOnClickListener(this);
        findViewById(R.id.btn_local_unregister_receiver).setOnClickListener(this);

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_register_receiver) {
            IntentFilter intentFilter = new IntentFilter("com.blog.demo.action.receiver");
            intentFilter.setPriority(500);
            registerReceiver(mReceiver, intentFilter);
        } else if (v.getId() == R.id.btn_unregister_receiver) {
            unregisterReceiver(mReceiver);
        } else if (v.getId() == R.id.btn_send_broadcast) {
            Intent intent = new Intent("com.blog.demo.action.receiver");
            intent.putExtra("value", "broadcast");
            sendBroadcast(intent);
        } else if (v.getId() == R.id.btn_send_order_broadcast) {
            Intent intent = new Intent("com.blog.demo.action.receiver");
            intent.putExtra("value", "broadcast");
            sendOrderedBroadcast(intent, null);
        } else if (v.getId() == R.id.btn_local_register_receiver) {
            IntentFilter intentFilter = new IntentFilter("com.blog.demo.action.receiver");
            intentFilter.setPriority(500);
            mLocalBroadcastManager.registerReceiver(mReceiver, intentFilter);
        } else if (v.getId() == R.id.btn_local_unregister_receiver) {
            mLocalBroadcastManager.unregisterReceiver(mReceiver);
        }
    }

}
