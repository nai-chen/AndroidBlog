package com.blog.demo.feature

import android.app.Activity
import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.blog.demo.R

class ActivityManagerActivity : Activity(), View.OnClickListener {
    private lateinit var mTextView: TextView
    private lateinit var am: ActivityManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_feature_activity_manager)

        findViewById<Button>(R.id.btn_show_memory_info).setOnClickListener(this)
        findViewById<Button>(R.id.btn_check_app_on_foreground).setOnClickListener(this)
        findViewById<Button>(R.id.btn_check_app_on_foreground2).setOnClickListener(this)
        findViewById<Button>(R.id.btn_check_debug_memory_info).setOnClickListener(this)

        mTextView = findViewById(R.id.text_view)
        mTextView.movementMethod = ScrollingMovementMethod.getInstance()

        am = getSystemService(ACTIVITY_SERVICE) as ActivityManager
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_show_memory_info) {
            val memoryInfo = ActivityManager.MemoryInfo()
            am.getMemoryInfo(memoryInfo)
            mTextView.text = """
            totalMem = ${memoryInfo.totalMem}
            availMem = ${memoryInfo.availMem}
            threshold = ${memoryInfo.threshold}
            lowMemory = ${memoryInfo.lowMemory}
            """.trimIndent()
        } else if (v.id == R.id.btn_check_app_on_foreground) {
            val pkgName = "com.blog.demo"
            mTextView.text = checkAppOnForeground(pkgName).toString()
        } else if (v.id == R.id.btn_check_app_on_foreground2) {
            val pkgName = "com.blog.demo"
            mTextView.text = checkAppOnForeground2(pkgName).toString()
        } else if (v.id == R.id.btn_check_debug_memory_info) {
            getDebugMemoryInfo()
        }
    }

    private fun checkAppOnForeground(pkgName: String): Boolean {
        val taskInfos = am.getRunningTasks(1)
        return if (taskInfos != null && taskInfos.size > 0) {
            taskInfos[0].topActivity?.packageName == pkgName
        } else {
            false
        }
    }

    private fun checkAppOnForeground2(pkgName: String): Boolean {
        val processInfoList = am.runningAppProcesses
        for (processInfo in processInfoList) {
            if (processInfo.processName == pkgName && processInfo.importance
                    == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true
            }
        }
        return false
    }

    private fun getDebugMemoryInfo() {
        val processInfoList = am.runningAppProcesses
        val sBuffer = StringBuffer()
        for (processInfo in processInfoList) {
            val pids = intArrayOf(processInfo.pid)
            val debugMemoryInfo = am.getProcessMemoryInfo(pids)
            sBuffer.append("processName = ${processInfo.processName}")
            for (info in debugMemoryInfo) {
                sBuffer.append("dalvikPss = ${info.dalvikPss}")
                sBuffer.append("nativePss = ${info.nativePss}")
                sBuffer.append("otherPss = ${info.otherPss}")
            }
        }
        mTextView.text = sBuffer.toString()
    }
}