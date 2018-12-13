package com.blog.demo.component;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Toast;

import com.blog.demo.IAidlInterface;
import com.blog.demo.LogUtil;
import com.blog.demo.AidlService;
import com.blog.demo.R;

/**
 * Created by cn on 2017/2/14.
 */

public class AidlActivity extends Activity implements View.OnClickListener {
    private IAidlInterface mInterface;

    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mInterface = IAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mInterface = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_aidl);
        findViewById(R.id.btn_start).setOnClickListener(this);

        bindService(new Intent(this, AidlService.class), sc, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(sc);
    }

    @Override
    public void onClick(View v) {
        LogUtil.log("AidlActivity", "onClick");
        if (mInterface == null) return;
        if (v.getId() == R.id.btn_start) {
            LogUtil.log("AidlActivity", "onStart");
            try {
                Toast.makeText(this, "" + mInterface.add(2, 3), Toast.LENGTH_LONG).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
