package com.blog.demo.application;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blog.demo.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by cn on 2017/11/9.
 */

public class HandlerActivity extends Activity implements View.OnClickListener {
    private final static int UPDATE_TIME = 1;

    private TextView mTv1, mTv2;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                // 根据what的值处理不同操作
                case UPDATE_TIME:
                    mTv1.setText(getCurrentTime());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        mTv1 = (TextView) findViewById(R.id.tv1);
        mTv1.setText(getCurrentTime());

        mTv2 = (TextView) findViewById(R.id.tv2);
        mTv2.setText(getCurrentTime());

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                new Thread() {
                    @Override
                    public void run() {
                        mHandler.sendEmptyMessage(UPDATE_TIME);
                    }
                }.start();
                break;
            case R.id.btn2:
                new Thread() {
                    @Override
                    public void run() {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mTv2.setText(getCurrentTime());
                            }
                        });
                    }
                }.start();
                break;
        }
    }

    private String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return df.format(calendar.getTime());
    }


}
