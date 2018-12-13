package com.blog.demo.system;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;

import com.blog.demo.R;

/**
 * Created by cn on 2017/6/23.
 */

public class ConnectivityManagerActivity extends Activity {
    private TextView mTvContent;

    private ConnectivityManager mManager;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                checkState();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        setContentView(R.layout.activity_connectivity);

        mTvContent = (TextView) findViewById(R.id.tv_content);

        checkState();
    }

    private void checkState() {
        NetworkInfo networkInfo = mManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            mTvContent.setText("网络未启用");
        } else {
            if (networkInfo.isConnected()) {
                if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    mTvContent.setText("移动网络");
                } else if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    mTvContent.setText("Wifi网络");
                } else {
                    mTvContent.setText("其他网络");
                }
            } else {
                mTvContent.append("网络不可用");
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(mReceiver);
    }
}
