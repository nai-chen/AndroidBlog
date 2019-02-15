package com.blog.demo.application.manager;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.blog.demo.R;

public class NotificationActivity extends Activity implements View.OnClickListener {
    private static final String NOTIFICATION_ACTION = "com.blog.demo.notification";

    private RadioGroup mRadioGroup;

    private NotificationManager nm;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String value = intent.getStringExtra("value");
            if (value != null) {
                Toast.makeText(NotificationActivity.this, value, Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_application_manager_notification);

        findViewById(R.id.btn_send_notification).setOnClickListener(this);
        findViewById(R.id.btn_send_notification_large).setOnClickListener(this);
        findViewById(R.id.btn_cancel_notification).setOnClickListener(this);
        findViewById(R.id.btn_cancel_all).setOnClickListener(this);
        findViewById(R.id.btn_pending_intent).setOnClickListener(this);
        mRadioGroup = findViewById(R.id.rg_notification);
        findViewById(R.id.btn_remote_view).setOnClickListener(this);

        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NOTIFICATION_ACTION);
        registerReceiver(mReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(mReceiver);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_send_notification) {
            nm.notify(1, build().build());
        } else if (v.getId() == R.id.btn_send_notification_large) {
            nm.notify(2, build()
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.notification_large))
                    .build());
        } else if (v.getId() == R.id.btn_cancel_notification) {
            nm.cancel(1);
        } else if (v.getId() == R.id.btn_cancel_all) {
            nm.cancelAll();
        } else if (v.getId() == R.id.btn_pending_intent) {
            Intent intent = new Intent(NOTIFICATION_ACTION);

            int flag = 0;
            if (mRadioGroup.getCheckedRadioButtonId() == R.id.rb_cancel_current) {
                flag = PendingIntent.FLAG_CANCEL_CURRENT;
                intent.putExtra("value", "cancel current");
            } else if (mRadioGroup.getCheckedRadioButtonId() == R.id.rb_no_create) {
                flag = PendingIntent.FLAG_NO_CREATE;
                intent.putExtra("value", "no create");
            } else if (mRadioGroup.getCheckedRadioButtonId() == R.id.rb_one_shot) {
                flag = PendingIntent.FLAG_ONE_SHOT;
                intent.putExtra("value", "one shot");
            } else if (mRadioGroup.getCheckedRadioButtonId() == R.id.rb_update_current) {
                flag = PendingIntent.FLAG_UPDATE_CURRENT;
                intent.putExtra("value", "update current");
            }

            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1000, intent, flag);
            Notification notification = build().build();
            notification.contentIntent = pendingIntent;
            nm.notify(3, notification);
        } else if (v.getId() == R.id.btn_remote_view) {
            remoteView();
        }
    }

    private Notification.Builder build() {
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.notification_small)
                .setContentTitle("标题")
                .setContentText("内容")
                .setTicker("您有新消息") // 状态栏显示信息
                .setAutoCancel(true); // 点击自动退出
        return builder;
    }

    private void remoteView() {
        Notification.Builder builder = build();
        RemoteViews remoteViews = new RemoteViews("com.blog.demo",
                R.layout.layout_remote_view_notification);
        remoteViews.setTextViewText(R.id.tv_name, "Jack");
        remoteViews.setTextViewText(R.id.tv_address, "BeiJing");

        builder.setContent(remoteViews);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            builder.setCustomContentView(remoteViews);
//        }

        nm.notify(4, builder.build());
    }

}
