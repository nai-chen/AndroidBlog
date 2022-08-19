package com.blog.demo.component.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.blog.demo.LogTool

class AidlService : Service() {
    private var mInterface = AidlInterface()

    override fun onBind(intent: Intent?): IBinder? {
        return mInterface
    }

    private inner class AidlInterface : IAidlInterface.Stub() {

        override fun add(val1: Int, val2: Int): Int {
            LogTool.logi("AidlService", toString())
            return val1 * 10 + val2
        }

    }

}