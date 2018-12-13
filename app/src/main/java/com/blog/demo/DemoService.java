package com.blog.demo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
/**
 * Created by cn on 2017/2/14.
 */

public class DemoService extends Service {
    public final static String LOGTAG = "DemoService";

    private IBinder mBinder = new DemoBind();

    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.log(LOGTAG, "onBind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtil.log(LOGTAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.log(LOGTAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.log(LOGTAG, "onStartCommand " + Thread.currentThread().getName());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.log(LOGTAG, "onDestroy");
    }

    public class DemoBind extends Binder {

        public DemoService getService() {
            return DemoService.this;
        }

    }

}
