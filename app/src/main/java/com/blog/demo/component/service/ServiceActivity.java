package com.blog.demo.component.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import androidx.annotation.Nullable;

import com.blog.demo.LogTool;
import com.blog.demo.R;

public class ServiceActivity extends Activity implements View.OnClickListener {
    private static final String LOG_TAG = "ServiceActivity";

    private ServiceConnection sc;
    private DemoService mService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_component_service);

        findViewById(R.id.btn_start_service).setOnClickListener(this);
        findViewById(R.id.btn_stop_service).setOnClickListener(this);
        findViewById(R.id.btn_bind_service).setOnClickListener(this);
        findViewById(R.id.btn_unbind_service).setOnClickListener(this);

        LogTool.logi(LOG_TAG, "onCreate " + Thread.currentThread().getName());
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_start_service:
                startService();
                break;
            case R.id.btn_stop_service:
                stopService();
                break;
            case R.id.btn_bind_service:
                bindService();
                break;
            case R.id.btn_unbind_service:
                unbindService();
                break;
        }
    }

    private void startService() {
        Intent intent = new Intent(this, DemoService.class);
        startService(intent);
    }

    private void stopService() {
        Intent intent = new Intent(this, DemoService.class);
        stopService(intent);
    }

    private void bindService() {
        if (sc == null) {
            sc = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    mService = ((DemoService.DemoBind) service).getService();
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    mService = null;
                }
            };
            Intent intent = new Intent(this, DemoService.class);
            bindService(intent, sc, BIND_AUTO_CREATE);
            LogTool.logi(LOG_TAG, "bindService " + Thread.currentThread().getName());
        }
    }

    private void unbindService() {
        if (sc != null) {
            unbindService(sc);
            sc = null;
        }
    }

}
