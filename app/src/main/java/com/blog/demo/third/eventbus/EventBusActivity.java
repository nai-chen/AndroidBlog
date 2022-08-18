package com.blog.demo.third.eventbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.blog.demo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusActivity extends Activity implements View.OnClickListener {
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_third_event_bus);

        findViewById(R.id.btn_register).setOnClickListener(this);
        findViewById(R.id.btn_goto_publisher).setOnClickListener(this);

        textView = findViewById(R.id.text_view);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_register) {
            EventBus.getDefault().register(this);
        } else if (v.getId() == R.id.btn_goto_publisher) {
            startActivity(new Intent(this, EventBusPublisherActivity.class));
        }
    }

    @Subscribe (threadMode = ThreadMode.MAIN, sticky = true)
    public void onReceiveMessage(MessageEvent event) {
        textView.setText(event.getMessage());
        Toast.makeText(this, "收到事件", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
