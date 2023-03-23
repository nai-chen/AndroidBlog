package com.blog.demo.component

import android.app.Activity
import android.os.Bundle
import android.os.Environment
import android.os.StatFs
import android.widget.TextView
import com.blog.demo.LogTool.logi
import com.blog.demo.R

class EnvironmentActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_text)

        val textView: TextView = findViewById(R.id.text_view)
        textView.text = """
                State = ${Environment.getExternalStorageState()}
                Music = ${getPublicDirectory(Environment.DIRECTORY_MUSIC)}
                Podcast = ${getPublicDirectory(Environment.DIRECTORY_PODCASTS)}
                Ringtone = ${getPublicDirectory(Environment.DIRECTORY_RINGTONES)}
                Notification = ${getPublicDirectory(Environment.DIRECTORY_NOTIFICATIONS)}
                Picture = ${getPublicDirectory(Environment.DIRECTORY_PICTURES)}
                Movie = ${getPublicDirectory(Environment.DIRECTORY_MOVIES)}
                Download = ${getPublicDirectory(Environment.DIRECTORY_DOWNLOADS)}
                DCIM = ${getPublicDirectory(Environment.DIRECTORY_DCIM)}
                ExternalStorageDirectory = ${Environment.getExternalStorageDirectory().absolutePath}
                DataDirectory = ${Environment.getDataDirectory().absolutePath}
                DownloadCacheDirectory = ${Environment.getDownloadCacheDirectory().absolutePath}
                RootDirectory = ${Environment.getRootDirectory().absolutePath}
                """.trimIndent()

        val statFs = StatFs(Environment.getExternalStorageDirectory().absolutePath)
        logi("EnvironmentActivity", "TotalBytes = " + statFs.totalBytes)
        logi("EnvironmentActivity", "FreeBytes = " + statFs.freeBytes)
        logi("EnvironmentActivity", "AvailableBytes = " + statFs.availableBytes)
    }

    private fun getPublicDirectory(type: String): String {
        return Environment.getExternalStoragePublicDirectory(type).absolutePath
    }

}