package com.blog.demo.component.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.blog.demo.LogTool;

public class DemoService extends Service {
    public final static String LOG_TAG = "DemoService";

    private IBinder mBinder = new DemoBind();

    @Override
    public IBinder onBind(Intent intent) {
        LogTool.logi(LOG_TAG, "onBind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogTool.logi(LOG_TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogTool.logi(LOG_TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogTool.logi(LOG_TAG, "onStartCommand " + Thread.currentThread().getName());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogTool.logi(LOG_TAG, "onDestroy");
    }

    public class DemoBind extends Binder {

        public DemoService getService() {
            return DemoService.this;
        }

    }
}
