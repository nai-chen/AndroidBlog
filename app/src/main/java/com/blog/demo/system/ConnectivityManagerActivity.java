package com.blog.demo.system;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.blog.demo.R;

public class ConnectivityManagerActivity extends Activity {

    private ConnectivityManager connectivityManager;
    private TextView textView;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null) {
                    textView.setText("Available = " + networkInfo.isAvailable() + "\n"
                            + "Connected = " + networkInfo.isConnected() + "\n"
                            + "Type = " + getType(networkInfo.getType()) + "\n"
                            + "Roaming = " + networkInfo.isRoaming() + "\n"
                            + "State = " + networkInfo.getState() + "\n"
                            + "DetailedState = " + networkInfo.getDetailedState() + "\n"
                            + "Failover = " + networkInfo.isFailover());
                } else {
                    textView.setText("no active network info");
                }
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        textView = findViewById(R.id.text_view);
        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(mReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(mReceiver);
    }

    private String getType(int type) {
        if (type == ConnectivityManager.TYPE_MOBILE) {
            return "MOBILE";
        } else if (type == ConnectivityManager.TYPE_WIFI) {
            return "WIFI";
        } else {
            return "unknow";
        }
    }

}
