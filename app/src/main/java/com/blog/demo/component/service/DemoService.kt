package com.blog.demo.component.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.blog.demo.LogTool

class DemoService : Service() {
    val LOG_TAG = "DemoService"

    private val mBinder: IBinder = DemoBind()

    override fun onBind(intent: Intent?): IBinder? {
        LogTool.logi(LOG_TAG, "onBind")
        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        LogTool.logi(LOG_TAG, "onUnbind")
        return super.onUnbind(intent)
    }

    override fun onCreate() {
        super.onCreate()
        LogTool.logi(LOG_TAG, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        LogTool.logi(LOG_TAG, "onStartCommand " + Thread.currentThread().name)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        LogTool.logi(LOG_TAG, "onDestroy")
    }

    inner class DemoBind : Binder() {

        fun getService(): DemoService = this@DemoService

    }

}