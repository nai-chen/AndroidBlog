package com.blog.demo.component;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.blog.demo.R;
import com.blog.demo.control.CardViewActivity;
import com.blog.demo.control.SpinnerActivity;

/**
 * Created by cn on 2017/3/31.
 */

public class NotificationActivity extends Activity implements View.OnClickListener {
    private static final String ACTION_DELETE_EVENT = "com.blog.demo.application.DELETE_EVENT";
    private static final String ACTION_CLICK_EVENT = "com.blog.demo.application.CLICK_EVENT";

    private NotificationManager notificationManager;

    private BroadcastReceiver mReciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ACTION_CLICK_EVENT.equals(intent.getAction())) {
                Toast.makeText(NotificationActivity.this, "click event", Toast.LENGTH_LONG).show();
            } else if (ACTION_DELETE_EVENT.equals(intent.getAction())) {
                Toast.makeText(NotificationActivity.this, "delete event", Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notification);

        findViewById(R.id.btn_send1).setOnClickListener(this);
        findViewById(R.id.btn_send2).setOnClickListener(this);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_cancel_all).setOnClickListener(this);

        findViewById(R.id.btn_pending_intent_cancel_current).setOnClickListener(this);
        findViewById(R.id.btn_pending_intent_no_create).setOnClickListener(this);
        findViewById(R.id.btn_pending_intent_one_shot).setOnClickListener(this);
        findViewById(R.id.btn_pending_intent_update_current).setOnClickListener(this);

        findViewById(R.id.btn_sound_default).setOnClickListener(this);
        findViewById(R.id.btn_sound_custom).setOnClickListener(this);
        findViewById(R.id.btn_vibrate_default).setOnClickListener(this);
        findViewById(R.id.btn_vibrate_custom).setOnClickListener(this);
        findViewById(R.id.btn_light_default).setOnClickListener(this);
        findViewById(R.id.btn_light_custom).setOnClickListener(this);

        findViewById(R.id.btn_remote_view).setOnClickListener(this);
        findViewById(R.id.btn_expand).setOnClickListener(this);
        findViewById(R.id.btn_fullscreen).setOnClickListener(this);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_DELETE_EVENT);
        filter.addAction(ACTION_CLICK_EVENT);
        registerReceiver(mReciver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReciver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send1:
                sendNotification1();
                break;
            case R.id.btn_send2:
                sendNotification2();
                break;
            case R.id.btn_cancel:
                cancelNotification();
                break;
            case R.id.btn_cancel_all:
                cancelAllNotification();
                break;
            case R.id.btn_pending_intent_cancel_current:
                pendingIntentCancelCurrent();
                break;
            case R.id.btn_pending_intent_no_create:
                pendingIntentNoCreate();
                break;
            case R.id.btn_pending_intent_one_shot:
                pendingIntentOneShot();
                break;
            case R.id.btn_pending_intent_update_current:
                pendingIntentUpdateCurrent();
                break;
            case R.id.btn_sound_default:
                soundDefault();
                break;
            case R.id.btn_sound_custom:
                soundCustom();
                break;
            case R.id.btn_vibrate_default:
                vibrateDefault();
                break;
            case R.id.btn_vibrate_custom:
                vibrateCustom();
                break;
            case R.id.btn_light_default:
                lightDefault();
                break;
            case R.id.btn_light_custom:
                lightCustom();
                break;
            case R.id.btn_remote_view:
                remoteView();
                break;
            case R.id.btn_expand:
                expand();
                break;
            case R.id.btn_fullscreen:
                fullscreen();
                break;
        }
    }

    private void sendNotification1() {
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.icon_marka)
                .setContentTitle("标题")
                .setContentText("内容")
                .setTicker("您有新消息") // 设置状态栏的显示的信息
                .build();
        notification.contentIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, CardViewActivity.class), PendingIntent.FLAG_CANCEL_CURRENT);
        notificationManager.notify(1, notification);
    }

    private void sendNotification2() {
        notificationManager.notify(2, build().build());
    }

    private void cancelNotification() {
        notificationManager.cancel(1);
    }

    private void cancelAllNotification() {
        notificationManager.cancelAll();
    }

    private void pendingIntentCancelCurrent() {
        Notification.Builder builder = build();
        builder.setContentIntent(PendingIntent.getActivity(
                this, 0, new Intent(this, SpinnerActivity.class), PendingIntent.FLAG_CANCEL_CURRENT));
        notificationManager.notify(1, builder.build());
    }

    private void pendingIntentNoCreate() {
        Notification notification = build().build();
        notification.contentIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, SpinnerActivity.class), PendingIntent.FLAG_NO_CREATE);
        notificationManager.notify(2, notification);
    }

    private void pendingIntentOneShot() {
        Notification notification = build().build();
        notification.contentIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, SpinnerActivity.class), PendingIntent.FLAG_ONE_SHOT);
        notificationManager.notify(1, notification);
    }

    private void pendingIntentUpdateCurrent() {
        Notification notification = build().build();
        notification.contentIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, SpinnerActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        notificationManager.notify(1, notification);
    }

    private void soundDefault() {
        Notification notification = build().build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        notificationManager.notify(1, notification);
    }

    private void soundCustom() {

    }

    private void vibrateDefault() {
        Notification notification = build().build();
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notificationManager.notify(1, notification);
    }

    private void vibrateCustom() {

    }

    private void lightDefault() {
        Notification notification = build().build();
        notification.defaults |= Notification.DEFAULT_LIGHTS;
        notificationManager.notify(1, notification);
    }

    private void lightCustom() {
    }

    private void remoteView() {
        Notification.Builder builder = build();
        RemoteViews remoteViews = new RemoteViews("com.blog.demo",
                R.layout.listview_item_customdapter);
        remoteViews.setTextViewText(R.id.tv_name, "Jack");
        remoteViews.setTextViewText(R.id.tv_address, "BeiJing");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setCustomContentView(remoteViews);
        }

        notificationManager.notify(1, builder.build());
        // notification.visibility = Notification.VISIBILITY_PRIVATE;
    }

    private void expand() {
        Notification.Builder builder = build();
        RemoteViews remoteViews = new RemoteViews("com.blog.demo",
                R.layout.listview_item_customdapter);
        remoteViews.setTextViewText(R.id.tv_name, "Jack");
        remoteViews.setTextViewText(R.id.tv_address, "BeiJing");

        RemoteViews bigRemoteViews = new RemoteViews("com.blog.demo",
                R.layout.listview_item_customdapter);
        bigRemoteViews.setTextViewText(R.id.tv_name, "Jack");
        bigRemoteViews.setTextViewText(R.id.tv_address, "BeiJing");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setCustomContentView(remoteViews);
            builder.setCustomBigContentView(bigRemoteViews);
        }

        notificationManager.notify(1, builder.build());
    }

    private void fullscreen() {
        Notification.Builder builder = build();
        builder.setFullScreenIntent(PendingIntent.getActivity(this, 0,
                new Intent(this, SpinnerActivity.class), PendingIntent.FLAG_CANCEL_CURRENT), true);

        notificationManager.notify(1, builder.build());
    }

    private Notification.Builder build() {
        return new Notification.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon_wrong))
                .setSmallIcon(R.drawable.icon_marka)
                .setContentTitle("标题")
                .setContentText("内容")
                .setTicker("您有新消息")
                .setAutoCancel(true);
    }

}
