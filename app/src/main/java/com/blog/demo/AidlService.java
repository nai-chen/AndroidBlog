package com.blog.demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by cn on 2017/2/14.
 */

public class AidlService extends Service {
    private final static String LOGTAG = "AidlService";

    private AidlInterface mInterface = new AidlInterface();

    @Override
    public IBinder onBind(Intent intent) {
        return mInterface;
    }

    private static class AidlInterface extends IAidlInterface.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
                    double aDouble, String aString) throws RemoteException {
        }

        @Override
        public int add(int val1, int val2) throws RemoteException {
            return val1 + val2;
        }

    }

}
