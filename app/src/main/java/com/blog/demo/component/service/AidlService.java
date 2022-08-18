package com.blog.demo.component.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class AidlService extends Service {
    private AidlInterface mInterface = new AidlInterface();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mInterface;
    }

    private static class AidlInterface extends IAidlInterface.Stub {

        @Override
        public int add(int val1, int val2) {
            return val1 * 10 + val2;
        }

    }
}
