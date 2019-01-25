package com.blog.demo.application;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.blog.demo.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HandlerActivity extends Activity implements View.OnClickListener {

    private final static int UPDATE_TIME = 1;

    private TextView mTextView;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                // 根据what的值处理不同操作
                case UPDATE_TIME:
                    mTextView.setText(getCurrentTime());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_handler);

        findViewById(R.id.btn_send_empty_message).setOnClickListener(this);
        findViewById(R.id.btn_post_runnable).setOnClickListener(this);

        mTextView = findViewById(R.id.text_view);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_send_empty_message) {
            mHandler.sendEmptyMessage(UPDATE_TIME);
        } else if (v.getId() == R.id.btn_post_runnable) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mTextView.setText(getCurrentTime());
                }
            });
        }
    }

    private String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

}
