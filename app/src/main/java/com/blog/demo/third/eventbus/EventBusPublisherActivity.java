package com.blog.demo.third.eventbus;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blog.demo.R;

import org.greenrobot.eventbus.EventBus;

public class EventBusPublisherActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_event_bus_publisher);

        findViewById(R.id.btn_publisher).setOnClickListener(this);
        findViewById(R.id.btn_publisher_sticky).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_publisher) {
            EventBus.getDefault().post(new MessageEvent("普通事件"));
        } else if (v.getId() == R.id.btn_publisher_sticky) {
            EventBus.getDefault().postSticky(new MessageEvent("粘性事件"));
        }
    }

}
