package com.blog.demo.component;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.blog.demo.DemoService;
import com.blog.demo.LogUtil;
import com.blog.demo.R;

/**
 * Created by cn on 2017/2/14.
 */

public class ServiceActivity extends Activity implements View.OnClickListener {
    private ServiceConnection sc;
    private DemoService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_service);

        findViewById(R.id.btn_start_service).setOnClickListener(this);
        findViewById(R.id.btn_stop_service).setOnClickListener(this);
        findViewById(R.id.btn_bind_service).setOnClickListener(this);
        findViewById(R.id.btn_unbind_service).setOnClickListener(this);

        LogUtil.log("ServiceActivity", "onCreate " + Thread.currentThread().getName());
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
            LogUtil.log("ServiceActivity", "bindService " + Thread.currentThread().getName());
        }
    }

    private void unbindService() {
        if (sc != null) {
            unbindService(sc);
            sc = null;
        }
    }

}
