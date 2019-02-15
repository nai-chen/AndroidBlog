package com.blog.demo.application.info;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.blog.demo.LogTool;
import com.blog.demo.R;

public class EnvironmentActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_text);

        TextView textView = findViewById(R.id.text_view);
        textView.setText("State = " + Environment.getExternalStorageState() + "\n"
                + "Music = " + getPublicDirectory(Environment.DIRECTORY_MUSIC) + "\n"
                + "Podcast = " + getPublicDirectory(Environment.DIRECTORY_PODCASTS) + "\n"
                + "Ringtone = " + getPublicDirectory(Environment.DIRECTORY_RINGTONES) + "\n"
                + "Notification = " + getPublicDirectory(Environment.DIRECTORY_NOTIFICATIONS) + "\n"
                + "Picture = " + getPublicDirectory(Environment.DIRECTORY_PICTURES) + "\n"
                + "Movie = " + getPublicDirectory(Environment.DIRECTORY_MOVIES) + "\n"
                + "Download = " + getPublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "\n"
                + "DCIM = " + getPublicDirectory(Environment.DIRECTORY_DCIM) + "\n"
                + "ExternalStorageDirectory = " + Environment.getExternalStorageDirectory().getAbsolutePath() + "\n"
                + "DataDirectory = " + Environment.getDataDirectory().getAbsolutePath() + "\n"
                + "DownloadCacheDirectory = " + Environment.getDownloadCacheDirectory().getAbsolutePath() + "\n"
                + "RootDirectory = " + Environment.getRootDirectory().getAbsolutePath() + "\n"
        );

        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        LogTool.logi("EnvironmentActivity", "TotalBytes = " + statFs.getTotalBytes());
        LogTool.logi("EnvironmentActivity", "FreeBytes = " + statFs.getFreeBytes());
        LogTool.logi("EnvironmentActivity", "AvailableBytes = " + statFs.getAvailableBytes());
    }

    private String getPublicDirectory(String type) {
        return Environment.getExternalStoragePublicDirectory(type).getAbsolutePath();
    }

}
