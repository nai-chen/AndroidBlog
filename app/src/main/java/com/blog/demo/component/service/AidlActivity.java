package com.blog.demo.component.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blog.demo.R;

public class AidlActivity extends Activity {
    private IAidlInterface aidlInterface;
    private ServiceConnection sc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindAidlService();

        setContentView(R.layout.activity_component_aidl_service);

        final TextView tv = findViewById(R.id.tv_aidl);
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    tv.setText(Integer.toString(aidlInterface.add(5, 6)));
                } catch (RemoteException e) {
                }
            }
        });
    }

    private void bindAidlService() {
        sc = new ServiceConnection(){

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                aidlInterface = IAidlInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                aidlInterface = null;
            }
        };
        bindService(new Intent(this, AidlService.class), sc, BIND_AUTO_CREATE);
    }

}
