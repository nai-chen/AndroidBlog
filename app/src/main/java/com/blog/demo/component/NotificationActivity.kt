package com.blog.demo.component

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.RemoteViews
import android.widget.Toast
import com.blog.demo.R

class NotificationActivity : Activity(), View.OnClickListener {
    private val NOTIFICATION_ACTION = "com.blog.demo.notification"

    private lateinit var mRadioGroup: RadioGroup

    private lateinit var nm: NotificationManager
    private val mReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val value = intent.getStringExtra("value")
            if (value != null) {
                Toast.makeText(this@NotificationActivity, value, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_component_notification_manager)
        findViewById<Button>(R.id.btn_send_notification).setOnClickListener(this)
        findViewById<Button>(R.id.btn_send_notification_large).setOnClickListener(this)
        findViewById<Button>(R.id.btn_cancel_notification).setOnClickListener(this)
        findViewById<Button>(R.id.btn_cancel_all).setOnClickListener(this)
        findViewById<Button>(R.id.btn_pending_intent).setOnClickListener(this)

        mRadioGroup = findViewById(R.id.rg_notification)
        findViewById<Button>(R.id.btn_remote_view).setOnClickListener(this)
        nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intentFilter = IntentFilter()
        intentFilter.addAction(NOTIFICATION_ACTION)
        registerReceiver(mReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mReceiver)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_send_notification) {
            nm.notify(1, build().build())
        } else if (v.id == R.id.btn_send_notification_large) {
            nm.notify(2, build().setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.notification_large)).build())
        } else if (v.id == R.id.btn_cancel_notification) {
            nm.cancel(1)
        } else if (v.id == R.id.btn_cancel_all) {
            nm.cancelAll()
        } else if (v.id == R.id.btn_pending_intent) {
            val intent = Intent(NOTIFICATION_ACTION)
            var flag = 0
            if (mRadioGroup.checkedRadioButtonId == R.id.rb_cancel_current) {
                flag = PendingIntent.FLAG_CANCEL_CURRENT
                intent.putExtra("value", "cancel current")
            } else if (mRadioGroup.checkedRadioButtonId == R.id.rb_no_create) {
                flag = PendingIntent.FLAG_NO_CREATE
                intent.putExtra("value", "no create")
            } else if (mRadioGroup.checkedRadioButtonId == R.id.rb_one_shot) {
                flag = PendingIntent.FLAG_ONE_SHOT
                intent.putExtra("value", "one shot")
            } else if (mRadioGroup.checkedRadioButtonId == R.id.rb_update_current) {
                flag = PendingIntent.FLAG_UPDATE_CURRENT
                intent.putExtra("value", "update current")
            }
            val pendingIntent = PendingIntent.getBroadcast(this, 1000, intent, flag)
            val notification: Notification = build().build()
            notification.contentIntent = pendingIntent
            nm.notify(3, notification)
        } else if (v.id == R.id.btn_remote_view) {
            remoteView()
        }
    }

    private fun build(): Notification.Builder {
        var builder = Notification.Builder(this)
            .setSmallIcon(R.drawable.notification_small)
            .setContentTitle("标题")
            .setContentText("内容")
            .setTicker("您有新消息") // 状态栏显示信息
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //builder的channelId需和下面channel的保持一致；
            builder.setChannelId("channel_id")
            var channel = NotificationChannel("channel_id","channel_name", NotificationManager.IMPORTANCE_DEFAULT)
            channel.setBypassDnd(true)//设置可以绕过请勿打扰模式
            channel.canBypassDnd()//可否绕过请勿打扰模式
            //锁屏显示通知
            channel.lockscreenVisibility = Notification.VISIBILITY_SECRET
            channel.shouldShowLights()//是否会闪光
            channel.enableLights(true)//闪光
            //指定闪光时的灯光颜色，为了兼容低版本在上面builder上通过setLights方法设置了
            //channel.setLightColor(Color.RED)
            channel.canShowBadge()//桌面launcher消息角标
            channel.enableVibration(true)//是否允许震动
            //震动模式，第一次100ms，第二次100ms，第三次200ms，为了兼容低版本在上面builder上设置了
            //channel.setVibrationPattern(new long[]{100,100,200})
            channel.getAudioAttributes()//获取系统通知响铃声音的配置
            channel.getGroup()//获取通知渠道组
            //绑定通知渠道
            nm.createNotificationChannel(channel)
        }

        return builder
    }

    private fun remoteView() {
        val builder = build()
        val remoteViews = RemoteViews("com.blog.demo", R.layout.layout_remote_view_notification)
        remoteViews.setTextViewText(R.id.tv_name, "Jack")
        remoteViews.setTextViewText(R.id.tv_address, "BeiJing")


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setCustomContentView(remoteViews)
        } else {
            builder.setContent(remoteViews)
        }
        nm.notify(4, builder.build())
    }

}